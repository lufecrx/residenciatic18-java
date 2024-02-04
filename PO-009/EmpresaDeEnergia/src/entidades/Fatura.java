package entidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fatura {

	private String idFatura;
	private Calendar data;
	private Imovel imovelAssociado;
	private double ultimaLeitura;
	private double penultimaLeitura;
	private double divida;
	private double valor;
	private double valorPago;
	private boolean quitado;
	private List<Pagamento> pagamentos;
	private List<Reembolso> reembolsos;

	public Fatura(Imovel imovelAssociado, double penultimaLeitura, double ultimaLeitura, Calendar data, double valor) {
		this.idFatura = gerarIdFatura(imovelAssociado, data);
		this.imovelAssociado = imovelAssociado;
		this.penultimaLeitura = penultimaLeitura;
		this.ultimaLeitura = ultimaLeitura;
		this.data = data;
		this.valor = valor;
		this.pagamentos = new ArrayList<>();
		this.reembolsos = new ArrayList<>();
		quitado = false;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Imovel getImovelAssociado() {
		return imovelAssociado;
	}

	public void setImovelAssociado(Imovel imovelAssociado) {
		this.imovelAssociado = imovelAssociado;
	}

	public double getUltimaLeitura() {
		return ultimaLeitura;
	}

	public void setUltimaLeitura(double ultimaLeitura) {
		this.ultimaLeitura = ultimaLeitura;
	}

	public double getPenultimaLeitura() {
		return penultimaLeitura;
	}

	public void setPenultimaLeitura(double penultimaLeitura) {
		this.penultimaLeitura = penultimaLeitura;
	}

	public double getValor() {
		return valor;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValorPago() {
		return valorPago;
	}

	public boolean isQuitado() {
		return quitado;
	}

	public void setQuitado(boolean quitado) {
		this.quitado = quitado;
	}

	public double getDivida() {
		return divida;
	}

	public String getIdFatura() {
		return idFatura;
	}

	public double getTotalReembolsos() {
		return reembolsos.stream().mapToDouble(Reembolso::getValor).sum();
	}

	public List<Reembolso> getReembolsos() {
		return reembolsos;
	}

	public void quitarFatura() {
		quitado = true;
	}

	public String gerarIdFatura(Imovel imovelAssociado, Calendar data) {
		// Mátricula do imovel + data
		String idFatura = imovelAssociado.getMatricula() + data.getTimeInMillis();
		return idFatura;
	}

	public double calcularTotalDosPagamentos() {
		return pagamentos.stream().mapToDouble(Pagamento::getValor).sum();
	}

	public void adicionarPagamento(Pagamento pagamento) {
		pagamentos.add(pagamento);

		// Atualiza o estado da fatura quando o pagamento é registrado
		atualizarEstadoFatura();
	}

	private void atualizarEstadoFatura() {
		this.valorPago = calcularTotalDosPagamentos();

		if (valorPago >= valor) {
			quitado = true;
			this.divida = 0;
			double valorEmExcesso = valorPago - valor;

			// Gerar reembolso, se necessário
			gerarReembolso(valorEmExcesso);
		} else {
			this.divida = valor - valorPago;
		}
	}

	private void gerarReembolso(double valorEmExcesso) {
		// Gera um reembolso apenas se houver pagamento em excesso
		if (valorEmExcesso > 0) {
			Reembolso novoReembolso = new Reembolso(idFatura, valorEmExcesso, Calendar.getInstance());
			reembolsos.add(novoReembolso);
		}
	}

}
