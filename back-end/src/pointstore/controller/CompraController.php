<?php

namespace pointstore\controller;

use pointstore\entity\Venda;
use pointstore\entity\MeusPontos;
use pointstore\entity\Usuario;
use pointstore\persistence\VendaDAO;
use Doctrine\ORM\Query\ResultSetMapping;
//use pointstore\persistence\MeusPontosDAO;

class CompraController extends AbstractController
{

    public function __construct()
    {
        parent::__construct(new VendaDAO());
    }

    public function insert($json)
    {

    }

    public function update($json)
    {

        $venda = new Venda();

        $qb = $this->getDao()->entityManager->getConnection();
        $rsUsuario= $qb->query("SELECT * FROM usuario where id = {$json->id_usuario}")->fetch();
        $rsVenda = $qb->query("SELECT * FROM venda where id = {$json->venda_id}")->fetch();
        $rsPontosComprador = $qb->query("SELECT * FROM meus_pontos where id_usuario = {$json->id_usuario} and tipoDePonto = '{$rsVenda['titulo']}'")->fetch();

        if($rsUsuario['credito'] < $rsVenda['valor']){
            return ['mensagem'=> "Voce não tem credito para finalizar a compra"];
        }

        if($rsVenda['status'] != 1){
            return ['mensagem'=> "O produto já foi comprado!"];
        }

        $rsUsuario['credito'] = $rsUsuario['credito'] - $rsVenda['valor'];

        $compradorDetached = $this->getDao()->entityManager->find('\pointstore\entity\Usuario', $json->id_usuario);
        $compradorDetached->setCredito($rsUsuario['credito']);
        $this->getDao()->update($compradorDetached);

        $vendedorDetached = $this->getDao()->entityManager->find('\pointstore\entity\Usuario', $rsVenda['id_usuario_vendedor']);
        $credito = $vendedorDetached->getCredito();
        $credito = $credito + $rsVenda['valor'];
        $vendedorDetached->setCredito($credito);
        $this->getDao()->update($vendedorDetached);

        $vendaDetached = $this->getDao()->entityManager->find('\pointstore\entity\Venda', $json->venda_id);
        $vendaDetached->setStatus(2);//COMPRADO
        $vendaDetached->setIdUsuarioComprador($json->id_usuario);

        $pontos = $vendaDetached->getQtdPontos();

        $PontosCompradorDetached = $this->getDao()->entityManager->find('\pointstore\entity\MeusPontos', $rsPontosComprador['id']);
        $pontosComprador = $PontosCompradorDetached->getQuantidadePonto();
        $pontosComprador = $pontosComprador+$pontos;
        $PontosCompradorDetached->setQuantidadePonto($pontosComprador);

        $this->getDao()->update($PontosCompradorDetached);
        $this->getDao()->update($vendaDetached);

        $mensagem = 'Compra realizada com sucesso!';


        return ['mensagem'=> $mensagem];
    }

    public function listar($id)
    {
        if ($id == null) {
            $qb = $this->getDao()->entityManager->getConnection();
            return $qb->query('select *,ve.nome as vendedor from venda v 
            inner join usuario u on u.id = v.id_usuario_comprador
            inner join usuario ve on ve.id = v.id_usuario_vendedor')->fetchAll();
        } else {
            $qb = $this->getDao()->entityManager->getConnection();
            return $qb->query('select *,ve.nome as vendedor from venda v 
            inner join usuario u on u.id = v.id_usuario_comprador 
            inner join usuario ve on ve.id = v.id_usuario_vendedor
            where u.id = '.$id)->fetchAll();
        }
        return $queryResult;

    }

}
