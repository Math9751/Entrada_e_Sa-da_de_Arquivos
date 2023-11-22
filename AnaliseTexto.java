import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnaliseTexto {
    public static void main(String[] args) {
        String arquivoEntrada = "biblia.txt";
        String arquivoSaida = "resultado.txt";

        try {
            // Leitura do arquivo
            String texto = lerArquivo(arquivoEntrada);

            // Gravação das informações no arquivo de resultado
            gravarInformacoes(texto, arquivoSaida);

            System.out.println("Análise concluída. Resultados gravados em " + arquivoSaida);
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }

    private static String lerArquivo(String nomeArquivo) throws IOException {
        StringBuilder conteudo = new StringBuilder();

        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        }

        return conteudo.toString();
    }

    private static void gravarInformacoes(String texto, String nomeArquivo) throws IOException {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(nomeArquivo))) {
            // Quantidade de palavras que iniciam com: a, e, i, o, u
            Map<Character, Integer> contagemIniciais = new HashMap<>();
            String[] palavras = texto.split("\\s+");

            for (String palavra : palavras) {
                if (!palavra.isEmpty()) {
                    char inicial = Character.toLowerCase(palavra.charAt(0));
                    contagemIniciais.put(inicial, contagemIniciais.getOrDefault(inicial, 0) + 1);
                }
            }

            escritor.println("Quantidade de palavras que iniciam com:");
            for (char inicial : contagemIniciais.keySet()) {
                escritor.println(inicial + ": " + contagemIniciais.get(inicial));
            }

            // Cinco palavras com mais de 5 letras com a maior quantidade de repetições
            Map<String, Integer> contagemPalavras = new HashMap<>();
            Pattern padraoPalavras = Pattern.compile("\\b\\p{Alpha}{6,}\\b");
            Matcher matcher = padraoPalavras.matcher(texto);

            while (matcher.find()) {
                String palavra = matcher.group().toLowerCase();
                contagemPalavras.put(palavra, contagemPalavras.getOrDefault(palavra, 0) + 1);
            }

            escritor.println("\nCinco palavras com mais de 5 letras com a maior quantidade de repetições:");
            contagemPalavras.entrySet().stream()
                    .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
                    .limit(5)
                    .forEach(entry -> escritor.println(entry.getKey() + ": " + entry.getValue()));

            // Quantidade total de palavras no texto
            escritor.println("\nQuantidade total de palavras no texto: " + palavras.length);

            // Quantidade de repetições da vogal à com crase
            int contagemAComCrase = texto.toLowerCase().split("à").length - 1;
            escritor.println("\nQuantidade de repetições da vogal à com crase: " + contagemAComCrase);

            // Palavras antes da palavra 'sol'
            escritor.println("\nPalavras antes da palavra 'sol':");
            imprimirPalavrasAntesOuDepois(texto, "sol", escritor, true);

            // Palavras depois da palavra 'terra'
            escritor.println("\nPalavras depois da palavra 'terra':");
            imprimirPalavrasAntesOuDepois(texto, "terra", escritor, false);

            // Palavras com mais de 5 letras e iniciam com a letra x ou w
            escritor.println("\nPalavras com mais de 5 letras e iniciam com a letra x ou w:");
            Pattern padraoXW = Pattern.compile("\\b[wx]\\p{Alpha}{5,}\\b");
            Matcher matcherXW = padraoXW.matcher(texto);
            while (matcherXW.find()) {
                escritor.println(matcherXW.group());
            }
        }
    }

    private static void imprimirPalavrasAntesOuDepois(String texto, String palavraReferencia,
                                                      PrintWriter escritor, boolean antes) {
        String padrao = "\\b\\p{Alpha}+\\b";
        Pattern padraoPalavras = Pattern.compile(padrao);
        Matcher matcher = padraoPalavras.matcher(texto);

        int posicaoReferencia = -1;

        while (matcher.find()) {
            if (matcher.group().equalsIgnoreCase(palavraReferencia)) {
                posicaoReferencia = matcher.start();
                break;
            }
        }

        if (posicaoReferencia != -1) {
            matcher.reset();

            while (matcher.find()) {
                if (antes && matcher.start() < posicaoReferencia) {
                    escritor.println(matcher.group());
                } else if (!antes && matcher.start() > posicaoReferencia) {
                    escritor.println(matcher.group());
                }
            }
        }
    }
}
