package financeiro.endereco;

import financeiro.util.DAOFactory;

public class EnderecoRN {

	private EnderecoDAO enderecoDAO;

	public EnderecoRN() {
		this.enderecoDAO = DAOFactory.criarEnderecoDAO();
	}

	public void salvar(Endereco endereco) {
		this.enderecoDAO.salvar(endereco);
	}

	public void excluir(Endereco endereco) {
		this.enderecoDAO.excluir(endereco);
	}

	public Endereco carregar(Integer fornecedor) {
		return this.enderecoDAO.carregar(fornecedor);
	}

}