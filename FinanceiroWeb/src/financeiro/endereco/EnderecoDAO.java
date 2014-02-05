package financeiro.endereco;

public interface EnderecoDAO {
	public void salvar(Endereco endereco);
	public void excluir(Endereco endereco);
	public Endereco carregar(Integer fornecedor);
}
