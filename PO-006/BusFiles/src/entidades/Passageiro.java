package entidades;

import utils.CartaoEnum;

public class Passageiro {

    private String nome;
    private String cpf;
    private CartaoEnum cartao;
    private String numCartao;


    public Passageiro(String nome, String cpf, CartaoEnum cartao, String numCartao) {
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

    public CartaoEnum getCartao() {
        return cartao;
    }

    public String getNumCartao() {
        return numCartao;
    }
}
