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
            $mensagem = 'Você não pode vender '.$json->quantidade.' porque você só tem '.$queryResult[0]['quantidadePonto'].' pontos';
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
        if ($id == null) {
            $qb = $this->getDao()->entityManager->getConnection();
            return $qb->query('select *,v.id as venda_id from venda v inner join usuario u on u.id = v.id_usuario_vendedor where status = 1')->fetchAll();
        } else {
            $qb = $this->getDao()->entityManager->getConnection();
            return $qb->query('select *,v.id as venda_id from venda v inner join usuario u on u.id = v.id_usuario_vendedor where v.id = '.$id)->fetch();
        }
        return $queryResult;

    }


    public function listarVendaPublicado($id){
        if ($id == null) {
            return "nenhum registro de venda cadastrado encontrado";
        }else{
            $qb = $this->getDao()->entityManager->getConnection();
            return $qb->query('select *,v.id as venda_id from venda v inner join usuario u on u.id = v.id_usuario_vendedor where status = 1 and v.id_usuario_vendedor = ' . $id)->fetchAll();
        }
    }

    public function atualizarVendaPublicada($json){
        $minhasVendas = $this->getDao()->entityManager->find('\pointstore\entity\Venda', $json->venda_id);
        $minhasVendas->setValor($json->valor);
        $this->getDao()->update($minhasVendas);
        return ["mensagem" => "Venda atualizada com sucesso"];
    }

    public function excluirVendaPublicadaId($id){
        $vendaDetached = $this->getDao()->entityManager->find('\pointstore\entity\Venda', $id);
        $meusPontosDetached = $this->getDao()->entityManager->find('\pointstore\entity\MeusPontos', $vendaDetached->getIdMeusPontos());

        $pontosVendedor = $meusPontosDetached->getQuantidadePonto() + $vendaDetached->getQtdPontos();

        $meusPontosDetached->setQuantidadePonto($pontosVendedor);


        $this->getDao()->update($meusPontosDetached);

        $queryResultVenda = $this->getDao()->entityManager->createQuery('DELETE pointstore\entity\Venda venda
        WHERE venda.id = :id');
        $queryResultVenda->setParameter('id', $id);
        $queryResultVenda->execute();

        $this->getDao()->entityManager->flush();

        return ["mensagem" => "venda excluida com sucesso, seus pontos foram devolvidos com seguranca"];

    }

}
