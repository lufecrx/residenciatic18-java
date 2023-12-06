package entidades;

import java.util.Calendar;

public class Fatura {
	
	private Calendar data;
	private int ultimaLeitura;
	private int penultimaLeitura;
	private float valorCalculado;
	private boolean isQuitado;
	
	public Fatura(Calendar data, int ultimaLeitura, int penultimaLeitura) {
		this.data = data;
		this.ultimaLeitura = ultimaLeitura;
		this.penultimaLeitura = penultimaLeitura;
		this.valorCalculado = ultimaLeitura - penultimaLeitura;
		this.isQuitado = false;
	}
	
	public void quitarFatura() {
		isQuitado = true;
	}
	
	
	
	
	
}
