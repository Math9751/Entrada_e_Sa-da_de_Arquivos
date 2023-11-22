import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LeituraArquivo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String nomeArquivo;

            do {
                System.out.println("Digite o nome do arquivo de leitura:");
                nomeArquivo = scanner.nextLine();

                try {
                    lerArquivo(nomeArquivo);
                } catch (ArquivoNaoEncontradoException e) {
                    System.out.println("Arquivo não encontrado. Por favor, digite um nome de arquivo válido.");
                } catch (IOException e) {
                    System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                }
            } while (true);
        }
    }

    private static void lerArquivo(String nomeArquivo) throws IOException, ArquivoNaoEncontradoException {
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            int caractere;
            while ((caractere = leitor.read()) != -1) {
                System.out.print(caractere + " ");
            }
            System.out.println("\nLeitura do arquivo concluída.");
        } catch (IOException e) {
            throw new ArquivoNaoEncontradoException("Arquivo não encontrado: " + nomeArquivo);
        }
    }

    // Exceção personalizada para indicar que o arquivo não foi encontrado
    static class ArquivoNaoEncontradoException extends Exception {
        public ArquivoNaoEncontradoException(String mensagem) {
            super(mensagem);
        }
    }
}
