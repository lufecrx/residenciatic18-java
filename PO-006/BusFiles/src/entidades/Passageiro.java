package entidades;

import utils.Cartao;

public class Passageiro {

    private String nome;
    private String cpf;
    private Cartao cartao;
    private String numCartao;


    public Passageiro(String nome, String cpf, Cartao cartao, String numCartao) {
        this.nome = nome;
        this.cpf = cpf;
        this.cartao = cartao;
        this.numCartao = numCartao;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getNumCartao() {
        return numCartao;
    }
}
