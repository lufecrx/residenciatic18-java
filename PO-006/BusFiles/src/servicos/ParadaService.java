package servicos;

import java.util.ArrayList;
import java.util.List;

import entidades.Parada;

public class ParadaService {
    
    private List<Parada> paradas;

    public ParadaService() {
        this.paradas = new ArrayList<>();
    }

    public List<Parada> getCadastros() {
        return paradas;
    }
}
