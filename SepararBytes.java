import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SepararBytes {
    public static void main(String[] args) {
        String arquivoEntrada = "entrada.txt";
        String arquivoMaior = "maior.txt";
        String arquivoMenor = "menor.txt";

        try {
            separarBytes(arquivoEntrada, arquivoMaior, arquivoMenor);
            System.out.println("Separação concluída com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao processar os arquivos: " + e.getMessage());
        }
    }

    private static void separarBytes(String arquivoEntrada, String arquivoMaior, String arquivoMenor) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(arquivoEntrada);
             FileOutputStream outputStreamMaior = new FileOutputStream(arquivoMaior);
             FileOutputStream outputStreamMenor = new FileOutputStream(arquivoMenor)) {

            int byteLido;
            while ((byteLido = inputStream.read()) != -1) {
                if (byteLido > 128) {
                    outputStreamMaior.write(byteLido);
                } else {
                    outputStreamMenor.write(byteLido);
                }
            }
        }
    }
}
