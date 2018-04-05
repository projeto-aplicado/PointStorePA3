app.controller('perfilController', function($scope, $http) {

    $scope.lista = {};
    $scope.listaPontos = [];
    $scope.usuario = usuario;


    $(document).ready(function(){
        $('#credito').attr("disabled", true);
        $('#login').attr("disabled", true);
    });
    

    $scope.atualizarPerfilUsuario = function(){

        var usuario = angular.toJson($scope.usuario);

        if (document.formAtualizarPerfil.nome.value == "" || document.formAtualizarPerfil.sobrenome.value == "" ||
            document.formAtualizarPerfil.cpf.value == "" || document.formAtualizarPerfil.email.value == "" ||
            document.formAtualizarPerfil.email.value == "" || document.formAtualizarPerfil.login.value == ""){
            alert("preencha os campos que estiverem vazio!");
            document.formAtualizarPerfil.cpf.focus();
            $(".formPadraoCpf").addClass("formPadraoFocus");
            return false;
        }else{
            $http.put('http://localhost/pointstorePA3/index.php/login/usuario', usuario)
            .success(function(retorno){
                localStorage.usuario = JSON.stringify($scope.usuario); 
                alert("perfil atualizado com sucesso!");
                window.location.reload();
            }).error(function(){
                alert("erro em atualizar seu perfil, favor, tente novamente mais tarde");
            });
        }

        

    }

});