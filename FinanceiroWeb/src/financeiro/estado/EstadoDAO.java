package financeiro.estado;

import java.util.List;

public interface EstadoDAO {

	public Estado carregar(Integer estado);
	public List<Estado> listar();
}
