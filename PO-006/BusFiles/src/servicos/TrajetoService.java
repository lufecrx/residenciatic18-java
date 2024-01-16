package servicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Parada;
import entidades.Trajeto;
import entidades.Trecho;
import utils.CadastroInterface;
import utils.GerenciadorDeDados;

public class TrajetoService implements CadastroInterface{
    
    private List<Trajeto> trajetos;
    private TrechoService trechos;
    private String nomeDoArquivo = "trajetos";

    public TrajetoService(TrechoService trechoService) {
        this.trajetos = new ArrayList<>();
        this.trechos = trechoService;
    }

    @Override
    public void cadastrar(Scanner scanner) {
        List<Trecho> trechosCadastrados = this.trechos.getCadastros();
        
        System.out.println("Cadastrando trajeto");
        Trajeto trajeto = new Trajeto();
        
        if(trechosCadastrados.isEmpty()) {
            System.out.println("Cadastre pelo menos um trecho antes de cadastrar o trajeto.");
            return;
        }
        System.out.println("Trechos: ");
        trechos.exibir();


        while (true ) {
            System.out.print("Selecione o número correspondente ao trecho para adicionar ao trajeto ou digite '0' para encerrar: ");
            int trecho = scanner.nextInt();

            if (trecho > 0 && trecho <= trechosCadastrados.size()) {
                System.out.println("Trecho selecionado: " + trechosCadastrados.get(trecho - 1).getOrigem() + " para " + trechosCadastrados.get(trecho - 1).getDestino());
                Trecho trechoSelecionado = trechosCadastrados.get(trecho - 1);
                trajeto.cadastraTrecho(trechoSelecionado);
            } else {
                System.out.println("Trecho inválido.");
            }          
            if (trecho == 0) {
                break;
            }
        }

        trajetos.add(trajeto);
        System.out.println("Trajeto cadastrado com sucesso.");     
    }

    public List<Trajeto> getCadastros() {
        return trajetos;
    }

    public void exibir() {
        if(this.trajetos.isEmpty()) {
            System.out.println("Nenhum trajeto encontrado.");
            return;
        }

        int index = 1;
        for (Trajeto trajeto : this.trajetos) {
            System.out.println("TRAJETO " + index++);
            for (Trecho trecho : trajeto.getTrechos()) {
                System.out.println(trecho.getOrigem() + " para " + trecho.getDestino());
            }
            System.out.println();
        }
    }

    public static void exibir(List<Trajeto> trajetos) {
        if(trajetos.isEmpty()) {
            System.out.println("Nenhum trajeto encontrado.");
            return;
        }
        
        int index = 1;
        for (Trajeto trajeto : trajetos) {
            System.out.println("TRAJETO " + index++);
            for (Trecho trecho : trajeto.getTrechos()) {
                System.out.println(trecho.getOrigem() + " para " + trecho.getDestino());
            }
            System.out.println();
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
                String[] dados = linha.split("|");
                
                Trajeto trajeto = new Trajeto();
                for (String trechoDados : dados) {
                    String[] dadosTrecho = trechoDados.split(";");
                    String origem = dadosTrecho[0];
                    String destino = dadosTrecho[1];

                    // Procurar trecho no cadastro de trechos para associar ao trajeto correspondente
                    Parada paradaOrigem = new Parada(origem);
                    Parada paradaDestino = new Parada(destino);
                    
                    for (Trecho trecho : trechos.getCadastros()) {
                        if (trecho.getOrigem().equals(paradaOrigem) && trecho.getDestino().equals(paradaDestino)) {
                            trajeto.cadastraTrecho(trecho);
                        }
                    }

                    trajetos.add(trajeto);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de trajetos: " + e.getMessage());
        }
    }
}
