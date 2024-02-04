package entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public String dataParaString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(data.getTime());
	}

	public static Calendar stringParaCalendar(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }
}
