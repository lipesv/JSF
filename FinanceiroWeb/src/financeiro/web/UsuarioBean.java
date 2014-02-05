package financeiro.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.util.DigestUtils;

import financeiro.conta.Conta;
import financeiro.conta.ContaRN;
import financeiro.usuario.Usuario;
import financeiro.usuario.UsuarioRN;
import financeiro.web.util.ContextoUtil;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3219402449484827657L;

	private Usuario usuario = new Usuario();

	private String novaSenha;
	private String confirmarSenha;

	private List<Usuario> lista;
	private String destinoSalvar;
	private Conta conta = new Conta();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String alterarSenha() {

		this.usuario = new Usuario();
		this.usuario.setAtivo(true);

		return "alteraSenha";
	}

	public String redefinirSenha() {

		FacesContext context = FacesContext.getCurrentInstance();
		String senhaAtual = this.usuario.getSenha();
		String novaSenha = this.getNovaSenha();

		if (!DigestUtils.md5DigestAsHex(senhaAtual.getBytes()).equals(
				ContextoUtil.getContextoBean().getUsuarioLogado().getSenha())) {

			FacesMessage facesMessage = new FacesMessage(
					"A senha atual não confere.");
			context.addMessage(null, facesMessage);
			return null;

		}

		if (!novaSenha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage(
					"A nova senha não foi confirmada corretamente.");
			context.addMessage(null, facesMessage);
			return null;
		}

		UsuarioRN usuarioRN = new UsuarioRN();

		this.usuario = usuarioRN.buscarPorLogin(ContextoUtil.getContextoBean()
				.getUsuarioLogado().getLogin());

		this.usuario.setSenha(DigestUtils.md5DigestAsHex(novaSenha.getBytes()));

		usuarioRN.salvar(this.usuario);
		ContextoUtil.getContextoBean().getUsuarioLogado().setSenha(novaSenha);

		return this.destinoSalvar;
	}

	public String novo() {

		this.destinoSalvar = "usuarioSucesso";

		this.usuario = new Usuario();
		this.usuario.setAtivo(true);

		return "usuario";
	}

	public String editar() {
		this.confirmarSenha = this.usuario.getSenha();
		return "/publico/usuario";
	}

	public String salvar() {

		FacesContext context = FacesContext.getCurrentInstance();
		String senhaCripto = "";

		String senha = this.usuario.getSenha();

		if (ContextoUtil.getContextoBean().getUsuarioLogado() == null) {
			if (!senha.equals(this.confirmarSenha)) {
				FacesMessage facesMessage = new FacesMessage(
						"A senha não foi confirmada corretamente");
				context.addMessage(null, facesMessage);
				return null;
			}

			senhaCripto = DigestUtils.md5DigestAsHex(this.usuario.getSenha()
					.getBytes());
		} else {
			senhaCripto = this.usuario.getSenha();
		}

		UsuarioRN usuarioRN = new UsuarioRN();

		if (this.usuario.getCodigo() != null) {
			this.usuario.setLogin(usuarioRN.carregar(this.usuario.getCodigo())
					.getLogin());
		}

		this.usuario.setSenha(senhaCripto);
		this.usuario.setDdd(this.usuario.getCelular().substring(1, 3));
		this.usuario.setCelular(this.usuario.getCelular().substring(4));

		usuarioRN.salvar(this.usuario);

		if (this.getConta().getDescricao() != null) {
			this.getConta().setUsuario(this.getUsuario());
			this.getConta().setFavorita(true);
			ContaRN contaRN = new ContaRN();
			contaRN.salvar(this.getConta());
		}

		return this.destinoSalvar;
	}

	public String excluir() {

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);

		this.lista = null;
		return null;
	}

	public String ativar() {

		if (this.usuario.isAtivo()) {
			this.usuario.setAtivo(false);
		} else {
			this.usuario.setAtivo(true);
		}

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);

		return null;
	}

	public String atribuiPermissao(Usuario usuario, String permissao) {

		this.usuario = usuario;
		java.util.Set<String> permissoes = this.usuario.getPermissao();

		if (permissoes.contains(permissao)) {
			permissoes.remove(permissao);
		} else {
			permissoes.add(permissao);
		}

		return null;
	}

	public List<Usuario> getLista() {

		if (this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return lista;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

}