package financeiro.util;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

public interface IMensagemBean {

	void exibirMensagem(SelectEvent event);
	void exibirMensagemDialog(RequestContext request);
	void definirMensagem(Exception ex);
}
