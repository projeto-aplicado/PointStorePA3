app.controller('comprasController', function ($scope, $http, $route, $routeParams, $location) {

        $scope.sucessMessage;
        $scope.erroMessage;
        $scope.isEditing = false;
        $scope.listaCompras = [];
        $scope.usuario = usuario;
        $scope.venda = []
        $scope.compra = []

        $http.get('http://localhost/pointstorePA3/index.php/compra/'+$scope.usuario.id,{})
        .success(function(retorno){
            $scope.listaCompras  = retorno;
        }).error(function(){
            //alert("Erro ao cerregar tabela");
        });

        $http.get('http://localhost/pointstorePA3/index.php/venda/'+$routeParams.id,{})
        .success(function(retorno){
            $scope.venda  = retorno;
        }).error(function(){
            //alert("Erro ao cerregar");
        });

        $scope.realizarCompra = function () {

            var compraDAO = new Object();
            compraDAO.formaPagamento = $scope.compra.formaPagamento;
            compraDAO.id_usuario = $scope.usuario.id;
            compraDAO.venda_id = $scope.venda.venda_id;

            var compra = angular.toJson(compraDAO);

            if(!$scope.compra.formaPagamento){
                alert('Por favor, escolha a forma de pagamento');
                return false;
            }

            $http.put('http://localhost/pointstorePA3/index.php/compra', compra)
                .success(function (retorno) {
                    alert(retorno.mensagem);
                    window.location.href = 'home.html';
                }).error(function () {
                    alert("Compra não foi realizada!");
            });
        }

    }
);