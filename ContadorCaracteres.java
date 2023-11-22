import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ContadorCaracteres {
    public static void main(String[] args) {
        String nomeArquivo = "texto.txt"; // Substitua pelo nome do seu arquivo

        try {
            int[] estatisticas = contarCaracteres(nomeArquivo);
            System.out.println("Quantidade de vogais: " + estatisticas[0]);
            System.out.println("Quantidade de consoantes: " + estatisticas[1]);
            System.out.println("Quantidade total de caracteres: " + estatisticas[2]);
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }

    private static int[] contarCaracteres(String nomeArquivo) throws IOException {
        int[] estatisticas = new int[3]; // Índice 0: vogais, Índice 1: consoantes, Índice 2: total de caracteres

        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                contarCaracteresNaLinha(linha, estatisticas);
            }
        }

        return estatisticas;
    }

    private static void contarCaracteresNaLinha(String linha, int[] estatisticas) {
        linha = linha.toLowerCase(); // Converter para minúsculas para simplificar a contagem

        for (int i = 0; i < linha.length(); i++) {
            char caractere = linha.charAt(i);
            if (Character.isLetter(caractere)) {
                estatisticas[2]++; // Contar caracteres (letras)
                if (ehVogal(caractere)) {
                    estatisticas[0]++; // Contar vogais
                } else {
                    estatisticas[1]++; // Contar consoantes
                }
            }
        }
    }

    private static boolean ehVogal(char caractere) {
        return "aeiou".indexOf(caractere) != -1;
    }
}
