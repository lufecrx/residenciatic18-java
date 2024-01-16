package servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Parada;
import entidades.Trecho;
import utils.CadastroInterface;
import utils.DuplicataException;

public class TrechoService implements CadastroInterface{
    
    private List<Trecho> trechos;
    private ParadaService paradaService;

    public TrechoService(ParadaService paradaService) {
        this.trechos = new ArrayList<>();
        this.paradaService = paradaService;
    }

    @Override
    public void cadastrar(Scanner scanner) {
        
        System.out.println("Cadastrando trecho");
        List<Parada> paradas = paradaService.getCadastros();

        System.out.println("Origem: ");
        String origem = scanner.nextLine();
        Parada paradaDeOrigem = new Parada(origem);
        try {
            validarParada(paradas, paradaDeOrigem);    
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        System.out.println("Destino: ");
        String destino = scanner.nextLine();
        Parada paradaDeDestino = new Parada(destino);
        try {
            validarParada(paradas, paradaDeDestino);
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        try {
            validarTrecho(trechos, paradaDeOrigem, paradaDeDestino);
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        System.out.println("Minutos: ");
        int minutos = scanner.nextInt();
        
        if(validarMinutos(minutos)) {
            Trecho trecho = new Trecho(paradaDeOrigem, paradaDeDestino, minutos);
            trechos.add(trecho);
            paradas.add(paradaDeOrigem);
            paradas.add(paradaDeDestino);
        }
    }

    @Override
    public List<Trecho> getCadastros() {
        return trechos;
    }

    public boolean validarParada(List<Parada> paradas, Parada parada) throws DuplicataException{
        for (Parada p : paradas) {
            if (p.getNome().equals(parada.getNome())) {
                throw new DuplicataException("Parada duplicada");
            }
        }
        return true;
    }

    public boolean validarTrecho(List<Trecho> trechos, Parada paradaDeOrigem, Parada paradaDeDestino) throws DuplicataException{
        for (Trecho trecho : trechos) {
            if (trecho.getOrigem().equals(paradaDeOrigem) && trecho.getDestino().equals(paradaDeDestino)) {
                throw new DuplicataException("Trecho duplicado");
            }
        }
        return true;
    }

    public boolean validarMinutos(int minutos) {
        return minutos > 0;
    }

    public void exibir() {
        if(trechos.isEmpty()) {
            System.out.println("Nenhum trecho encontrado.");
            return;
        }

        int index = 1;
        for (Trecho trecho : this.trechos) {
            System.out.println(index + "- " + trecho.getOrigem() + " para " + trecho.getDestino());
            index++;
        }
    }
}
