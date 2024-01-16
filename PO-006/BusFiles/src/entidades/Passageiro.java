package entidades;

import utils.CartaoEnum;
import utils.RegistroInterface;

public class Passageiro implements RegistroInterface {

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

    @Override
    public String toFormattedString() {
        return nome + ";" + cpf + ";" + cartao + ";" + numCartao;
    }
}
