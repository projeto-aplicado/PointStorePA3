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
            document.formAtualizarPerfil.senha.value == ""){
            alert("preencha os campos que estiverem vazio!");
            document.formAtualizarPerfil.cpf.focus();
            $(".formPadraoCpf").addClass("formPadraoFocus");
            return false;
        }else{
            VerificaCPF($scope.usuario.cpf);
        }

        function VerificaCPF (cpf) {
            if (vercpf(document.formAtualizarPerfil.cpf.value)){
                $http.put('http://localhost/pointstorePA3/index.php/login/usuario', usuario)
                .success(function(retorno){
                    localStorage.usuario = JSON.stringify($scope.usuario); 
                    alert("perfil atualizado com sucesso!");
                    window.location.reload();
                }).error(function(){
                    alert("erro em atualizar seu perfil, favor, tente novamente mais tarde");
                });
            }else{
                errors = "1";
                if (errors) alert('CPF NÃO VÁLIDO');
                document.retorno = (errors == '');
            }
        }

        function vercpf (cpf){
            if (cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222" || 
                cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666" || 
                cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999") return false;
            add = 0;
            for (i=0; i < 9; i ++)
            add += parseInt(cpf.charAt(i)) * (10 - i);
            rev = 11 - (add % 11);
            if (rev == 10 || rev == 11)
            rev = 0;
            if (rev != parseInt(cpf.charAt(9)))
            return false;
            add = 0;
            for (i = 0; i < 10; i ++)
            add += parseInt(cpf.charAt(i)) * (11 - i);
            rev = 11 - (add % 11);
            if (rev == 10 || rev == 11)
            rev = 0;
            if (rev != parseInt(cpf.charAt(10)))
            return false;
            alert('O CPF informado é valiado');
            return true;
        }
        

    }

});