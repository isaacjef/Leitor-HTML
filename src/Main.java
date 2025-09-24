package src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;;

public class Main {

    public static void main(String[] args) {
        String urlDaPagina = "https://eventos.ifgoiano.edu.br/integra2025/"; // Substitua pelo URL da página que quer baixar
        String nomeArquivo = "pagina_baixada.txt";

        String urlImagem = "https://eventos.ifgoiano.edu.br/media/static/palestrantes/Aleksander_Westphal_Muniz_oxqEnwS.png";

        
        try {
            //Baixar página html e passar para txt
            URL url = new URL(urlDaPagina);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));

            String linha;
            while ((linha = reader.readLine()) != null) {
                writer.write(linha);
                writer.newLine();
            }

            System.out.println("Página baixada com sucesso para: " + nomeArquivo); // MBallem

            //Imagem
            URL url2 = new URL(urlImagem);
            //BufferedImage imagem = ImageIO.read(url2);
            RenderedImage imagem = ImageIO.read(url2);
            System.out.println("Imagem : " + imagem);
            Path caminhoArquivo = Paths.get("imagem_baixada111.png");
            File arquivo = caminhoArquivo.toFile();
            //File arquivo = new File("C:\\Users\\2023108203030144\\Downloads\\Teste");
            //System.out.println("A | : " + arquivo);

            boolean sucesso = ImageIO.write(imagem, "png", arquivo);

            if (sucesso) {
                System.out.println("Imagem salva com sucesso em: " + arquivo.getAbsolutePath());
            } else {
                System.out.println("ERRO");
            }

            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
