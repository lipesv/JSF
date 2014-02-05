package financeiro.contato;

import financeiro.util.DAOFactory;

public class ContatoRN {

	private ContatoDAO contatoDAO;

	public ContatoRN() {
		this.contatoDAO = DAOFactory.criarContatoDAO();
	}

	public void salvar(Contato contato) {
		this.contatoDAO.salvar(contato);

	}

	public void excluir(Contato contato) {
		this.contatoDAO.excluir(contato);

	}

	public Contato carregar(Integer fornecedor) {
		return this.contatoDAO.carregar(fornecedor);
	}

}
