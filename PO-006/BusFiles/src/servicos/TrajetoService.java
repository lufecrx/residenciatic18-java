package servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Parada;
import entidades.Passageiro;
import entidades.Trajeto;
import entidades.Trecho;
import utils.CadastroInterface;
import utils.CadastroInvalidoException;

public class TrajetoService implements CadastroInterface{
    
    private List<Trajeto> trajetos;
    private TrechoService trechos;
    private PassageiroService passageiros;

    public TrajetoService(TrechoService trechoService, PassageiroService passageiroService) {
        this.trajetos = new ArrayList<>();
        this.trechos = trechoService;
        this.passageiros = passageiroService;
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

    @Override
    public List<?> getCadastros() {
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
    
    public void cadastrarEmbarque(Scanner scanner) throws CadastroInvalidoException {

        System.out.println("Cadastrando embarque");
        Parada paradaDeOrigem = null;
        Passageiro passageiroAssociado = null;
        
        System.out.println("Trechos: ");
        trechos.exibir();

        System.out.print("Selecione o número correspondente do trecho (parada de origem) para associar ao embarque: ");
        int trecho = scanner.nextInt();

        if (trecho > 0 && trecho <= trechos.getCadastros().size()) {
            System.out.println("Trecho : " + trechos.getCadastros().get(trecho - 1));
            System.out.println("Parada de origem selecionada: " + trechos.getCadastros().get(trecho - 1).getOrigem().getNome());
            paradaDeOrigem = (Parada) trechos.getCadastros().get(trecho - 1).getOrigem();
        }

        if (paradaDeOrigem == null) {
            throw new CadastroInvalidoException("Nenhum trecho selecionado.");
        }

        passageiros.exibir();
        System.out.print("Selecione o número correspondente do passageiro para associar ao embarque: ");
        int passageiro = scanner.nextInt();

        if (passageiro > 0 && passageiro <= passageiros.getCadastros().size()) {
            System.out.println("Passageiro selecionado: " + passageiros.getCadastros().get(passageiro - 1).getNome());
            passageiroAssociado = (Passageiro) passageiros.getCadastros().get(passageiro - 1);
        }

        if (passageiroAssociado == null) {
            throw new CadastroInvalidoException("Nenhum passageiro selecionado.");
        }

        paradaDeOrigem.embarcar(passageiroAssociado);
    }
    

}
