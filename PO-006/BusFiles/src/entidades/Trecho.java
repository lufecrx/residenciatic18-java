package entidades;

public class Trecho {
    
    private Parada origem;
    private Parada destino;
    private String minutos;

    public Trecho(Parada origem, Parada destino, String minutos) {
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

    public String getMinutos() {
        return minutos;
    }
}
