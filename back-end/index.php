<?php

$loader = require __DIR__ . '/vendor/autoload.php';

use pointstore\entity\Usuario;
use pointstore\controller\UsuarioController;
use pointstore\entity\MeusPontos;
use pointstore\controller\MeusPontosController;
use pointstore\controller\VendaController;
use pointstore\controller\CompraController;

$app = new \Slim\Slim();

$usuarioCtrl = new UsuarioController();
$meusPontosCtrl = new MeusPontosController();
$vendaCtrl = new VendaController();
$compraCtrl = new CompraController();

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

$app->put( '/usuario/senha(/)', function() use ($usuarioCtrl) {
	$app = \Slim\Slim::getInstance();
	$json = json_decode($app->request()->getBody());
	echo json_encode($usuarioCtrl->atualizarSenha($json));
} );

$app->post( '/login(/)', function() use ($usuarioCtrl){
	$app = \Slim\Slim::getInstance();
	$json = json_decode($app->request()->getBody());
	echo json_encode($usuarioCtrl->logarUsuario($json));
} );

$app->put( '/login/usuario(/)', function() use ($usuarioCtrl){
	$app = \Slim\Slim::getInstance();
	$json = json_decode($app->request()->getBody());
	echo json_encode($usuarioCtrl->update($json));
} );

$app->get ( '/meuspontos(/)', function ($id = null) use ($meusPontosCtrl) {
	echo json_encode($meusPontosCtrl->listarPontoUsuario($id));
});

$app->get( '/meuspontos(/(:id))', function($id = null) use ($meusPontosCtrl){
	echo json_encode($meusPontosCtrl->listarPontoUsuario($id));
});

$app->put( '/meuspontos/atualizar(/)', function() use ($meusPontosCtrl){
	$app = \Slim\Slim::getInstance();
	$json = json_decode($app->request()->getBody());
	echo json_encode($meusPontosCtrl->update($json));
} );

$app->post( '/venda(/)', function() use ($vendaCtrl){
    $app = \Slim\Slim::getInstance();
    $json = json_decode($app->request()->getBody());
    echo json_encode($vendaCtrl->insert($json));
} );

$app->get( '/venda(/(:id))', function($id = null) use ($vendaCtrl){
    echo json_encode($vendaCtrl->listar($id));
});

$app->get( '/venda/gerenciarVendas(/(:id))', function($id = null) use ($vendaCtrl){
    echo json_encode($vendaCtrl->listarVendaPublicado($id));
});

$app->put( '/venda/gerenciarVendas(/(:id))', function() use ($vendaCtrl){
    $app = \Slim\Slim::getInstance();
    $json = json_decode($app->request()->getBody());
    echo json_encode($vendaCtrl->atualizarVendaPublicada($json));
});

$app->delete( '/venda/excluirVenda(/(:id))', function() use ($vendaCtrl){
	$app = \Slim\Slim::getInstance();
    $json = json_decode($app->request()->getBody());
    echo json_encode($vendaCtrl->excluirVendaPublicada($json));
});

$app->delete( '/venda/excluirVendaId(/(:id))', function($id = null) use ($vendaCtrl){
    echo json_encode($vendaCtrl->excluirVendaPublicadaId($id));
});

$app->get( '/compra(/(:id))', function($id = null) use ($compraCtrl){
    echo json_encode($compraCtrl->listar($id));
});
$app->put( '/compra(/)', function() use ($compraCtrl){
    $app = \Slim\Slim::getInstance();
    $json = json_decode($app->request()->getBody());
    echo json_encode($compraCtrl->update($json));
} );


$app->run();
