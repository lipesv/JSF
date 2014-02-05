package financeiro.cidade;

import java.util.List;

import financeiro.estado.Estado;
import financeiro.util.DAOFactory;

public class CidadeRN {

	private CidadeDAO cidadeDAO;

	public CidadeRN() {
		this.cidadeDAO = DAOFactory.criarCidadeDAO();
	}

	public Cidade carregar(Integer cidade) {
		return this.cidadeDAO.carregar(cidade);
	}

	public List<Cidade> listar(Estado estado) {
		return this.cidadeDAO.listar(estado);
	}

}