<?php
namespace pointstore\controller;

use pointstore\persistence\MeusPontosDAO;
use pointstore\entity\Usuario;
use pointstore\entity\MeusPontos;
use pointstore\controller\AbstractController;
use Doctrine\ORM\EntityManager;

class MeusPontosController extends AbstractController{

	public function __construct(){
    	parent::__construct(new MeusPontosDAO());
	}

	public function insert($json){}

	public function update($json){}

	public function listarPontoUsuario($id){
		if($id == null){
			$meusPontosDetached = $this->getDao()->entityManager->createQuery('SELECT meusPontos FROM pointstore\entity\MeusPontos meusPontos');
			$queryResult = $meusPontosDetached->getArrayResult();
		} else{
			$meusPontosDetached = $this->getDao()->entityManager->createQuery('SELECT meusPontos FROM \pointstore\entity\MeusPontos meusPontos WHERE meusPontos.usuario_id = :usuario_id');
			$meusPontosDetached->setParameter('usuario_id', $id);
			$queryResult = $meusPontosDetached->getResult()[0];
		}
		return $queryResult;

	}

}
