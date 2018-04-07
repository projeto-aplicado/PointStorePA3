app.controller('usuarioController', function($scope, $http, $route, $routeParams, $location) {

    $scope.cadastrarUsuario = function (nome, sobrenome, email, login, senha) {
        
        var usuarioDAO = new Object();
        usuarioDAO.nome = nome;
        usuarioDAO.sobrenome = sobrenome;
        usuarioDAO.email = email;
        usuarioDAO.login = login;
        usuarioDAO.senha = senha;

        if(document.formCadastrarUsuario.nome.value == "" || document.formCadastrarUsuario.sobrenome.value == "" ||
            document.formCadastrarUsuario.email.value == "" || document.formCadastrarUsuario.login.value == "" ||
            document.formCadastrarUsuario.senha.value == ""){
            alert("preencha os campos que estiverem vazio!");
        }else{
            var usuario = angular.toJson(usuarioDAO);
            $http.post('http://localhost/pointstorePA3/index.php/usuario', usuario)
            .success(function(retorno){
                alert("dados cadastrado com sucesso!");
                window.location.href = "http://localhost/pointstorePA3/pointstore/index.html#/view/login/tela_login";
            }).error(function(){
                 alert("erro ao cadastrar o usuario, favor, tente novamente mais tarde");
            });
        }
    }


    $scope.recuperarSenhaUsuario = function(email, senha, senha2){
        var usuarioDAO = new Object();
        usuarioDAO.email = email;
        usuarioDAO.senha = senha;

        if (document.formAlterarSenha.senha.value == document.formAlterarSenha.senha2.value) {
            if(document.formAlterarSenha.email.value == "" || document.formAlterarSenha.senha.value == ""){
            alert("preencha os campos que estiverem vazio!");
            }else{
                var usuario = angular.toJson(usuarioDAO);
                $http.put('http://localhost/pointstorePA3/index.php/usuario/senha', usuario)
                .success(function(retorno){
                    alert("senha do usuario atualizada com sucesso!");
                    window.location.href = "http://localhost/pointstorePA3/pointstore/index.html#/view/login/tela_login";
                }).error(function(){
                    alert("erro ao atualizar a senha!");
                });
            }
        }else{
            alert("as senhas sao diferente");
        }

        
    }

});