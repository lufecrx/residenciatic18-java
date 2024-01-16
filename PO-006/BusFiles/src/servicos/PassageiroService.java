package servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Passageiro;
import utils.CadastroInterface;
import utils.CartaoEnum;
import utils.DuplicataException;

public class PassageiroService implements CadastroInterface {
    
    private List<Passageiro> passageiros;

    public PassageiroService() {
        this.passageiros = new ArrayList<>();
    }

    @Override
    public void cadastrar(Scanner scanner) {
        
        System.out.println("Cadastrando passageiro");

        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("CPF: ");
        String cpf = scanner.nextLine();

        CartaoEnum tipoCartao = null;
        
        do {
            // Perguntar o tipo de cartão até ser inserido um tipo válido
            tipoCartao = getTipoCartao(scanner);
        } while (tipoCartao == null);

        System.out.println("Número do cartão: ");
        String numCartao = scanner.next();

        try {
            if(validarCpf(passageiros, cpf) && validarNumCartao(passageiros, numCartao)) {
                Passageiro passageiro = new Passageiro(nome, cpf, tipoCartao, numCartao);
                passageiros.add(passageiro);
            }
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());  
            return;
        }
    }

    @Override
    public List<Passageiro> getCadastros() {
        return passageiros;
    }

    public CartaoEnum getTipoCartao(Scanner scanner) {
        System.out.println("Tipos de cartão: ");
        System.out.println("1 - Estudantil");
        System.out.println("2 - Idoso");
        System.out.println("3 - Transporte");
        
        System.out.println("Escolha o tipo: ");
        int tipo = scanner.nextInt();

        CartaoEnum tipoCartao = null;
        
        switch (tipo) {
            case 1:
                tipoCartao = CartaoEnum.ESTUDANTIL;
                break;
            case 2:
                tipoCartao = CartaoEnum.IDOSO;
                break;
            case 3:
                tipoCartao = CartaoEnum.TRANSPORTE;
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

        return tipoCartao;
    }

    public boolean validarCpf(List<Passageiro> passageiros, String cpf) throws DuplicataException {
        for (Passageiro passageiro : passageiros) {
            if (passageiro.getCpf().equals(cpf)) {
                throw new DuplicataException("Já existe um passageiro com o mesmo CPF.");
            }
        }
        return true;
    }

    public boolean validarNumCartao(List<Passageiro> passageiros, String numCartao) throws DuplicataException {
        for (Passageiro passageiro : passageiros) {
            if (passageiro.getNumCartao().equals(numCartao)) {
                throw new DuplicataException("Já existe um passageiro com o mesmo número de cartão.");
            }
        }
        return true;
    }  

    @Override
    public void exibir() {
        if(passageiros.isEmpty()) {
            System.out.println("Nenhum passageiro encontrado");
            return;
        }

        for (Passageiro passageiro : passageiros) {
            System.out.println("Nome: " + passageiro.getNome() + " | CPF: " + passageiro.getCpf() + " | Cartão: " + passageiro.getCartao() + " | Número do cartão: " + passageiro.getNumCartao());
        }
    }    
}
