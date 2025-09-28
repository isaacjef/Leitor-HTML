package ifgoiano;

import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        String urlPagina = "https://eventos.ifgoiano.edu.br/integra2025/"; // Substitua pelo URL da página que quer baixar
        String nomeArquivo = "/eventos_ifgoiano.txt";
        TratarDados tratador = new TratarDados();
        
        try {
            
            //baixarHTML(urlPagina, nomeArquivo);
            
            ArrayList<Palestrante> a = tratador.readTxt();
            a.forEach(v -> 
                System.out.println(v.getName())
            );

            Database event = new Database("event");

            //Cria conexão com o banco de dados
            //event.connect();
            

        } catch (Exception e) {
            System.err.println("Generic Error: " + e.getMessage());
        }
    }

    //Baixar página html e passar para txt
    public static void baixarHTML(String urlPagina, String nomeArquivo) {
        try {
            URL url = new URI(urlPagina).toURL();
            BufferedWriter writer;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                writer = new BufferedWriter(new FileWriter(nomeArquivo));
                String linha;
                while ((linha = reader.readLine()) != null) {
                    writer.write(linha);
                    writer.newLine();
                }
                System.out.println("Página baixada com sucesso para: " + nomeArquivo);
            }

            writer.close();
        } catch (IOException e) {
           System.err.println("Error reading the file: " + e.getMessage());
        } catch (URISyntaxException e) {
           System.err.println("Error na sintaxe URI: " + e.getMessage());
        }
    }

    public static void baixarImagen(String urlImagem, String nomeImagem){
        try{
            URL url2 = new URI(urlImagem).toURL();
            RenderedImage imagem = ImageIO.read(url2);
            //System.out.println("Imagem : " + imagem);
            Path caminhoArquivo = Paths.get(nomeImagem + ".png");
            String caminhoPasta = System.getProperty("user.dir") + File.separator + "download\\";
            File arquivo = new File(caminhoPasta + caminhoArquivo);

            boolean sucesso = ImageIO.write(imagem, "png", arquivo);

            if (sucesso) {
                System.out.println("Imagem salva com sucesso em: " + arquivo.getAbsolutePath());
            } else {
                System.out.println("ERRO");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        // Criar catch para pegar imagem padrão quando não houver imagem do poalestrante em questão.
    }
}
