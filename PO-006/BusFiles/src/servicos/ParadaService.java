package servicos;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import entidades.Parada;
import utils.CadastroInvalidoException;
import utils.DuplicataException;

public class ParadaService {

    private Set<Parada> paradas;

    public ParadaService() {
        this.paradas = new HashSet<>();
    }

    public Parada cadastrar(Scanner scanner) throws DuplicataException, CadastroInvalidoException {
        Parada novaParada = new Parada(scanner.nextLine());
        validarParada(novaParada);
        paradas.add(novaParada);
        
        return novaParada;
    }

    public Set<Parada> getCadastros() {
        return paradas;
    }

    public boolean validarParada(Parada parada)
            throws DuplicataException, CadastroInvalidoException {
        if (parada.getNome() == null || parada.getNome().isEmpty()) {
            throw new CadastroInvalidoException("Parada inválida");
        }

        for (Parada p : paradas) {
            if (p.getNome().equals(parada.getNome())) {
                throw new DuplicataException("Parada duplicada");
            }
        }
        return true;
    }
}
