<?php

namespace pointstore\entity;


/**
 * @Entity
 * @Table(name="meus_pontos")
 */
class MeusPontos{

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
	private $tipoDePonto;

	/**
	*
	* @var integer @Column(type="integer", length=255)
	*/
	private $quantidadePonto;

	/**
	 * @ManyToOne(targetEntity="\pointstore\entity\Usuario")
	 * @JoinColumn(name="usuario_id", referencedColumnName="id")
	 */
	private $usuario;


	public function __construct(){}

	public function getId(){
		return $this->id;
	}

	public function setId($id){
		$this->id = $id;
	}

	public function getTipoDePonto(){
		return $this->tipoDePonto;
	}

	public function setTipoDePonto($tipoDePonto){
		$this->tipoDePonto = $tipoDePonto;
	}

	public function getQuantidadePonto(){
		return $this->quantidadePonto;
	}

	public function setQuantidadePonto($quantidadePonto){
		$this->quantidadePonto = $quantidadePonto;
	}

	public function getUsuario(){
		return $this->usuario;
	}

	public function setUsuario($usuario){
		$this->usuario = $usuario;
	}
}