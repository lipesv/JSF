package financeiro.cheque;

import java.util.Date;
import java.util.List;

import financeiro.conta.Conta;
import financeiro.lancamento.Lancamento;
import financeiro.util.DAOFactory;
import financeiro.util.RNException;

public class ChequeRN {

	private ChequeDAO chequeDAO;

	public ChequeRN() {
		this.chequeDAO = DAOFactory.criarChequeDAO();
	}

	public void salvar(Cheque cheque) {
		this.chequeDAO.salvar(cheque);
	}

	public int salvarSequencia(Conta conta, Integer chequeInicial,
			Integer chequeFinal) {

		Cheque cheque = null;
		Cheque chequeVerifica = null;
		ChequeId chequeId = null;
		int contaTotal = 0;

		for (int i = chequeInicial; i < chequeFinal; i++) {

			chequeId = new ChequeId();
			chequeId.setCheque(i);
			chequeId.setConta(conta.getCodigo().intValue());

			chequeVerifica = this.carregar(chequeId);

			if (chequeVerifica == null) {

				cheque = new Cheque();

				cheque.setChequeId(chequeId);
				cheque.setSituacao(Cheque.SITUACAO_CHEQUE_NAO_EMITIDO);
				cheque.setDataCadastro(new Date(System.currentTimeMillis()));

				this.salvar(cheque);

				contaTotal++;
			}

		}

		return contaTotal;
	}

	public void excluir(Cheque cheque) throws RNException {

		if (cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_NAO_EMITIDO) {
			this.chequeDAO.excluir(cheque);
		} else {
			throw new RNException(
					"N�o � poss�vel excluir cheque, status n�o permitido para opera��o");
		}

	}

	public Cheque carregar(ChequeId chequeId) {
		return this.chequeDAO.carregar(chequeId);
	}

	public List<Cheque> listar(Conta conta) {
		return this.chequeDAO.listar(conta);
	}

	public void cancelarCheque(Cheque cheque) throws RNException {

		if (cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_NAO_EMITIDO
				|| cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_CANCELADO) {
			cheque.setSituacao(Cheque.SITUACAO_CHEQUE_CANCELADO);
			this.chequeDAO.salvar(cheque);
		} else {
			throw new RNException(
					"N�o foi possp�vel cancelar cheque, status n�o permitido para opera��o.");
		}

	}

	public void baixarCheque(ChequeId chequeId, Lancamento lancamento) {

		Cheque cheque = this.carregar(chequeId);

		if (cheque != null) {
			cheque.setSituacao(Cheque.SITUACAO_CHEQUE_BAIXADO);
			cheque.setLancamento(lancamento);
			this.chequeDAO.salvar(cheque);
		}

	}

	public void desvinculaLancamento(Conta conta, Integer numeroCheque)
			throws RNException {

		ChequeId chequeId = new ChequeId();

		chequeId.setCheque(numeroCheque);
		chequeId.setConta(conta.getCodigo().intValue());

		Cheque cheque = this.carregar(chequeId);

		if (cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_CANCELADO) {
			throw new RNException("N�o � poss�vel usar cheque cancelado.");
		} else {
			cheque.setSituacao(Cheque.SITUACAO_CHEQUE_NAO_EMITIDO);
			cheque.setLancamento(null);
			this.salvar(cheque);
		}
	}

}
