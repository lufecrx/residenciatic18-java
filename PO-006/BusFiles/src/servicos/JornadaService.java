package servicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Jornada;
import entidades.Motorista;
import entidades.Trajeto;
import entidades.Trecho;
import entidades.Veiculo;
import utils.CadastroInterface;
import utils.CadastroInvalidoException;
import utils.GerenciadorDeDados;

public class JornadaService implements CadastroInterface {
    
    private List<Jornada> jornadas;
    private TrajetoService trajetos;
    private MotoristaService motoristas;
    private VeiculoService veiculos;
    private String nomeDoArquivo = "jornadas";

    public JornadaService(TrajetoService trajetoService, MotoristaService motoristaService, VeiculoService veiculoService) {
        this.jornadas = new ArrayList<>();
        this.trajetos = trajetoService;
        this.motoristas = motoristaService;
        this.veiculos = veiculoService;
    }

    @Override
    public void cadastrar(Scanner scanner) {
        
        System.out.println("Cadastrando jornada");

        if (trajetos.getCadastros().isEmpty()) {
            System.out.println("Cadastre pelo menos um trajeto antes de cadastrar uma jornada.");
            return;
        }

        System.out.print("Trajetos: ");
        trajetos.exibir();

        try {
            List<Trajeto> trajetosDaJornada = adicionarTrajetos(scanner);
            Motorista motoristaAssociado = associarMotorista(scanner);
            Veiculo veiculoAssociado = associarVeiculo(scanner);
            
            Jornada jornada = new Jornada(trajetosDaJornada, motoristaAssociado, veiculoAssociado);
            jornadas.add(jornada);
            salvar();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("Jornada cadastrada com sucesso!");
        salvar();
    }

    public List<Trajeto> adicionarTrajetos(Scanner scanner) throws CadastroInvalidoException {
        List<Trajeto> trajetosDaJornada = new ArrayList<>();

        while (true) {
            System.out.print("Selecione o número correspondente ao trajeto para adicionar a jornada ou digite '0' para encerrar: ");
            int trajeto = scanner.nextInt();

            if (trajeto > 0 && trajeto <= trajetos.getCadastros().size()) {
                System.out.println("Trajeto selecionado: " + trajetos.getCadastros().get(trajeto - 1));
                trajetosDaJornada.add((Trajeto) trajetos.getCadastros().get(trajeto - 1));
            }

            if (trajeto == 0) {
                break;
            }
        }

        if (trajetosDaJornada.isEmpty()) {
            throw new CadastroInvalidoException("Nenhum trajeto selecionado.");
        }

        return trajetosDaJornada;
    }

    public Motorista associarMotorista(Scanner scanner) throws CadastroInvalidoException {
        System.out.println("Motoristas: ");
        motoristas.exibir();

        System.out.print("Selecione a CNH correspondente ao motorista para associar a jornada: ");
        String cnh = scanner.next();

        for (Motorista motorista : motoristas.getCadastros()) {
            if (motorista.getCnh().equals(cnh)) {
                return motorista;
            }
        }

        throw new CadastroInvalidoException("CNH inválido.");
    }

    public Veiculo associarVeiculo(Scanner scanner) throws CadastroInvalidoException {

        System.out.println("Veiculos: ");
        veiculos.exibir();

        System.out.print("Selecione o placa correspondente ao veiculo para associar a jornada: ");
        String placa = scanner.next();

        for (Veiculo veiculo : veiculos.getCadastros()) {
            if(veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }

        throw new CadastroInvalidoException("Placa inválida.");
    }

    public List<Jornada> getCadastros() {
        return jornadas;
    }

    @Override
    public void exibir() {
        if(this.jornadas.isEmpty()) {
            System.out.println("Nenhuma jornada encontrada.");
        }

        int index = 1;
        for (Jornada jornada : this.jornadas) {
            System.out.println("JORNADA " + index++);
            System.out.println("Trajetos: ");
            TrajetoService.exibir(jornada.getTrajetos());
            System.out.println();            
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
                String[] dados = linha.split("//");
                
                List<Trajeto> listaDeTrajetos = new ArrayList<>();
                // Construir trajeto
                String[] dadosTrajeto = dados[0].split("|");
                for (String trecho : dadosTrajeto) {
                    String[] dadosTrecho = trecho.split(";");
                    String origem = dadosTrecho[0];
                    String destino = dadosTrecho[1];

                    // Procurar trecho no cadastro de trechos
                    for (Trajeto trajetoCadastrado : trajetos.getCadastros()) {
                        for (Trecho trechoCadastrado : trajetoCadastrado.getTrechos()) {
                            if (trechoCadastrado.getOrigem().getNome().equals(origem) && trechoCadastrado.getDestino().getNome().equals(destino)) {
                                listaDeTrajetos.add(trajetoCadastrado);
                            }
                        }
                    }
                }
                
                String motorista = dados[1];
                String veiculo = dados[2];

                // Procurar motorista no cadastro de motoristas
                for (Motorista motoristaCadastrado : motoristas.getCadastros()) {
                    if (motoristaCadastrado.getNome().equals(motorista)) {
                        // Procurar veiculo no cadastro de veiculo
                        for (Veiculo veiculoCadastrado : veiculos.getCadastros()) {
                            if (veiculoCadastrado.getPlaca().equals(veiculo)) {
                                Jornada jornada = new Jornada(listaDeTrajetos, motoristaCadastrado, veiculoCadastrado); 
                                this.jornadas.add(jornada);                               
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de jornadas: " + e.getMessage());
        }
    }
}
