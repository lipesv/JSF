package financeiro.endereco;

import org.hibernate.Session;

public class EnderecoDAOHibernate implements EnderecoDAO {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Endereco endereco) {
		this.session.saveOrUpdate(endereco);
	}

	@Override
	public void excluir(Endereco endereco) {
		this.session.delete(endereco);
	}

	@Override
	public Endereco carregar(Integer fornecedor) {
		return (Endereco) this.session.get(Endereco.class, fornecedor);
	}

}
