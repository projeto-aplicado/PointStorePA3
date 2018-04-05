app.controller('homeController', function ($scope, $http, $route, $routeParams, $location) {

        $scope.sucessMessage;
        $scope.erroMessage;
        $scope.isEditing = false;
        $scope.listarHome = [];
        $scope.usuario = usuario;

        $http.get('http://localhost/pointstorePA3/index.php/venda/',{})
        .success(function(retorno){
            $scope.listarHome = retorno;
        }).error(function(){
            alert("Erro ao cerregar");
        });

    }
);