package servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Jornada;
import entidades.Motorista;
import entidades.Trajeto;
import entidades.Veiculo;
import utils.CadastroInterface;
import utils.CadastroInvalidoException;

public class JornadaService implements CadastroInterface {
    
    private List<Jornada> jornadas;
    private TrajetoService trajetos;
    private MotoristaService motoristas;
    private VeiculoService veiculos;

    public JornadaService(TrajetoService trajetoService, MotoristaService motoristaService, VeiculoService veiculoService) {
        this.jornadas = new ArrayList<>();
        this.trajetos = trajetoService;
        this.motoristas = motoristaService;
        this.veiculos = veiculoService;
    }

    @Override
    public void cadastrar(Scanner scanner) {
        
        System.out.println("Cadastrando jornada");

        System.out.println("Trajetos: ");
        trajetos.exibir();

        try {
            List<Trajeto> trajetosDaJornada = adicionarTrajetos(scanner);
            Motorista motoristaAssociado = associarMotorista(scanner);
            Veiculo veiculoAssociado = associarVeiculo(scanner);
            
            Jornada jornada = new Jornada(trajetosDaJornada, motoristaAssociado, veiculoAssociado);
            jornadas.add(jornada);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
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

    @Override
    public List<?> getCadastros() {
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

}
