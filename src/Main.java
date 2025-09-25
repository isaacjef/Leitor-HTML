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
import java.awt.image.RenderedImage;;

public class Main {

    public static void main(String[] args) {
        String urlDaPagina = "https://eventos.ifgoiano.edu.br/integra2025/"; // Substitua pelo URL da página que quer baixar
        String nomeArquivo = "pagina_baixada.txt";

        // Exemplo aplicação da função baixarImagem
        // OBS: Ainda precisa tratar quando não houver imagem do palestrante
        String urlImagem = "https://eventos.ifgoiano.edu.br/media/static/palestrantes/Aleksander_Westphal_Muniz_oxqEnwS.png";
        for(int i = 0; i<2; i++)
            baixarImagen(urlImagem, ""+i);
        
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

            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Função para baixar imagem para pasta "download" e 
     * pode retornar o caminho da imagem para armazenamento na classe Palestrante
     * 
     * Deve ser chamada sempre que encontrar um palestrante para baixar sua imagem e
     * armazenar o diretório salvo.
     * 
     * @param urlIlagem: Tipo string
     * @param nomeImagem: referência para não criar imagem com nome duplicado
     * 
     * @return arquivo.getAbsolutePath(): É a String que armazena o diretório da imagem salva
     */
    public static void baixarImagen(String urlImagem, String nomeImagem){
        try{
            URL url2 = new URL(urlImagem);
            RenderedImage imagem = ImageIO.read(url2);
            System.out.println("Imagem : " + imagem);
            Path caminhoArquivo = Paths.get("imagem" + nomeImagem + ".png");
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
        // Criar catch para pegar imagem padrão quando não houver imagem ao poalestrante em questão.
        
    }
}
