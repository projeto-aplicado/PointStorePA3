app.controller('perfilController', function($scope, $http) {

    $scope.lista = {};
    $scope.listaPontos = [];
    $scope.usuario = usuario;
    
    

	$scope.atualizarPerfilUsuario = function(){

        var usuario = angular.toJson($scope.usuario);

        $http.put('http://localhost/pointstorePA3/index.php/login/usuario', usuario)
        .success(function(retorno){

            localStorage.usuario = JSON.stringify($scope.usuario);    

            alert("perfil atualizado com sucesso!");
        }).error(function(){
            erroMessage = "Usuario "+usuarioDAO.nome+" não foi salvo!";
        });

    }

});