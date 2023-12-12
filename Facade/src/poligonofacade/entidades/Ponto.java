package poligonofacade.entidades;

import java.util.Scanner;

public class Ponto {
	
	private double x;
	private double y;
	
	public Ponto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double distanciaPara(Ponto ponto) {
		double quadradoDiferencaX = Math.pow(ponto.getX() - this.getX(), 2);
		double quadradoDiferencaY = Math.pow(ponto.getY() - this.getY(), 2);
		double somaQuadrados = quadradoDiferencaX + quadradoDiferencaY;

		// Calculando a raiz quadrada da soma dos quadrados
		double distancia = Math.sqrt(somaQuadrados);
		
		return distancia;
	}
	
	public static Ponto solicitaPonto(Scanner scanner) {
        double x = 0; 
        double y = 0;
        
        boolean entradaValida = false;

        do {
            try {
                System.out.println("Digite as coordenadas:");

                System.out.print("Coordenada X: ");
                x = scanner.nextDouble();

                System.out.print("Coordenada Y: ");
                y = scanner.nextDouble();

                entradaValida = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Por favor, insira valores válidos.");
                scanner.nextLine();
            }
        } while (!entradaValida);    

        return new Ponto(x, y);
    }
}
