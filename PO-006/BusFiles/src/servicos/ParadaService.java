package servicos;

import java.util.HashSet;
import java.util.Set;

import entidades.Parada;

public class ParadaService {
    
    private Set<Parada> paradas;

    public ParadaService() {
        this.paradas = new HashSet<>();
    }

    public Set<Parada> getCadastros() {
        return paradas;
    }
}
