package financeiro.contato;

import org.hibernate.Session;

public class ContatoDAOHibernate implements ContatoDAO {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Contato contato) {
		this.session.saveOrUpdate(contato);
	}

	@Override
	public void excluir(Contato contato) {
		this.session.delete(contato);
	}

	@Override
	public Contato carregar(Integer fornecedor) {
		return (Contato) this.session.get(Contato.class, fornecedor);
	}

}
