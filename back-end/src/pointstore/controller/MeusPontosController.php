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

	public function cadastrarPontoUsuario($user){

	}

}
