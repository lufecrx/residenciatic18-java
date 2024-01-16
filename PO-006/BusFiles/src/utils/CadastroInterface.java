package utils;

import java.util.List;
import java.util.Scanner;

public interface CadastroInterface {
    
    public void cadastrar(Scanner scanner);    
    public void exibir();
    public void salvar(List<?> cadastros);
    public void carregar();
}
