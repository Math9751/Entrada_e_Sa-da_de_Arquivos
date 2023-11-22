import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GravarMensagemEmArquivo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Solicitação da mensagem ao usuário
            System.out.println("Digite a mensagem que deseja gravar no arquivo:");

            // Leitura da mensagem do usuário
            String mensagem = scanner.nextLine();

            // Nome do arquivo onde a mensagem será gravada
            String nomeArquivo = "mensagem.txt";

            // Gravação da mensagem no arquivo
            gravarMensagemNoArquivo(nomeArquivo, mensagem);

            System.out.println("Mensagem gravada no arquivo " + nomeArquivo + ".");
        }
    }

    private static void gravarMensagemNoArquivo(String nomeArquivo, String mensagem) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            // Grava cada caractere da mensagem no arquivo
            for (char caractere : mensagem.toCharArray()) {
                writer.print(caractere);
            }
        } catch (IOException e) {
            System.out.println("Erro ao gravar no arquivo: " + e.getMessage());
        }
    }
}
