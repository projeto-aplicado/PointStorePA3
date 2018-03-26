<?php

namespace pointstore\entity;


/**
 * @Entity
 * @Table(name="venda")
 */
class Venda{

	/**
	*var integer
    * @Id
	*@Column(type="integer")
	*@GeneratedValue(strategy="AUTO")
	*/
	private $id;

	/**
	*
	* @var string @Column(type="string", length=255)
	*/
	private $titulo;

	/**
	*
	* @var integer @Column(type="integer", length=255)
	*/
	private $valor;

	//@ManyToOne(targetEntity="\pointstore\entity\Usuario",inversedBy="Venda",cascade={"persist"})
    //@JoinColumn(name="id_usuario_vendedor", referencedColumnName="id")
	/**
     * @var integer
     * @Column(type="integer", length=11,name="id_usuario_vendedor")
	 */
	private $id_usuario_vendedor;

	/**
     *
	 * @var integer
     * @Column(type="integer", length=11,name="id_usuario_comprador")
     * @JoinColumn(name="id_usuario_comprador", referencedColumnName="id")
     * @ManyToOne(targetEntity="\pointstore\entity\Usuario",inversedBy="Venda",cascade={"persist"})
     *
	 */
	private $id_usuario_comprador;


	//@OneToOne(targetEntity="\pointstore\entity\MeusPontos",inversedBy="Venda",cascade={"persist"})
	//@JoinColumn(name="id_meus_pontos", referencedColumnName="id")
	/**
     * @Column(type="integer", length=11,name="id_meus_pontos")
	 */
	private $id_meus_pontos;

	/**
	*
	* @var string @Column(type="string", length=255)
	*/
	private $status;
    /**
     *
     * @var integer @Column(type="integer", length=255)
     */
	private $qtd_pontos;

	public function __construct(){}

	public function getId(){
		return $this->id;
	}

	public function setId($id){
		$this->id = $id;
	}

	public function getTitulo(){
		return $this->titulo;
	}

	public function setTitulo($titulo){
		$this->titulo = $titulo;
	}

	public function getValor(){
		return $this->valor;
	}

	public function setValor($valor){
		$this->valor = $valor;
	}

	public function getIdUsuarioVendedor(){
		return $this->id_usuario_vendedor;
	}

	public function setIdUsuarioVendedor($idUsuarioVendedor){
		$this->id_usuario_vendedor = $idUsuarioVendedor;
	}

	public function getIdUsuarioComprador(){
		return $this->id_usuario_comprador;
	}

	public function setIdUsuarioComprador($idUsuarioComprador){
		$this->id_usuario_comprador = $idUsuarioComprador;
	}

	public function getIdMeusPontos(){
		return $this->id_meus_pontos;
	}

	public function setIdMeusPontos($idMeusPontos){
		$this->id_meus_pontos = $idMeusPontos;
	}

	public function getStatus(){
		return $this->status;
	}

	public function setStatus($status){
		$this->status = $status;
	}

	public function getQtdPontos(){
		return $this->qtd_pontos;
	}

	public function setQtdPontos($qtd_pontos){
		$this->qtd_pontos = $qtd_pontos;
	}
}