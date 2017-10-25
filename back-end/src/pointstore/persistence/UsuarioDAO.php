<?php
namespace pointstore\persistence;

use Doctrine\ORM\EntityManager;
use Doctrine\ORM\Tools\Setup;
use pointstore\persistence\AbstractDAO;
use pointstore\entity\Usuario;

class UsuarioDAO extends AbstractDAO{
	public function __construct() {
		parent::__construct('pointstore\entity\Usuario');
	}
}