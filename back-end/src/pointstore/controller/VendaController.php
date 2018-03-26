<?php

namespace pointstore\controller;

use pointstore\entity\Venda;
use pointstore\entity\MeusPontos;
use pointstore\entity\Usuario;
use pointstore\persistence\VendaDAO;
//use pointstore\persistence\MeusPontosDAO;

class VendaController extends AbstractController
{

    public function __construct()
    {
        parent::__construct(new VendaDAO());
    }

    public function insert($json)
    {
        $venda = new Venda();
        $meusPontos = new MeusPontos();
        $usuario = new Usuario();


        $meusPontosDetached = $this->getDao()->entityManager->createQuery('SELECT meusPontos FROM pointstore\entity\MeusPontos meusPontos
        WHERE meusPontos.id_usuario = :id_usuario AND meusPontos.tipoDePonto = :tipo_ponto');

        $meusPontosDetached->setParameter('id_usuario', $json->id_usuario);
        $meusPontosDetached->setParameter('tipo_ponto', $json->tipoDePontos);
        $queryResult = $meusPontosDetached->getArrayResult();

        //$meusPontos->setId($queryResult[0]['id']);
        //$usuario->setId($json->id_usuario);

        $this->getDao()->entityManager->flush();

        $venda->setValor($json->valor);
        $venda->setQtdPontos($json->quantidade);
        $venda->setTitulo($json->tipoDePontos);
        $venda->setStatus(1);//PUBLICADO
        $venda->setIdMeusPontos($queryResult[0]['id']);
        $venda->setIdUsuarioVendedor($json->id_usuario);

        if($queryResult[0]['quantidadePonto'] < $json->quantidade){
            $mensagem = 'Você não pode vender '.$json->quantidade.' porque você só tem '.$queryResult[0]['quantidadePonto'].' pontos ';
        }else{
            //$qb = $this->getDao()->entityManager->getConnection();
            $this->getDao()->insert($venda);

            $PontosVendedorDetached = $this->getDao()->entityManager->find('\pointstore\entity\MeusPontos', $queryResult[0]['id']);

            $pontosVendedor = $PontosVendedorDetached->getQuantidadePonto();
            $pontosVendedor = $pontosVendedor-$json->quantidade;

            //$qb->update('meus_pontos',array('quantidadePonto'=>$pontosVendedor),array('id'=>$queryResult[0]['id']));
            $PontosVendedorDetached->setQuantidadePonto($pontosVendedor);
            $this->getDao()->update($PontosVendedorDetached);
            $mensagem = 'Venda cadastrada com sucesso!';
        }

        return ['mensagem'=> $mensagem];
    }

    public function update($json)
    {
        $meusPontosDetached = $this->getDao()->entityManager->find('\pointstore\entity\MeusPontos', $json->id);
        $meusPontosDetached->setQuantidadePonto($json->quantidadePonto);
        $this->getDao()->update($meusPontosDetached);
        return ["mensagem" => "Ponto atualizada com sucesso"];
    }

    public function listar($id)
    {
        

    }


}
