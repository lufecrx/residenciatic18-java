 package entidades;

import java.util.ArrayList;
import java.util.List;

public class PontosDeParada {
    
    private List<Parada> paradas;
    
    public PontosDeParada() {
        this.paradas = new ArrayList<>();
    }

    public void cadastraParada(Parada parada) {
        this.paradas.add(parada);
    }

    public void  mostrarParadas() {
        int index = 1;
        for (Parada parada : this.paradas) {
            System.out.println(index++ +  "- " + parada.getNome());
        }
    }

    // Retorna uma parada pelo nome
    public Parada getParada(int index) {
        return this.paradas.get(index - 1);
    }
}
