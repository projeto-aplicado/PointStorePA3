
function validarMeusPontos() {
	if (document.meusPontos.tipoDePontos.value == ""){
		alert("campo tipo de pontos está vazio, favor preencher");
		document.meusPontos.tipoDePontos.focus();
		return false;
	}
	if (document.meusPontos.quantidadePonto.value == ""){
		alert("campo quantidade de ponto vazio, favor preencher");
		document.meusPontos.quantidadePonto.focus();
		return false;
	}
}