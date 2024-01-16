package entidades;

public class Trecho {
    
    private Parada origem;
    private Parada destino;
    private int minutos;

    public Trecho(Parada origem, Parada destino, int minutos) {
        this.origem = origem;
        this.destino = destino;
        this.minutos = minutos;
    }

    public Parada getOrigem() {
        return origem;
    }

    public Parada getDestino() {
        return destino;
    }

    public int getMinutos() {
        return minutos;
    }
}
