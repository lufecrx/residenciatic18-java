package entidades;

import utils.RegistroInterface;

public class Motorista implements RegistroInterface {
    
    private String nome;
    private String cnh;

    public Motorista(String nome, String cnh) {
        this.nome = nome;
        this.cnh = cnh;
    }

    public String getNome() {
        return nome;
    }

    public String getCnh() {
        return cnh;
    }

    @Override
    public String toString() {
        return nome + ";" + cnh;
    }
}
