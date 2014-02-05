package financeiro.contato;

public interface ContatoDAO {
	
	public void salvar(Contato contato);
	public void excluir(Contato contato);
	public Contato carregar(Integer fornecedor);
}
