import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class SepararParesImpares {
    public static void main(String[] args) {
        int quantidadeNumeros = 10;

        // Criando um array de 10 números aleatórios entre 1 e 100
        int[] numeros = gerarNumerosAleatorios(quantidadeNumeros);

        // Ordenando os números de forma crescente
        Arrays.sort(numeros);

        // Separando pares e ímpares
        int[] pares = Arrays.stream(numeros).filter(num -> num % 2 == 0).toArray();
        int[] impares = Arrays.stream(numeros).filter(num -> num % 2 != 0).toArray();

        // Gravando pares no arquivo "pares.txt"
        gravarNumerosNoArquivo("pares.txt", pares);

        // Gravando ímpares no arquivo "impares.txt"
        gravarNumerosNoArquivo("impares.txt", impares);

        System.out.println("Números gravados nos arquivos pares.txt e impares.txt.");
    }

    private static int[] gerarNumerosAleatorios(int quantidade) {
        int[] numeros = new int[quantidade];
        Random random = new Random();

        for (int i = 0; i < quantidade; i++) {
            numeros[i] = random.nextInt(100) + 1; // Números entre 1 e 100
        }

        return numeros;
    }

    private static void gravarNumerosNoArquivo(String nomeArquivo, int[] numeros) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (int numero : numeros) {
                writer.println(numero);
            }
        } catch (IOException e) {
            System.out.println("Erro ao gravar no arquivo: " + e.getMessage());
        }
    }
}
