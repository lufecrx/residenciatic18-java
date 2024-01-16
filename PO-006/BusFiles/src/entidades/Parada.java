package entidades;

import java.util.List;

public class Parada {
    
    private String nome;
    private List<Passageiro> passageirosEmbarcados;
    private boolean checkpoint;

    public Parada(String nome) {
        this.nome = nome;
    }
    
    public List<Passageiro> getPassageirosEmbarcados() {
        return passageirosEmbarcados;
    }
    
    public String getNome() {
        return nome;
    }

    public boolean isCheckpoint() {
        return checkpoint;
    }

    public void embarcar(Passageiro passageiro) {
        this.passageirosEmbarcados.add(passageiro);
    }

    public void mostrarPassageirosEmbarcados() {
        for (Passageiro passageiro : this.passageirosEmbarcados) {
            System.out.println(passageiro.getNome());
            switch (passageiro.getCartao()) {
                case ESTUDANTIL:
                    System.out.println("Cartão Estudantil");
                    break;
                case IDOSO:
                    System.out.println("Cartão Idoso");
                    break;
                case TRANSPORTE: 
                    System.out.println("Cartão Transporte");
                    break;
                default:
                    System.out.println("Cartão Indefinido");
                    break;
            }
            System.out.println("Número do cartão: " + passageiro.getNumCartao());
            System.out.println("CPF: " + passageiro.getCpf());
        }
    }
}