$(document).ready(function() {
	// setUpUiWidgetClass();
	setUpObjectsAndMask();
});

function setUpObjectsAndMask() {

	$("input[id$='cep']").setMask({
		autoTab : false
	});
	$("input[id$='cnpj']").setMask({
		autoTab : false
	});
	$("input[id$='cpf']").setMask({
		autoTab : false
	});
	$("input[id$='telefoneEmpresa']").setMask({
		autoTab : false
	});
	$("input[id$='faxEmpresa']").setMask({
		autoTab : false
	});
	$("input[id$='telefoneContato']").setMask({
		autoTab : false
	});

	$("select[id*='estado'] option:first").attr("value", "");
}

function validaComboPai() {
	
	alert('a');
	
	if ($("select[id*='estado'] option:selected").val() == "") {
		$("select[id*='estado'] option:selected").attr("value", "0");
	}
}

function handleRequest(xhr, status, args) {

	// <![CDATA[

	var validacao = args.validationFailed != undefined;
	var erro = (args.erroProcessamento != undefined && args.erroProcessamento);

	if (!validacao && !erro)
		PF('dlgFornecedores').hide();

	// ]]>
}