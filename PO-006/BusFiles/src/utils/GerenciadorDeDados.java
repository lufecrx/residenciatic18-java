package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GerenciadorDeDados {

    public static void salvar(String nomeDoArquivo, List<?> cadastros) {
        // Limpar o arquivo
        try {
            FileWriter writer = new FileWriter("arquivos/" + nomeDoArquivo + ".txt");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao limpar o arquivo: " + e.getMessage());
        }

        // Salvar dados em um arquivo
        try {
            FileWriter writer = new FileWriter("arquivos/"+ nomeDoArquivo + ".txt");
            for (Object cadastro : cadastros) {
                writer.write(cadastro.toString());
                writer.write(System.lineSeparator());
            }
            writer.close();
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
    
}
