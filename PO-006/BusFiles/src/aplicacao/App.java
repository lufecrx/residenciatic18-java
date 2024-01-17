package aplicacao;

import java.util.Scanner;

import servicos.EmbarqueService;
import servicos.JornadaService;
import servicos.MotoristaService;
import servicos.ParadaService;
import servicos.PassageiroService;
import servicos.TrajetoService;
import servicos.TrechoService;
import servicos.VeiculoService;
import utils.ListaVaziaException;

public class App {
    
    private VeiculoService veiculoService;
    private MotoristaService motoristaService;
    private ParadaService paradaService;
    private PassageiroService passageiroService;
    private TrechoService trechoService;
    private EmbarqueService embarqueService;
    private TrajetoService trajetoService;
    private JornadaService jornadaService;

    public static void main(String[] args) {
        App app = new App();
        app.startServices();
        app.loadData();

        Scanner scanner = new Scanner(System.in);
        
        while (app.menuPrincipal(scanner)) {
            System.out.println();
        }
    }

    public void startServices() {
        veiculoService = new VeiculoService();
        motoristaService = new MotoristaService();
        paradaService = new ParadaService();
        passageiroService = new PassageiroService();
        trechoService = new TrechoService(paradaService);
        embarqueService = new EmbarqueService(trechoService, passageiroService);
        trajetoService = new TrajetoService(trechoService);
        jornadaService = new JornadaService(trajetoService, motoristaService, veiculoService);
    }

    public void loadData() {
        veiculoService.carregar();
        motoristaService.carregar();
        passageiroService.carregar();
        trechoService.carregar();
        embarqueService.carregar();
        trajetoService.carregar();
        jornadaService.carregar();
    }

    public boolean menuPrincipal(Scanner scanner) {
        System.out.println("Selecione uma opção:");
        System.out.println("1 - Menu de cadastro");
        System.out.println("2 - Menu de consulta");
        System.out.println("0 - Sair");

        System.out.print("Insira o número da opção desejada: ");
        String opcao = scanner.next();
        
        switch (opcao) {
            case "1":
                menuCadastro(scanner);
                break;
            case "2":
                menuConsulta(scanner);
                break;
            case "0":
                System.out.println("Saindo...");
                return false;
            default:
                System.out.println("Opcão inválida");
                break;
        }

        return true;
    }

    public void menuCadastro(Scanner scanner) {
        System.out.println("Selecione uma opção:");
        System.out.println("1 - Cadastrar jornada");
        System.out.println("2 - Cadastrar motorista");
        System.out.println("3 - Cadastrar veiculo");
        System.out.println("4 - Cadastrar passageiro");
        System.out.println("6 - Cadastrar trecho");
        System.out.println("7 - Cadastrar trajeto");
        System.out.println("8 - Cadastrar embarque");
        System.out.println("0 - Sair");

        System.out.print("Insira o número da opção desejada: ");
        String opcao = scanner.next();
        scanner.nextLine();

        switch (opcao) {
            case "1":
                jornadaService.cadastrar(scanner);
                break;
            case "2":
                motoristaService.cadastrar(scanner);
                break;
            case "3":
                veiculoService.cadastrar(scanner);
                break;
            case "4":
                passageiroService.cadastrar(scanner);
                break;
            case "6":
                trechoService.cadastrar(scanner);
                break;
            case "7":
                trajetoService.cadastrar(scanner);
                break;
            case "8":
                embarqueService.cadastrar(scanner);
                break;
            case "0":
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opcão inválida");
                break;
        }
    }

    public void menuConsulta(Scanner scanner) {
        System.out.println("Selecione uma opção:");
        System.out.println("1 - Consultar jornadas");
        System.out.println("2 - Consultar motoristas");
        System.out.println("3 - Consultar veiculos");
        System.out.println("4 - Consultar passageiros");
        System.out.println("5 - Consultar trechos");
        System.out.println("6 - Consultar trajetos");
        System.out.println("7 - Consultar jornadas");
        System.out.println("8 - Consultar embarques");
        System.out.println("0 - Sair");

        System.out.print("Insira o número da opção desejada: ");
        String opcao = scanner.next();
        
        try {
            switch (opcao) {
                case "1":
                    jornadaService.exibir();
                    break;
                case "2":
                    motoristaService.exibir();
                    break;
                case "3":
                    veiculoService.exibir();
                    break;
                case "4":  
                    passageiroService.exibir();
                    break;
                case "5":  
                    trechoService.exibir();
                    break;
                case "6":   
                    trajetoService.exibir();
                    break;
                case "7":
                    jornadaService.exibir();
                    break;
                case "8":
                    embarqueService.exibir();
                    break;
                case "0":
                    System.out.println("Saindo...");
                    break;  
                default:
                    System.out.println("Opcão inválida");
                    break;
            }
        } catch (ListaVaziaException e) {
            System.out.println(e.getMessage());
        }
    }

}
