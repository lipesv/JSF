package financeiro.fornecedor;

import java.util.List;

public interface FornecedorDAO {
	public void salvar(Fornecedor fornecedor);
	public void excluir(Fornecedor fornecedor);
	public Fornecedor carregar(Integer fornecedor);
	public List<Fornecedor> listar();
	public List<Fornecedor> listar(String fornecedor);
}
