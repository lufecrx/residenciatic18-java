package poligonofacade.entidades;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Poligono {
	ArrayList<Ponto> pontos;
	
	public Poligono() {
		this.pontos = new ArrayList<Ponto>();
	}
	
	public static Poligono criaPoligono(int quantidadeDePontos, Scanner leituraDoTeclado) throws InputMismatchException {
		Poligono poligono = new Poligono();
		for(int i = 0; i < quantidadeDePontos; i++) {
			Ponto ponto = Ponto.solicitaPonto(leituraDoTeclado);
			poligono.inserePonto(ponto);
		}
		return poligono;
	}
	
	public void inserePonto(Ponto ponto) {
		pontos.add(ponto);
	}
	
	public void inserePonto(double x, double y) {
		Ponto p = new Ponto (x, y);
		pontos.add(p);
	}
	
	public double perimetro() {
		double per = 0;
		Ponto p1;
		Ponto p2;
		
		for (int i=0;i<pontos.size()-1;i++) {
			p1 = pontos.get(i);
			p2 = pontos.get(i+1);

			double quadradoDiferencaX = Math.pow(p1.getX() - p2.getX(), 2);
			double quadradoDiferencaY = Math.pow(p1.getY() - p2.getY(), 2);
			double somaQuadrados = quadradoDiferencaX + quadradoDiferencaY;

			// Calculando a raiz quadrada da soma dos quadrados
			double raizQuadrada = Math.sqrt(somaQuadrados);

			// Adicionando o resultado ao valor atual de 'per'
			per += raizQuadrada;
		}
	    
	    p1 = pontos.get(pontos.size() - 1); // Último ponto
	    p2 = pontos.get(0); // Primeiro ponto

	    double quadradoDiferencaX = Math.pow(p1.getX() - p2.getX(), 2);
	    double quadradoDiferencaY = Math.pow(p1.getY() - p2.getY(), 2);
	    double somaQuadrados = quadradoDiferencaX + quadradoDiferencaY;

	    // Calculando a raiz quadrada da soma dos quadrados e adicionando ao valor de 'per'
	    per += Math.sqrt(somaQuadrados);

	    return per;
	}
}
