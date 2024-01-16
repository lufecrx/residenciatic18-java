package servicos;

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

public class EmbarqueService implements CadastroInterface {

    private List<Embarque> embarques;
    private TrechoService trechos;
    private PassageiroService passageiros;

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
            System.out.print("Selecione o número correspondente do trecho (parada de origem) para associar ao embarque: ");
            trechoIdx = scanner.nextInt();
            validarTrechoIdx(trechoIdx);
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida para trecho");
            scanner.nextLine();
            return;
        } 
        catch (CadastroInvalidoException e) {
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
            System.out.println(index++ + "- " + embarque.getPassageiro().getNome() + " - " + embarque.getParada().getNome());
        }

        System.out.println("Embarques encontrados: " + embarques.size());
    }

}
