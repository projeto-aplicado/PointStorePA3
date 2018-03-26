app.controller('vendasController', function ($scope, $http, $route, $routeParams, $location) {

        $scope.sucessMessage;
        $scope.erroMessage;
        $scope.isEditing = false;
        $scope.usuario = usuario;
        $scope.venda = []

        $scope.cadastrarVenda = function () {

            var vendaDAO = new Object();
            vendaDAO.tipoDePontos = $scope.venda.tipoDePontos;
            vendaDAO.quantidade = $scope.venda.quantidade;
            vendaDAO.valor = $scope.venda.valor;
            vendaDAO.id_usuario = $scope.usuario.id;

            var venda = angular.toJson(vendaDAO);

            if(!$scope.venda.tipoDePontos){
                alert('Por favor, escolha o tipo de ponto');
                return false;
            }

            if(!$scope.venda.quantidade){
                alert('Por favor, digite a quantidade');
                return false;
            }

            if(!$scope.venda.valor){
                alert('Por favor, digite o valor!');
                return false;
            }

        }

});