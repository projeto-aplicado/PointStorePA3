app.controller('loginController', function ($scope, $http, $route, $routeParams, $location) {

	$scope.logarUsuario = function(login, senha) {
		var logarDAO = new Object();
		logarDAO.login = login;
		logarDAO.senha = senha;

		var logar = angular.toJson(logarDAO);
        $http.post('http://localhost/pointstorePA3/index.php/login', logar)
        .success(function(retorno){
            if (retorno != undefined && retorno != null && retorno != "") {
            	localStorage.setItem("usuario", JSON.stringify(retorno));
            	window.location.href = 'home.html';
            }else{
            	alert("Usuário ou senha inválidos!");
            }

        }).error(function(){
            $scope.erroMessage = "Usuario "+logarDAO.login+" não foi logado!";
        });

			
	}

});