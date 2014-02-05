package financeiro.util;

public interface IValidacaoBean {

	boolean isValid();
	void setValid(boolean value);

	String getMessage();
	void setMessage(String value);

	String exibirMensagemValidacao(String clientId);
}