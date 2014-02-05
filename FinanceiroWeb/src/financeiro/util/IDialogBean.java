package financeiro.util;

import org.primefaces.event.CloseEvent;

public interface IDialogBean {
	public void OpenDialog();
	public void CloseDialog(Object dialog);
	public void handleClose(CloseEvent event);
}
