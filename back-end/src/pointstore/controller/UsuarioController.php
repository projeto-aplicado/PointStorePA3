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
    	$user = new Usuario();
    	$user->setNome($json->nome);
    	$user->setSobrenome($json->sobrenome);
    	$user->setEmail($json->email);
    	$user->setLogin($json->login);
    	$user->setSenha($json->senha);
    	$this->getDao()->insert($user);
    	return ["mensagem"=>"Usuario inserido com sucesso"];
	}
	
	public function update($id, $json){
		
	}
	
}