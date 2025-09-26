package ifgoiano;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        String urlDaPagina = "https://eventos.ifgoiano.edu.br/integra2025/"; // Substitua pelo URL da página que quer baixar
        String nomeArquivo = "eventos_ifgoiano.txt";

        TratarDados tratador = new TratarDados();
        
        try {
            //Baixar página html e passar para txt
            //Recomendação do VSCODE: Utilizar classe URI ao invés da URL.
            URL url = new URI(urlDaPagina).toURL();
            BufferedWriter writer;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                writer = new BufferedWriter(new FileWriter(nomeArquivo));
                String linha;
                while ((linha = reader.readLine()) != null) {
                    writer.write(linha);
                    writer.newLine();
                }
                System.out.println("Página baixada com sucesso para: " + nomeArquivo); // MBallem
            }

            //tratador.readTxt();

            writer.close();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (URISyntaxException e) {
            System.err.println("Error na sintaxe URI: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Generic Error: " + e.getMessage());
        }
    }
}
