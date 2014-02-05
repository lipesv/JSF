package financeiro.util;

import financeiro.categoria.CategoriaDAO;
import financeiro.categoria.CategoriaDAOHibernate;
import financeiro.cheque.ChequeDAO;
import financeiro.cheque.ChequeDAOHibernate;
import financeiro.cidade.CidadeDAO;
import financeiro.cidade.CidadeDAOHibernate;
import financeiro.conta.ContaDAO;
import financeiro.conta.ContaDAOHibernate;
import financeiro.contato.ContatoDAO;
import financeiro.contato.ContatoDAOHibernate;
import financeiro.endereco.EnderecoDAO;
import financeiro.endereco.EnderecoDAOHibernate;
import financeiro.estado.EstadoDAO;
import financeiro.estado.EstadoDAOHibernate;
import financeiro.fornecedor.FornecedorDAO;
import financeiro.fornecedor.FornecedorDAOHibernate;
import financeiro.lancamento.LancamentoDAO;
import financeiro.lancamento.LancamentoDAOHibernate;
import financeiro.usuario.UsuarioDAO;
import financeiro.usuario.UsuarioDAOHibernate;

public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {

		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();

		usuarioDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());

		return usuarioDAO;
	}

	public static ContaDAO criarContaDAO() {

		ContaDAOHibernate contaDAO = new ContaDAOHibernate();

		contaDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());

		return contaDAO;
	}

	public static CategoriaDAO criarCategoriaDAO() {

		CategoriaDAOHibernate categoriaDAO = new CategoriaDAOHibernate();

		categoriaDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());

		return categoriaDAO;
	}

	public static LancamentoDAO criarLancamentoDAO() {

		LancamentoDAOHibernate lancamentoDAO = new LancamentoDAOHibernate();

		lancamentoDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());

		return lancamentoDAO;
	}

	public static FornecedorDAO criarFornecedorDAO() {

		FornecedorDAOHibernate fornecedorDAO = new FornecedorDAOHibernate();

		fornecedorDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());

		return fornecedorDAO;
	}

	public static CidadeDAO criarCidadeDAO() {

		CidadeDAOHibernate cidadeDAO = new CidadeDAOHibernate();

		cidadeDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());

		return cidadeDAO;
	}

	public static EstadoDAO criarEstadoDAO() {

		EstadoDAOHibernate estadoDAO = new EstadoDAOHibernate();

		estadoDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());

		return estadoDAO;
	}

	public static ContatoDAO criarContatoDAO() {

		ContatoDAOHibernate contatoDAO = new ContatoDAOHibernate();

		contatoDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());

		return contatoDAO;

	}

	public static EnderecoDAO criarEnderecoDAO() {

		EnderecoDAOHibernate enderecoDAO = new EnderecoDAOHibernate();

		enderecoDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());

		return enderecoDAO;
	}

	public static ChequeDAO criarChequeDAO() {

		ChequeDAOHibernate chequeDAO = new ChequeDAOHibernate();

		chequeDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());

		return chequeDAO;
	}

}