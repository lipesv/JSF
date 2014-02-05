package financeiro.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

public abstract class AplicacaoBean implements IDialogBean, IMensagemBean,
		IValidacaoBean {

	private String tituloMensagem;
	private boolean valid;
	private String message;

	@Override
	public boolean isValid() {
		return this.valid;
	}

	@Override
	public void setValid(boolean value) {
		this.valid = value;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public void setMessage(String value) {
		this.message = value;
	}

	public String getTituloMensagem() {
		return tituloMensagem;
	}

	public void setTituloMensagem(String tituloMensagem) {
		this.tituloMensagem = tituloMensagem;
	}

	@Override
	public String exibirMensagemValidacao(String clientId) {

		FacesContext fc = FacesContext.getCurrentInstance();
		Iterator<FacesMessage> iter = fc.getMessages(clientId);

		if (iter.hasNext()) {
			return iter.next().getDetail();
		}

		return "";
	}

	@Override
	public void exibirMensagem(SelectEvent event) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage message = null;

		if (((AplicacaoBean) event.getObject()).isValid()) {
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Financeiro", Mensagem.Sucesso.getTipo());
		}

		facesContext.addMessage(null, message);
	}

	@Override
	public void exibirMensagemDialog(RequestContext request) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage message;
		StringBuilder sb = new StringBuilder();

		if (isValid()) {

			setTituloMensagem(Mensagem.Sucesso.getTipo());
			handleClose(null);
			request.update("listagem");
			request.update("mensagem");

		} else {

			setTituloMensagem(Mensagem.Erro.getTipo());

			if (getMessage() != null) {
				sb.append("\r\n");
				sb.append("<span style='margin-left:10px'>" + getMessage()
						+ "</span>");
			}

			request.update("mensagemDialog");
		}

		message = new FacesMessage((isValid() ? FacesMessage.SEVERITY_INFO
				: FacesMessage.SEVERITY_ERROR), getTituloMensagem(),
				sb.toString());

		request.addCallbackParam("erroProcessamento", !isValid());
		facesContext.addMessage(null, message);

	}

	@Override
	public void definirMensagem(Exception ex) {

		if (ex instanceof NullPointerException) {
			setMessage(ex.toString().substring(
					ex.toString().lastIndexOf(".") + 1));

		} else {
			setMessage(ex.getMessage());
		}
	}

	@Override
	public void OpenDialog() {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);

		options.put("contentWidth", 840);
		options.put("contentHeight", 500);

		RequestContext.getCurrentInstance().openDialog("fornecedor_edicao",
				options, null);

	}

	@Override
	public void CloseDialog(Object dialog) {
		RequestContext context = RequestContext.getCurrentInstance();
		exibirMensagemDialog(context);
	}

	@Override
	public abstract void handleClose(CloseEvent event);

	public String onFlowProcess(FlowEvent event) {
		return event.getNewStep();
	}

}