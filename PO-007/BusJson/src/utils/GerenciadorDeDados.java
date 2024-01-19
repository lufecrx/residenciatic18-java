package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.google.gson.Gson;

public class GerenciadorDeDados {

    public static void salvar(String nomeDoArquivo, List<?> cadastros) {
        // Limpar o arquivo
        try {
            FileWriter writer = new FileWriter("arquivos/" + nomeDoArquivo + ".json");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao limpar o arquivo: " + e.getMessage());
        }

        try {
            Gson gson = new Gson();
            String json = gson.toJson(cadastros);

            Path path = file.toPath();
            Files.write(path, json.getBytes());

            System.out.println("Estudantes escritos com sucesso.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever os estudantes.");
        }
        
        // Salvar dados em um arquivo
        try {
            FileWriter writer = new FileWriter("arquivos/"+ nomeDoArquivo + ".txt");
            for (Object cadastro : cadastros) {
                writer.write(cadastro.toString());
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static void criarArquivoInexistente(String arquivo) throws IOException {
        // Verificar se o diretório existe e se não existir, criar
        File diretorio = new File("arquivos");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }

        // Criar arquivo se ele não existir
        File file = new File(arquivo);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static void estaVazio(List<?> cadastros, String nome) throws ListaVaziaException {
        if (cadastros.isEmpty()) {
            throw new ListaVaziaException("Nenhum cadastro de " + nome + " encontrado.");
        }
    }
    
}
