<?php
namespace pointstore\persistence;

use Doctrine\ORM\EntityManager;
use Doctrine\ORM\Tools\Setup;

abstract class AbstractDAO {
	public $entityManager;
	private $entityPath;
	public function __construct($entityPath) {
			$this->entityPath = $entityPath;
			$this->entityManager = $this->createEntityManager();
		}
	public function createEntityManager() {
		$path = array (
		    'pointstore/entity'
		);
		$devMode = true;
		$config = Setup::createAnnotationMetadataConfiguration( $path, $devMode );
		$connectionOptions =  array (
		    'dbname' => 'PointStore',
			'user' => 'root',
			'password' => 'Yveltal',
			'host' => 'localhost',
			'driver' => 'pdo_mysql'
		);

		return EntityManager::create( $connectionOptions, $config );
	}
	
	public function insert($obj){
		$this->entityManager->persist($obj);
		$this->entityManager->flush();
	}
	
	public function update($obj){
		$this->entityManager->merge($obj);
		$this->entityManager->flush();
	}

	public function findById($id){
		return $this->entityManager->find( $this->entityPath ,$id) ;
	}
	
	public function findAll(){
		$collection = $this->entityManager->getRepository( $this->entityPath )->findAll();
		$data = array();
		foreach ( $collection as $obj ) {
			$data [] = $obj;
		}
		return $data;
	}
}