package entidades;

import java.util.ArrayList;
import java.util.List;

import utils.RegistroInterface;

public class Trajeto implements RegistroInterface {
    
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

    public List<Trecho> getTrechos() {
        return trechos;
    }

    @Override
    public String toFormattedString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < trechos.size(); i++) {
            sb.append(trechos.get(i).toFormattedString());
            if (i < trechos.size() - 1) {
                sb.append("|"); // Caractere especial para separar os trechos
            }
        }
        return sb.toString();
    }
}
