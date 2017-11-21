<?php

$loader = require __DIR__ . '/vendor/autoload.php';

use pointstore\entity\Usuario;
use pointstore\controller\UsuarioController;

$app = new \Slim\Slim();

$usuarioCtrl = new UsuarioController();

$app->get ( '/', function () {
	echo json_encode( [
			"api" => "PointStore",
			"version" => "1.0.0"
	] );
});

$app->get ( '/usuario(/)', function ($id = null) use ($usuarioCtrl) {
	echo json_encode($usuarioCtrl->get($id));
});

$app->get( '/usuario(/(:id))', function($id = null) use ($usuarioCtrl){
	echo json_encode($usuarioCtrl->get($id));
});

$app->post( '/usuario(/)', function() use ($usuarioCtrl){
	$app = \Slim\Slim::getInstance();
	$json = json_decode($app->request()->getBody());
	echo json_encode($usuarioCtrl->insert($json));
} );

$app->put( '/usuario(/)', function() use ($usuarioCtrl) {
	$app = \Slim\Slim::getInstance();
	$json = json_decode($app->request()->getBody());
	echo json_encode($usuarioCtrl->atualizarEmail($json));
} );

$app->post( '/login(/)', function() use ($usuarioCtrl){
	$app = \Slim\Slim::getInstance();
	$json = json_decode($app->request()->getBody());
	echo json_encode($usuarioCtrl->logarUsuario($json));
} );



$app->run();
