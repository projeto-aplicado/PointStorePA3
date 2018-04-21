<?php
namespace pointstore\controller;

use pointstore\persistence\UsuarioDAO;
use pointstore\entity\Usuario;
use pointstore\entity\MeusPontos;
use pointstore\controller\AbstractController;
use Doctrine\ORM\EntityManager;

class UsuarioController extends AbstractController {
	
	public function __construct(){
    	parent::__construct(new UsuarioDAO());
	}

	public function insert($json){

    	$user = new Usuario();
    	$user->setNome($json->nome);
    	$user->setSobrenome($json->sobrenome);
    	$user->setEmail($json->email);
    	$user->setLogin($json->login);
    	$user->setSenha($json->senha);

    	$user->setCredito("100");

//    	$pontos = $this->getMeusPontosDefault();
//    	$user->setMeusPontos($pontos);


    	$this->getDao()->insert($user);
    	$pontos = $this->getMeusPontosDefault($user);
    	$this->getDao()->insert($pontos[0]);
        $this->getDao()->insert($pontos[1]);
    	return ["mensagem"=>"Usuario inserido com sucesso"];
	}

	public function getMeusPontosDefault($user){
		$tam = new MeusPontos();
    	$tam->setTipoDePonto("Tam");
    	$tam->setUsuario($user);
    	$tam->setQuantidadePonto(0);

    	$gol = new MeusPontos();
    	$gol->setTipoDePonto("Gol");
        $gol->setUsuario($user);
    	$gol->setQuantidadePonto(0);

    	return array($tam, $gol);
	}
	
	public function atualizarSenha($json){
		$usuarioDetached = $this->getDao()->entityManager->createQuery('SELECT user FROM \pointstore\entity\Usuario user WHERE user.email = :email');
		$usuarioDetached->setParameter('email', $json->email);
		$queryResult = $usuarioDetached->getResult()[0];
    	$queryResult->setSenha($json->senha);
		$this->getDao()->update($queryResult);
    	return ["mensagem"=>"Senha atualizada com sucesso"];
	}

	public function update($json){
		$usuarioDetached = new Usuario();
		$usuarioDetached = $this->getDao()->entityManager->find('\pointstore\entity\Usuario', $json->id);
		$usuarioDetached->setNome($json->nome);
    	$usuarioDetached->setSobrenome($json->sobrenome);
    	$usuarioDetached->setCpf($json->cpf);
    	$usuarioDetached->setEmail($json->email);
    	$usuarioDetached->setLogin($json->login);
    	$usuarioDetached->setSenha($json->senha);
		$this->getDao()->update($usuarioDetached);
    	return ["mensagem"=>"Usuario atualizada com sucesso"];
		
	}


	public function logarUsuario($json){
		$usuarioDetached = new Usuario();
		$usuarioDetached = $this->getDao()->entityManager->createQuery('SELECT user FROM \pointstore\entity\Usuario user WHERE user.login = :login and user.senha = :senha');
		$usuarioDetached->setParameter('login', $json->login);
		$usuarioDetached->setParameter('senha', $json->senha);
		if(!empty($usuarioDetached->getResult())){
			$queryResult = (object) $usuarioDetached->getArrayResult()[0];
			return $queryResult;			
		}else{
			$usuarioDetached = null;
			return $usuarioDetached;
		}
	}

	public function listarUsuario($id){
		$usuarioDetached = new Usuario();
		if($id == null){
			$usuarioDetached = $this->getDao()->entityManager->createQuery('SELECT user FROM pointstore\entity\Usuario user');
			$queryResult = $usuarioDetached->getArrayResult();
		}else{
			$usuarioDetached = $this->getDao()->entityManager->createQuery('SELECT user FROM \pointstore\entity\Usuario user WHERE user.id = :id');
            $usuarioDetached->setParameter('id', $id);
            $queryResult = $usuarioDetached->getArrayResult();
		}
		return $queryResult;
	}
	
}