import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MediaNotas {
    public static void main(String[] args) {
        String nomeArquivo = "notas.txt";

        try {
            double[] notas = lerNotasDoArquivo(nomeArquivo);
            double media = calcularMedia(notas);

            System.out.println("Notas lidas do arquivo:");
            for (double nota : notas) {
                System.out.println(nota);
            }

            System.out.println("\nMédia das notas: " + media);

            salvarMediaNoArquivo(nomeArquivo, media);
            System.out.println("Média salva no arquivo " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }

    private static double[] lerNotasDoArquivo(String nomeArquivo) throws IOException {
        double[] notas = new double[4];

        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            for (int i = 0; i < 4; i++) {
                String linha = leitor.readLine();
                notas[i] = Double.parseDouble(linha);
            }
        }

        return notas;
    }

    private static double calcularMedia(double[] notas) {
        double soma = 0;

        for (double nota : notas) {
            soma += nota;
        }

        return soma / notas.length;
    }

    private static void salvarMediaNoArquivo(String nomeArquivo, double media) throws IOException {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(nomeArquivo, true))) {
            // Adicionando a média ao final do arquivo
            escritor.println(media);
        }
    }
}
