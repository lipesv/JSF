package financeiro.cidade;

import java.util.List;

import financeiro.estado.Estado;

public interface CidadeDAO {
	
	public Cidade carregar(Integer cidade);
	public List<Cidade> listar(Estado estado);
}
