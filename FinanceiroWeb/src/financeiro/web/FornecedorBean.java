package financeiro.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

import financeiro.cidade.Cidade;
import financeiro.cidade.CidadeRN;
import financeiro.contato.Contato;
import financeiro.contato.ContatoRN;
import financeiro.endereco.Endereco;
import financeiro.endereco.EnderecoRN;
import financeiro.estado.Estado;
import financeiro.estado.EstadoRN;
import financeiro.fornecedor.Fornecedor;
import financeiro.fornecedor.FornecedorRN;
import financeiro.util.AplicacaoBean;

@ManagedBean(name = "fornecedorBean")
@ViewScoped
public class FornecedorBean extends AplicacaoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2327966243203147996L;

	private Fornecedor fornecedor = new Fornecedor();
	private Endereco endereco;
	private Contato contato;

	private List<Estado> estados;
	private List<Cidade> cidades;

	private List<Fornecedor> lista;

	public FornecedorBean() {
		montaObjetos();
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Estado> getEstados() {

		if (estados == null) {
			EstadoRN estadoRN = new EstadoRN();
			estados = estadoRN.listar();
		}

		return estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public List<Fornecedor> getLista() {

		if (lista == null) {

			FornecedorRN fornecedorRN = new FornecedorRN();
			lista = fornecedorRN.listar();
		}

		return lista;
	}

	public void listaCidadesporEstado() {

		if (this.endereco.getEstado().getCodigo() > 0) {
			CidadeRN cidadeRN = new CidadeRN();
			this.cidades = cidadeRN.listar(this.endereco.getEstado());
		} else {
			this.cidades = null;
		}
	}

	public List<Fornecedor> listaFornecedoresporNome(String query) {

		if (query != null) {
			FornecedorRN fornecedorRN = new FornecedorRN();
			lista = fornecedorRN.listar(query);
		}

		return lista;
	}

	public void montaObjetos() {

		endereco = new Endereco();
		endereco.setEstado(new Estado());
		endereco.setCidade(new Cidade());

		contato = new Contato();

		this.fornecedor = new Fornecedor();
		fornecedor.setEndereco(endereco);
		fornecedor.setContato(contato);
	}

	public void novo() {

		montaObjetos();
		// OpenDialog();
	}

	public void salvar() {

		FornecedorRN fornecedorRN = new FornecedorRN();
		EnderecoRN enderecoRN = new EnderecoRN();
		ContatoRN contatoRN = new ContatoRN();

		try {

			this.fornecedor.setDddTelefone(this.fornecedor.getTelefone()
					.substring(1, 3).trim());
			this.fornecedor.setTelefone(this.fornecedor.getTelefone()
					.substring(4).replace("-", "").trim());

			if (!this.fornecedor.getFax().equals("")) {
				this.fornecedor.setDddFax(this.fornecedor.getFax()
						.substring(1, 3).trim());
				this.fornecedor.setFax(this.fornecedor.getFax().substring(4)
						.replace("-", "").trim());
			}

			if (!this.contato.getTelefone().equals("")) {
				this.contato.setDddTelefone(this.contato.getTelefone()
						.substring(1, 3).trim());
				this.contato.setTelefone(this.contato.getTelefone()
						.substring(4).replace("-", "").trim());
			}

			this.endereco.setFornecedor(this.fornecedor);
			this.contato.setFornecedor(this.fornecedor);
			
			fornecedorRN.salvar(this.fornecedor);
			enderecoRN.salvar(this.endereco);
			contatoRN.salvar(this.contato);

			setValid(true);

		} catch (Exception e) {
			setValid(false);
			definirMensagem(e);
		} finally {
			CloseDialog(this);
			this.lista = null;
		}

	}

	public void editar() {

		this.fornecedor.setTelefone(this.fornecedor.getDddTelefone()
				+ this.fornecedor.getTelefone());

		this.endereco = this.fornecedor.getEndereco();
		listaCidadesporEstado();

		this.contato = this.fornecedor.getContato();
		this.contato.setTelefone(this.fornecedor.getContato().getDddTelefone()
				+ this.fornecedor.getContato().getTelefone());

	}

	public void excluir() {
		FornecedorRN fornecedorRN = new FornecedorRN();
		fornecedorRN.excluir(this.fornecedor);
		montaObjetos();
		this.lista = null;
	}

	@Override
	public void handleClose(CloseEvent event) {

		RequestContext context = RequestContext.getCurrentInstance();
		context.reset("cadastro");

		montaObjetos();

		this.lista = null;
		this.cidades = null;

	}

}