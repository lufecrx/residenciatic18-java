package servicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Parada;
import entidades.Trecho;
import utils.CadastroInterface;
import utils.CadastroInvalidoException;
import utils.DuplicataException;
import utils.GerenciadorDeDados;
import utils.ValorInvalidoException;

public class TrechoService implements CadastroInterface{
    
    private List<Trecho> trechos;
    private ParadaService paradaService;
    private String nomeDoArquivo = "trechos";

    public TrechoService(ParadaService paradaService) {
        this.trechos = new ArrayList<>();
        this.paradaService = paradaService;
    }

    @Override
    public void cadastrar(Scanner scanner) {
        
        System.out.println("Cadastrando trecho");

        Parada paradaDeOrigem;
        Parada paradaDeDestino;
        System.out.print("Origem: ");
        paradaDeOrigem = paradaService.criar(scanner);;
        if (paradaDeOrigem == null) { return; }
        
        System.out.print("Destino: ");
        paradaDeDestino = paradaService.criar(scanner);;
        if (paradaDeDestino == null) { return; }

        System.out.print("Minutos: ");
        String minutos;
        minutos = scanner.next();
        try {
            validarMinutos(minutos);
        } catch (ValorInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        Trecho trecho = new Trecho(paradaDeOrigem, paradaDeDestino, minutos);
        trechos.add(trecho);
   
    }

    public List<Trecho> getCadastros() {
        return trechos;
    }

    public boolean validarTrecho(List<Trecho> trechos, Parada paradaDeOrigem, Parada paradaDeDestino) throws DuplicataException{
        for (Trecho trecho : trechos) {
            if (trecho.getOrigem().equals(paradaDeOrigem) && trecho.getDestino().equals(paradaDeDestino)) {
                throw new DuplicataException("Trecho duplicado");
            }
        }
        return true;
    }

    public void validarMinutos(String min) throws ValorInvalidoException {
        try {
            Integer.parseInt(min);
        } catch (NumberFormatException e) {
            throw new ValorInvalidoException("Entrada inválida para minutos. Digite um número inteiro válido.");
        }

        int minutos = Integer.parseInt(min);
        if (minutos <= 0) {
            throw new ValorInvalidoException("Minutos inválidos");
        }
    }
    
    @Override
    public void exibir() {
        if(trechos.isEmpty()) {
            System.out.println("Nenhum trecho encontrado.");
            return;
        }

        System.out.println("TRECHOS: ");
        int index = 1;
        for (Trecho trecho : this.trechos) {
            System.out.println(index + "- " + trecho.getOrigem() + " para " + trecho.getDestino());
            index++;
        }
    }

    @Override
    public void salvar(List<?> cadastros) {
        cadastros = getCadastros();
        GerenciadorDeDados.salvar(nomeDoArquivo, cadastros);
    }

    @Override
    public void carregar() {
        String arquivo = "arquivos/" + nomeDoArquivo + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String [] dados = linha.split(";");

                if (dados.length == 3) {
                    String origem = dados[0];
                    String destino = dados[1];
                    String minutos = dados[2];
                    
                    Parada paradaOrigem = paradaService.criar(origem);
                    Parada paradaDestino = paradaService.criar(destino);

                    Trecho trecho = new Trecho(paradaOrigem, paradaDestino, minutos);
                    trechos.add(trecho);
                }      
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de trechos: " + e.getMessage());
        } catch (DuplicataException | CadastroInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
