<?php

namespace pointstore\entity;

/**
 * @Entity
 * @Table(name="usuario")
 */
class Usuario{

	/**
	*	@var integer @Id
	*      @Column(type="integer")
	*      @GeneratedValue(strategy="AUTO")
	*/
	private $id;

	/**
	*
	* @var string @Column(type="string", length=255)
	*/
	private $nome;
	
	/**
	*
	* @var string @Column(type="string", length=255)
	*/
	private $sobrenome;
	
	/**
	*
	* @var string @Column(type="string", length=255)
	*/
	private $cpf;
	
	/**
	*
	* @var string @Column(type="string", length=255)
	*/
	private $credito;
	
	/**
	*
	* @var string @Column(type="string", length=255)
	*/
	private $email;
	
	/**
	*
	* @var string @Column(type="string", length=255)
	*/
	private $login;
	
	/**
	*
	* @var string @Column(type="string", length=255)
	*/
	private $senha;
	
	/**
	*
	* @var integer @Column(type="integer", length=255)
	*/
	private $vendas;
	
	public function getId(){
		return $this->id;
	}

	public function setId($id){
		$this->id = $id;
	}

	public function getNome(){
		return $this->nome;
	}

	public function setNome($nome){
		$this->nome = $nome;
	}

	public function getSobrenome(){
		return $this->sobrenome;
	}

	public function setSobrenome($sobrenome){
		$this->sobrenome = $sobrenome;
	}

	public function getCpf(){
		return $this->cpf;
	}

	public function setCpf($cpf){
		$this->cpf = $cpf;
	}

	public function getCredito(){
		return $this->credito;
	}

	public function setCredito($credito){
		$this->credito = $credito;
	}

	public function getEmail(){
		return $this->email;
	}

	public function setEmail($email){
		$this->email = $email;
	}

	public function getLogin(){
		return $this->login;
	}

	public function setLogin($login){
		$this->login = $login;
	}

	public function getSenha(){
		return $this->senha;
	}

	public function setSenha($senha){
		$this->senha = $senha;
	}

	public function getVendas(){
		return $this->vendas;
	}

	public function setVendas($vendas){
		$this->vendas = $vendas;
	}

	public function __construct(){}

	/*public function __construct($json->nome,$json->sobrenome,$json->email,$json->login,$json->senha){
		$this->nome = $json->nome;
  		$this->sobrenome = $json->sobrenome;
   		$this->email = $json->email;
   		$this->login = $json->login;
   		$this->senha = $json->senha;
	}*/

	/*public function __construct($nome = null, $sobrenome = null, $cpf = null, $credito = null, $email = null, $login = null, $senha = null, $vendas = null){
		$this->nome = $nome;
  		$this->sobrenome = $sobrenome;
  		$this->cpf = $cpf;
  		$this->credito = $credito;
   		$this->email = $email;
   		$this->login = $login;
   		$this->senha = $senha;
   		$this->vendas = $vendas;
	}*/

	public static function construct($array){
		$obj = new Usuario();
		$obj->setNome( $array['nome']);
		$obj->setSobrenome( $array['sobrenome']);
		$obj->setEmail( $array['email']);
		$obj->setLogin( $array['login']);
		$obj->setSenha( $array['senha']);
		return $obj;
	}

	public function toString(){
 		return "  [id:" .$this->id. "]  [nome:" .$this->nome. "]  [sobrenome:" .$this->sobrenome. "]  
 		[cpf:" .$this->cpf. "]  [credito:" .$this->credito. "]  [email:" .$this->email. "]  [login:" .$this->login. "]  
 		[senha:" .$this->senha. "]	[vendas:" .$this->vendas. "]  ";
	}

 	public function toArray(){
   		return [
  				"id"=>$this->id,
  				"nome"=>$this->nome,
  				"sobrenome"=>$this->sobrenome,
  				"cpf"=>$this->cpf,
  				"credito"=>$this->credito,
   				"email"=>$this->email,
   				"login"=>$this->login,
   				"senha"=>$this->senha,
   				"vendas"=>$this->vendas];
 	}
	
}
