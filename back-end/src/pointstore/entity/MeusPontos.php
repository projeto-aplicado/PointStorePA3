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
	 * @ManyToOne(targetEntity="\pointstore\entity\Usuario",inversedBy="MeusPontos")
	 * @JoinColumn(name="id_usuario", referencedColumnName="id")
	 */
	private $id_usuario;

	/**
	 * @OneToMany(targetEntity="\pointstore\entity\Venda", mappedBy="idMeusPontos", cascade={"persist", "remove", "merge"}, orphanRemoval=true)
	 */
	private $venda;


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
		return $this->id_usuario;
	}

	public function setUsuario($usuario){
		$this->id_usuario = $usuario;
	}

	public function getVenda(){
		return $this->venda;
	}

	public function setVenda($venda){
		$this->venda = $venda;
	}
}