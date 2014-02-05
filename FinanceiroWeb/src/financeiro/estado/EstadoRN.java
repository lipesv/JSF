package financeiro.estado;

import java.util.List;

import financeiro.util.DAOFactory;

public class EstadoRN {

	private EstadoDAO estadoDAO;

	public EstadoRN() {
		this.estadoDAO = DAOFactory.criarEstadoDAO();
	}

	public Estado carregar(Integer estado) {
		return this.estadoDAO.carregar(estado);
	}

	public List<Estado> listar() {
		return this.estadoDAO.listar();
	}

}
