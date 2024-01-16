package servicos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entidades.Embarque;
import entidades.Parada;
import entidades.Passageiro;
import entidades.Trecho;
import utils.CadastroInterface;
import utils.CadastroInvalidoException;
import utils.GerenciadorDeDados;

public class EmbarqueService implements CadastroInterface {

    private List<Embarque> embarques;
    private TrechoService trechos;
    private PassageiroService passageiros;
    private String nomeDoArquivo = "embarques";

    public EmbarqueService(TrechoService trechos, PassageiroService passageiros) {
        this.embarques = new ArrayList<>();
        this.trechos = trechos;
        this.passageiros = passageiros;
    }

    @Override
    public void cadastrar(Scanner scanner) {
        System.out.println("Cadastrando embarque");

        try {
            validarEmbarque();
        } catch (CadastroInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        Parada paradaDeOrigem = null;
        Passageiro passageiroAssociado = null;

        System.out.println("Trechos: ");
        trechos.exibir();

        int trechoIdx;
        try {
            System.out.print(
                    "Selecione o número correspondente do trecho (parada de origem) para associar ao embarque: ");
            trechoIdx = scanner.nextInt();
            validarTrechoIdx(trechoIdx);
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida para trecho");
            scanner.nextLine();
            return;
        } catch (CadastroInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        Trecho trechoSelecionado = trechos.getCadastros().get(trechoIdx - 1);
        paradaDeOrigem = trechoSelecionado.getOrigem();

        System.out.println("Trecho selecionado: " + trechoSelecionado);
        System.out.println("Parada de origem selecionada: " + paradaDeOrigem.getNome());

        System.out.println("Passageiros: ");
        passageiros.exibir();

        int passageiroIdx;
        try {
            System.out.print("Selecione o número correspondente do passageiro para associar ao embarque: ");
            passageiroIdx = scanner.nextInt();
            validarPassageiroIdx(passageiroIdx);
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida parapassageiro");
            scanner.nextLine();
            return;
        } catch (CadastroInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        passageiroAssociado = passageiros.getCadastros().get(passageiroIdx - 1);

        System.out.println("Passageiro selecionado: " + passageiroAssociado.getNome());

        embarques.add(new Embarque(passageiroAssociado, paradaDeOrigem));
        System.out.println("Embarque cadastrado com sucesso!");
        salvar();
    }

    public void validarEmbarque() throws CadastroInvalidoException {
        if (trechos.getCadastros().isEmpty() && passageiros.getCadastros().isEmpty()) {
            throw new CadastroInvalidoException(
                    "Cadastre pelo menos um trecho e um passageiro antes de cadastrar o embarque.");
        }
        if (trechos.getCadastros().isEmpty()) {
            throw new CadastroInvalidoException("Cadastre pelo menos um trecho antes de cadastrar o embarque.");
        }
        if (passageiros.getCadastros().isEmpty()) {
            throw new CadastroInvalidoException("Cadastre pelo menos um passageiro antes de cadastrar o embarque.");
        }
    }

    public void validarTrechoIdx(int trechoIdx) throws CadastroInvalidoException {
        if (trechoIdx < 1 || trechoIdx > trechos.getCadastros().size()) {
            throw new CadastroInvalidoException("Trecho inválido selecionado.");
        }
    }

    public void validarPassageiroIdx(int passageiroIdx) throws CadastroInvalidoException {
        if (passageiroIdx < 1 || passageiroIdx > passageiros.getCadastros().size()) {
            throw new CadastroInvalidoException("Passageiro inválido selecionado.");
        }
    }

    public List<Embarque> getCadastros() {
        return embarques;
    }

    @Override
    public void exibir() {
        if (embarques.isEmpty()) {
            System.out.println("Nenhum embarque encontrado.");
        }

        int index = 1;
        for (Embarque embarque : embarques) {
            System.out.println(
                    index++ + "- " + embarque.getPassageiro().getNome() + " - " + embarque.getParada().getNome());
        }

        System.out.println("Embarques encontrados: " + embarques.size());
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
                String[] dadosEmbarque = linha.split("|");

                Embarque embarque;
                String[] dadosPassageiro = dadosEmbarque[0].split(";");
                String cpfPassageiro = dadosPassageiro[1];

                for (Passageiro passageiro : passageiros.getCadastros()) {
                    if (passageiro.getCpf().equals(cpfPassageiro)) {
                        String[] dadosParada = dadosEmbarque[1].split(";");
                        String nomeParada = dadosParada[1];

                        for (Trecho trecho : trechos.getCadastros()) {
                            if (trecho.getOrigem().getNome().equals(nomeParada)) {
                                embarque = new Embarque(passageiro, trecho.getOrigem());
                                embarques.add(embarque);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de embarques: " + e.getMessage());
        }
    }
}
