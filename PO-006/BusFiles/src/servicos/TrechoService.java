package servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Parada;
import entidades.Trecho;
import utils.CadastroInterface;
import utils.CadastroInvalidoException;
import utils.DuplicataException;
import utils.ValorInvalidoException;

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

        Parada paradaDeOrigem;
        Parada paradaDeDestino;
        try {
            System.out.print("Origem: ");
            paradaDeOrigem = paradaService.cadastrar(scanner);
            
            System.out.print("Destino: ");
            paradaDeDestino = paradaService.cadastrar(scanner);
        } catch (DuplicataException | CadastroInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }
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

    @Override
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
