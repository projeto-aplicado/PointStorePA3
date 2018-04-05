<?php

namespace pointstore\controller;

use pointstore\persistence\MeusPontosDAO;
use pointstore\entity\Usuario;
use pointstore\entity\MeusPontos;
use pointstore\controller\AbstractController;
use Doctrine\ORM\EntityManager;

class MeusPontosController extends AbstractController
{

    public function __construct()
    {
        parent::__construct(new MeusPontosDAO());
    }

    public function insert($json)
    {

    }

    public function update($json)
    {
        $meusPontosDetached = $this->getDao()->entityManager->find('\pointstore\entity\MeusPontos', $json->id);
        $meusPontosDetached->setQuantidadePonto($json->quantidadePonto);
        $this->getDao()->update($meusPontosDetached);
        return ["mensagem" => "Ponto atualizada com sucesso"];
    }

    public function listarPontoUsuario($id)
    {

        if ($id == null) {
            $meusPontosDetached = $this->getDao()->entityManager->createQuery('SELECT meusPontos FROM pointstore\entity\MeusPontos meusPontos');
            $queryResult = $meusPontosDetached->getArrayResult();
        } else {
            $meusPontosDetached = $this->getDao()->entityManager->createQuery('SELECT meusPontos FROM \pointstore\entity\MeusPontos meusPontos WHERE meusPontos.id_usuario = :id_usuario');
            $meusPontosDetached->setParameter('id_usuario', $id);
            $queryResult = $meusPontosDetached->getArrayResult();
        }
        return $queryResult;

    }

}
