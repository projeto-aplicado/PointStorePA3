app.controller('lojasController', function($scope, $http, $route, $routeParams, $location) {

	$scope.lista = {};
	$scope.listarPontos = [];
	$scope.pontos;
    $scope.usuario = usuario;

    $http.get('http://localhost/pointstorePA3/index.php/meuspontos/'+$scope.usuario.id,{})
        .success(function(retorno){
          $scope.listarPontos = retorno;
        }).error(function(){
        alert("Erro ao cerregar tabela");
    });

    $scope.cadastrarTiposDePontos = function(){
        var meusPontosDAO = new Object();
        meusPontosDAO.tipoDePontos = $scope.pontos.tipoDePontos;
        meusPontosDAO.quantidadePonto = $scope.pontos.quantidadePonto;
        meusPontosDAO.id = $scope.pontos.id;
        var tipoDePontosUsuario = angular.toJson(meusPontosDAO);

        if(document.meusPontos.tipoDePonto.value == ""){
            alert('Por favor, escolha o tipo de ponto');
            return false;
        }

        if(document.meusPontos.quantidadePonto.value == ""){
            alert('Por favor, escolha a quantidade de pontos');
            document.meusPontos.quantidadePonto.focus();
            $(".formPadrao").addClass("formPadraoFocus");
            return false;
        }

        $http.put('http://localhost/pointstorePA3/index.php/meuspontos/atualizar', tipoDePontosUsuario)
            .success(function(retorno){
                alert("ponto cadastrado com sucesso!");
                window.location.reload();
            }).error(function(){
            alert("erro no cadastro do ponto");
        });
    }

    $scope.pesquisarPontos = function(tipoDePonto){
        $http.get('http://localhost/pointstorePA3/index.php/meuspontos/'+$scope.usuario.id,{'tipoDePonto':tipoDePonto})
            .success(function(retorno){
                $(retorno).each(function(k,v){
                    if($("#meus-pontos_tipoDePonto").val() == v.tipoDePonto){
                       $scope.pontos = v;
                    }
                });
            }).error(function(){
                alert("erro ao listar pontos, por favor tente novamente!");
        });
    }

});