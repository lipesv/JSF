package financeiro.cidade;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import financeiro.estado.Estado;

public class CidadeDAOHibernate implements CidadeDAO {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public Cidade carregar(Integer cidade) {
		return (Cidade) this.session.get(Cidade.class, cidade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> listar(Estado estado) {
		Criteria criteria = this.session.createCriteria(Cidade.class);
		criteria.add(Restrictions.eq("estado", estado));
		return (List<Cidade>) criteria.list();
	}

}
