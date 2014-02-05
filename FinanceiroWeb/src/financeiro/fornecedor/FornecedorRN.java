package financeiro.fornecedor;

import java.util.List;

import financeiro.util.DAOFactory;

public class FornecedorRN {

	private FornecedorDAO fornecedorDAO;

	public FornecedorRN() {
		this.fornecedorDAO = DAOFactory.criarFornecedorDAO();
	}

	public void salvar(Fornecedor fornecedor) {
		this.fornecedorDAO.salvar(fornecedor);
	}

	public void excluir(Fornecedor fornecedor) {
		this.fornecedorDAO.excluir(fornecedor);
	}

	public Fornecedor carregar(Integer fornecedor) {
		return this.fornecedorDAO.carregar(fornecedor);
	}

	public List<Fornecedor> listar() {
		return this.fornecedorDAO.listar();
	}

	public List<Fornecedor> listar(String fornecedor) {
		return this.fornecedorDAO.listar(fornecedor);
	}

}