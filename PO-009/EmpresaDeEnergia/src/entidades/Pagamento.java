package entidades;

import java.util.Calendar;

public class Pagamento {
    private String idFatura;
    private double valor;
    private Calendar data;

    public Pagamento(String idFatura, double valor, Calendar data) {
        this.idFatura = idFatura;
        this.valor = valor;
        this.data = data;
    }

    public String getIdFatura() {
        return idFatura;
    }

    public double getValor() {
        return valor;
    }

    public Calendar getData() {
        return data;
    }
}
