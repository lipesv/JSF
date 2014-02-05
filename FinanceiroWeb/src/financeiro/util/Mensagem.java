package financeiro.util;

public enum Mensagem {

	Sucesso("Operação realizada com sucesso!"), 
	Erro("Ocorreu um erro no processamento.");

	private String tipo;

	private Mensagem(String tipoMensagem) {
		this.tipo = tipoMensagem;
	}

	public String getTipo() {
		return tipo;
	}

};
