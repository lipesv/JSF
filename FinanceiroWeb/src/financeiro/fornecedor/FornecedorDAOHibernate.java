package financeiro.fornecedor;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class FornecedorDAOHibernate implements FornecedorDAO {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Fornecedor fornecedor) {
		this.session.saveOrUpdate(fornecedor);
	}

	@Override
	public void excluir(Fornecedor fornecedor) {
		this.session.delete(fornecedor);
	}

	@Override
	public Fornecedor carregar(Integer fornecedor) {
		return (Fornecedor) this.session.get(Fornecedor.class, fornecedor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> listar() {
		Criteria criteria = this.session.createCriteria(Fornecedor.class);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> listar(String fornecedor) {

		Criteria criteria = this.session.createCriteria(Fornecedor.class);

		if (fornecedor != null) {
			criteria.add(Restrictions.ilike("nomeFantasia", fornecedor,
					MatchMode.ANYWHERE));
			// like("nomeFantasia", fornecedor));
		}

		return criteria.list();
	}
}