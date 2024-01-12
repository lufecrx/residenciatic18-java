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

    public void setOrigem(PontosDeParada pontos, int index) {
        this.origem = pontos.getParada(index);
    }

    public void setDestino(PontosDeParada pontos, int index) {
        this.destino = pontos.getParada(index);
    }

    public void setOrigem(Parada origem) {
        this.origem = origem;
    }

    public void setDestino(Parada destino) {
        this.destino = destino;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }
}
