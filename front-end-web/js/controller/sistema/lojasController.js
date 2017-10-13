app.controller('lojasController', function($scope, $http, $route, $routeParams, $location) {


	alert('teste fora da funcao');
	$scope.lista = {};
	$scope.listarPontos = [
    	{tipoDoPonto: 'TAM', quantidadePonto: '0'}
    	,{tipoDoPonto: 'GOL', quantidadePonto: '0'}
    	,{tipoDoPonto: 'DOTS', quantidadePonto: '0'}
    ];


	/*$scope.cadastrarTiposDePontos = function(tipoDePontos, quantidadePonto){
        var meusPontosDAO = new Object();
        var tipoDePontosDAO = new Loja();

        meusPontosDAO.quantidadePonto = quantidadePonto;
        tipoDePontosDAO.tipoDePontos = tipoDePontos;
        meusPontosDAO.loja = tipoDePontosDAO;

        var tipoDePontosUsuario = angular.toJson(meusPontosDAO);
        $http.post('http://localhost:8080/PointStoreWeb/rest/meuspontos', tipoDePontosUsuario)
        .success(function(retorno){
            alert(retorno);
        }).error(function(){
            erroMessage = "O ponto "+tipoDePontosDAO.tipoDePontos+" não foi salvo!";
        });
    }*/

});