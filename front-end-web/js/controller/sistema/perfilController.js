app.controller('perfilController', function($scope, $http) {

    $scope.lista = {};
    $scope.listaPontos = [];
    $scope.usuario = usuario;
    
    

	$scope.atualizarPerfilUsuario = function(){

        var usuario = angular.toJson($scope.usuario);

        $http.put('http://localhost:8080/PointStoreWeb/rest/usuario', usuario)
        .success(function(retorno){

            localStorage.usuario = JSON.stringify($scope.usuario);    

            alert(retorno);
        }).error(function(){
            erroMessage = "Usuario "+usuarioDAO.nome+" não foi salvo!";
        });

    }

});