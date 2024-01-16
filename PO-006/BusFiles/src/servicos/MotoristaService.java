package servicos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Motorista;
import utils.CadastroInterface;
import utils.DuplicataException;
import utils.GerenciadorDeDados;

public class MotoristaService implements CadastroInterface {

    private List<Motorista> motoristas;
    private String nomeDoArquivo = "motoristas";

    public MotoristaService() {
        this.motoristas = new ArrayList<>();
    }

    @Override
    public void cadastrar(Scanner scanner) {

        System.out.println("Cadastrando motorista");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CNH: ");
        String cnh = scanner.nextLine();

        try {
            if (validarCnh(motoristas, cnh)) {
                Motorista motorista = new Motorista(nome, cnh);
                motoristas.add(motorista);
            }
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("Motorista cadastrado com sucesso!");
        salvar();
    }

    public List<Motorista> getCadastros() {
        return motoristas;
    }

    private boolean validarCnh(List<Motorista> motoristas, String cnh) throws DuplicataException {
        // Verifica se já existe um motorista com a mesma CNH
        for (Motorista motorista : motoristas) {
            if (motorista.getCnh().equals(cnh)) {
                throw new DuplicataException("Já existe um motorista com a mesma CNH.");
            }
        }
        return true;
    }

    @Override
    public void exibir() {
        if (motoristas.isEmpty()) {
            System.out.println("Nenhum motorista encontrado");
            return;
        }

        for (Motorista motorista : motoristas) {
            System.out.println("Nome: " + motorista.getNome() + "| CNH: " + motorista.getCnh());
        }
    }

    @Override
    public void salvar() {
        GerenciadorDeDados.salvar(nomeDoArquivo, getCadastros());
    }

    @Override
    public void carregar() {
        String arquivo = "arquivos/" + nomeDoArquivo + ".txt";

        try {
            GerenciadorDeDados.criarArquivoInexistente(arquivo);
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de " + nomeDoArquivo + ": " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");

                if (dados.length == 2) {
                    String nome = dados[0];
                    String cnh = dados[1];

                    Motorista motorista = new Motorista(nome, cnh);
                    motoristas.add(motorista);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de motoristas: " + e.getMessage());
        }
    }
}
