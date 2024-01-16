package servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Motorista;
import utils.CadastroInterface;
import utils.DuplicataException;

public class MotoristaService implements CadastroInterface {
    
    private List<Motorista> motoristas;

    public MotoristaService() {
        this.motoristas = new ArrayList<>();
    }

    @Override
    public void cadastrar(Scanner scanner) {

        System.out.println("Cadastrando motorista");
        
        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("CNH: ");
        String cnh = scanner.nextLine();

        try {
            if(validarCnh(motoristas, cnh)) {
                Motorista motorista = new Motorista(nome, cnh);
                motoristas.add(motorista);
            }
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
    }

    @Override
    public List<Motorista> getCadastros() {
        return motoristas;
    }

    private boolean validarCnh(List<Motorista> motoristas, String cnh) throws DuplicataException {
        // Verifica se já  existe um motorista com a mesma CNH
        for (Motorista motorista : motoristas) {
            if (motorista.getCnh().equals(cnh)) {
                throw new DuplicataException("Já existe um motorista com a mesma CNH.");
            }
        }
        return true;
    }    

    @Override
    public void exibir() {
        if(motoristas.isEmpty()) {
            System.out.println("Nenhum motorista encontrado");
            return;
        }

        for (Motorista motorista : motoristas) {
            System.out.println("Nome: " + motorista.getNome() + "| CNH: " + motorista.getCnh());
        }
    }
    
}
