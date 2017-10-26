<?php
namespace pointstore\controller;

use pointstore\persistence\UsuarioDAO;
use pointstore\entity\Usuario;
use pointstore\controller\AbstractController;

class UsuarioController extends AbstractController {
	
	public function __construct() {
    	parent::__construct(new UsuarioDAO());
	}

	public function insert($json){
    	$user = new Usuario($json->nome,$json->sobrenome,$json->email,$json->login,$json->senha);
    	$this->getDao()->insert( $user );
    	return ["mensagem"=>"Usuario inserido com sucesso"];
	}
	
	public function update($id, $json){
		
	}
	
}