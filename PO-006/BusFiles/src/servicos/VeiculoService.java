package servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Veiculo;
import utils.CadastroInterface;
import utils.DuplicataException;

public class VeiculoService implements CadastroInterface {

    private List<Veiculo> veiculos;

    public VeiculoService() {
        this.veiculos = new ArrayList<>();
    }
    
    @Override
    public void cadastrar(Scanner scanner) {
        
        System.out.println("Cadastrando veiculo");
        
        
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        
        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        try {
            if(validarPlaca(veiculos, placa)) {
                Veiculo veiculo = new Veiculo(placa, marca, modelo);
                veiculos.add(veiculo);
                System.out.println("Veiculo cadastrado com sucesso!");
                return;
            }
        } catch (DuplicataException e) {
            System.out.println("Erro: " + e.getMessage());
        }        
    }

    @Override
    public List<Veiculo> getCadastros() {
        return veiculos;
    }

    private boolean validarPlaca(List<Veiculo> veiculos, String placa) throws DuplicataException {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                throw new DuplicataException("Já existe um veículo com a mesma placa");
            }
        }
        return true;
    }
    
    @Override
    public void exibir() {
        if(veiculos.isEmpty()) {
            System.out.println("Nenhum veiculo encontrado");
            return;
        }

        for (Veiculo veiculo : veiculos) {
            System.out.println("Placa: " + veiculo.getPlaca() + " | Marca: " + veiculo.getMarca() + " | Modelo: " + veiculo.getModelo());
        }
    }
}
