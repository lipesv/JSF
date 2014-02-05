package financeiro.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import financeiro.categoria.Categoria;
import financeiro.categoria.CategoriaRN;
import financeiro.web.util.ContextoUtil;

@ManagedBean(name = "categoriaBean")
@RequestScoped
public class CategoriaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 234448016404150007L;
	
	private TreeNode categoriasTree;
	private Categoria editada = new Categoria();
	private List<SelectItem> categoriasSelect;
	private boolean mostraEdicao;

	public CategoriaBean() {
		this.mostraEdicao = false;
	}

	public void novo() {

		Categoria pai = null;

		if (this.editada.getCodigo() != null) {
			CategoriaRN categoriaRN = new CategoriaRN();
			pai = categoriaRN.carregar(this.editada.getCodigo());
			this.mostraEdicao = true;
		} else {

			CategoriaRN categoriaRN = new CategoriaRN();

			if (categoriaRN.listar(
					ContextoUtil.getContextoBean().getUsuarioLogado()).size() == 0) {
				categoriaRN.salvarEstruturaPadrao(ContextoUtil
						.getContextoBean().getUsuarioLogado());
				this.mostraEdicao = false;
			} else {
				this.mostraEdicao = true;
			}
		}

		this.editada = new Categoria();
		this.editada.setPai(pai);

	}

	public void selecionar(NodeSelectEvent event) {

		this.editada = (Categoria) event.getTreeNode().getData();
		Categoria pai = this.editada.getPai();

		if (this.editada != null && pai != null && pai.getCodigo() != null) {
			this.mostraEdicao = true;
		} else {
			this.mostraEdicao = false;
		}
	}

	public void salvar() {

		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		CategoriaRN categoriaRN = new CategoriaRN();
		this.editada.setUsuario(contextoBean.getUsuarioLogado());

		categoriaRN.salvar(this.editada);

		this.editada = null;
		this.mostraEdicao = false;
		this.categoriasTree = null;
		this.categoriasSelect = null;
	}

	public void excluir() {

		CategoriaRN categoriaRN = new CategoriaRN();

		if (this.editada.getCodigo() != null) {
			categoriaRN.excluir(this.editada);
		}

		this.editada = null;
		this.mostraEdicao = false;
		this.categoriasTree = null;
		this.categoriasSelect = null;
	}

	public Categoria getEditada() {
		return editada;
	}

	public void setEditada(Categoria editada) {
		this.editada = editada;
	}

	public boolean isMostraEdicao() {
		return mostraEdicao;
	}

	public void setMostraEdicao(boolean mostraEdicao) {
		this.mostraEdicao = mostraEdicao;
	}

	public TreeNode getCategoriasTree() {

		if (this.categoriasTree == null) {

			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			CategoriaRN categoriaRN = new CategoriaRN();

			List<Categoria> categorias = categoriaRN.listar(contextoBean
					.getUsuarioLogado());

			this.categoriasTree = new DefaultTreeNode(null, null);
			this.montaDadosTree(this.categoriasTree, categorias);
		}
		return this.categoriasTree;
	}

	private void montaDadosTree(TreeNode pai, List<Categoria> lista) {
		if (lista != null && lista.size() > 0) {
			TreeNode filho = null;
			for (Categoria categoria : lista) {
				filho = new DefaultTreeNode(categoria, pai);
				this.montaDadosTree(filho, categoria.getFilhos());
			}
		}
	}

	public List<SelectItem> getCategoriasSelect() {

		if (this.categoriasSelect == null) {

			this.categoriasSelect = new ArrayList<SelectItem>();
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			CategoriaRN categoriaRN = new CategoriaRN();

			List<Categoria> categorias = categoriaRN.listar(contextoBean
					.getUsuarioLogado());

			this.montaDadosSelect(this.categoriasSelect, categorias, "");
		}
		return categoriasSelect;
	}

	private void montaDadosSelect(List<SelectItem> select,
			List<Categoria> categorias, String prefixo) {

		SelectItem item = null;

		if (categorias != null) {

			for (Categoria categoria : categorias) {

				item = new SelectItem(categoria, prefixo
						+ categoria.getDescricao());

				item.setEscape(false);

				select.add(item);

				this.montaDadosSelect(select, categoria.getFilhos(), prefixo
						+ "&nbsp;&nbsp;");
			}
		}

	}

}
