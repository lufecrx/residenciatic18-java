package entidades;

import java.util.List;

public class Imovel {
	
	private String matricula;
	private String endereco;
	private int leituraAtual;
	private int ultimaLeitura;
	private int penultimaLeitura;
	private List<Fatura> faturas;
	
	public Imovel(String matricula, String endereco) {
		this.matricula = matricula;
		this.endereco = endereco;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(int leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public int getUltimaLeitura() {
		return ultimaLeitura;
	}

	public void setUltimaLeitura(int ultimaLeitura) {
		this.ultimaLeitura = ultimaLeitura;
	}

	public int getPenultimaLeitura() {
		return penultimaLeitura;
	}

	public void setPenultimaLeitura(int penultimaLeitura) {
		this.penultimaLeitura = penultimaLeitura;
	}

	public List<Fatura> getFaturas() {
		return faturas;
	}

	public void setFaturas(List<Fatura> faturas) {
		this.faturas = faturas;
	}

	public void fazerLeitura() {
		
	}
	
	
	

}
