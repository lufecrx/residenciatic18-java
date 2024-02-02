package aplicacao;

import java.util.Scanner;

import servicos.ClienteService;
import servicos.FaturaService;
import servicos.ImovelService;

public class App {

    private ClienteService clienteService;
    private ImovelService imovelService;
    private FaturaService faturaService;

	public static void main(String[] args) {
		App program = new App();
		program.Run();		
	}

    public void carregarEntidades(Scanner scanner) {
        // Carregar dados
        this.clienteService = new ClienteService(scanner);
        this.imovelService = new ImovelService(scanner);
        this.faturaService = new FaturaService(scanner, imovelService);
    }
	
	private void Run() {
        Scanner scanner = new Scanner(System.in);
        carregarEntidades(scanner);
		
		// MenuPagamentos menuPagamentos = new MenuPagamentos(faturas);
		// MenuFalhasReparos menuFalhasReparos = new MenuFalhasReparos(imoveis);
		
        int opcao;
        do {
            System.out.println("==== Menu Principal ====");
            System.out.println("1. Gestão de Clientes");
            System.out.println("2. Gestão de Imóveis");
            System.out.println("3. Pagamentos");
            System.out.println("4. Faturas");
            System.out.println("5. Falhas e Reparos");
            System.out.println("0. Sair");
            System.out.print("Escolha a opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

            switch (opcao) {
                case 1:
                    clienteService.exibirMenu();
                    break;
                case 2:
                    imovelService.exibirMenu();
                    break;
                case 3:
                    // menuPagamentos.exibirMenu();
                    break;
                case 4:
                    faturaService.exibirMenu();
                    break;
                case 5:
                    // menuFalhasReparos.exibirMenu();
                    break;
                case 0:
                    System.out.println("Encerrando a aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

	}
}
