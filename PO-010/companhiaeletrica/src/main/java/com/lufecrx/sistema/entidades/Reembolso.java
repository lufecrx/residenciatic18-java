package com.lufecrx.sistema.entidades;

import java.util.Calendar;

public class Reembolso {
    private String idFatura;
    private Double valor;
    private Calendar data;

    public Reembolso(String idFatura, double valor, Calendar data) {
        this.idFatura = idFatura;
        this.valor = valor;
        this.data = data;
    }

    public String getIdFatura() {
        return idFatura;
    }

    public Double getValor() {
        return valor;
    }

    public Calendar getData() {
        return data;
    }
}
