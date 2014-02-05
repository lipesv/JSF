package financeiro.estado;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

public class EstadoDAOHibernate implements EstadoDAO {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public Estado carregar(Integer estado) {
		return (Estado) this.session.get(Estado.class, estado);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Estado> listar() {
		Criteria criteria = this.session.createCriteria(Estado.class);
		return (List<Estado>) criteria.list();
	}

}