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


        $scope.permitirComprar = function(id_usuario_vendedor, venda_id){
            if (usuario.id == id_usuario_vendedor) {
                alert("você não pode comprar seu próprio anúncio");
            }else{
                window.location.href = "#/finalizar_compra/"+venda_id;
            }
        }

    }
);