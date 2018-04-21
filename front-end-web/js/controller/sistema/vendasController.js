app.controller('vendasController', function ($scope, $http, $route, $routeParams, $location) {

        $scope.sucessMessage;
        $scope.erroMessage;
        $scope.isEditing = false;
        $scope.usuario = usuario;
        $scope.venda = []
        $scope.listarVendasPublicadas = [];
        $scope.listarVendasCompradas = [];

        $http.get('http://localhost/pointstorePA3/index.php/venda/gerenciarVendas/'+$scope.usuario.id, {})
        .success(function(retorno){
            $scope.listarVendasPublicadas = retorno;
        }).error(function(){
            //alert("Erro ao cerregar");
        });

        $http.get('http://localhost/pointstorePA3/index.php/venda/historicoVendas/'+$scope.usuario.id, {})
        .success(function(retorno){
            $scope.listarVendasCompradas = retorno;
        }).error(function(){
            //alert("Erro ao cerregar");
        });

        $scope.atualizarVendaCadastrada = function(valor, venda_id){
            var vendaDAO = new Object();
            vendaDAO.valor = valor;
            vendaDAO.venda_id = venda_id;

            var minha_venda = angular.toJson(vendaDAO);

            $http.put('http://localhost/pointstorePA3/index.php/venda/gerenciarVendas', minha_venda)
                .success(function (retorno) {
                    alert(retorno.mensagem);
                    window.location.reload();
                }).error(function () {
                    alert("Venda não foi atualizada!");
            });

        }

        $scope.excluirVendaCadastradaId = function(venda_id){

            $http.delete('http://localhost/pointstorePA3/index.php/venda/excluirVendaId/'+venda_id, {})
                .success(function (retorno) {
                    alert(retorno.mensagem);
                    window.location.reload();
                }).error(function () {
                    alert("Venda não foi excluida!");
            });

        }

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
                    window.location.reload();
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
                window.location.reload();
            }).error(function(){
            alert("erro no cadastro do ponto");
        });
    }
    }
);