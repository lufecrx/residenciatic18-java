package entidades;

import java.util.ArrayList;
import java.util.List;

public class Trajeto {
    
    private List<Trecho> trechos;
    
    public Trajeto() {
        this.trechos = new ArrayList<>();
    }
    
    public void cadastraTrecho(Trecho trecho) {
        this.trechos.add(trecho);
    }

    public void mostrarTrechos() {
        int index = 1;
        for (Trecho trecho : this.trechos) {
            System.out.println(index++ +  "- " + trecho.getOrigem() + " para " + trecho.getDestino());
        }
    }
}
