package entidades;

public class Parada {
    
    private String nome;
    private boolean checkpoint;

    public Parada(String nome, String horario) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public boolean isCheckpoint() {
        return checkpoint;
    }
    
}