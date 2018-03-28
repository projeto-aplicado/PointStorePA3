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

            $http.post('http://localhost/pointstorePA3/index.php/venda', venda)
                .success(function (retorno) {
                    alert(retorno.mensagem);
                }).error(function () {
                    alert("Venda não foi salva!");
            });
        }

    $scope.cadastrarTiposDePontos = function(){
        var meusPontosDAO = new Object();
        meusPontosDAO.tipoDePontos = $scope.pontos.tipoDePontos;
        meusPontosDAO.quantidadePonto = $scope.pontos.quantidadePonto;
        meusPontosDAO.id = $scope.pontos.id;
        var tipoDePontosUsuario = angular.toJson(meusPontosDAO);

        if(!$scope.pontos.tipoDePontos){
            alert('Por favor, escolha o tipo de ponto!');
            return false;
        }

        if(!$scope.pontos.numeroCartao){
            alert('Por favor, digite a quantidade de ponto');
            return false;
        }

        $http.put('http://localhost/pointstorePA3/index.php/meuspontos/atualizar', tipoDePontosUsuario)
            .success(function(retorno){
                alert("ponto cadastrado com sucesso!");
            }).error(function(){
            alert("erro no cadastro do ponto");
        });
    }
    }
);