package src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;;

public class Main {

    public static void main(String[] args) {
        //connect();
        Palestrante pale = new Palestrante("1", "null", "nullt", "null@gmail", "null.png");
        Palestrante pale1 = new Palestrante("2", "alexandre", "casa", "alexandre@gmail", "alexandre_imagem.png");
        ArrayList<Palestrante> pales = new ArrayList<>();
        pales.add(pale);
        pales.add(pale1);

        Database event = new Database("event2");
        System.out.println("Conexão; "+ event.connect());
        event.deletarTabela();
        System.out.println("Desconexão; "+ event.desconnect());
        
        System.out.println("Conexão; "+ event.connect());
        event.criarTabela();
        event.inserirPalestrantes(pales);

        System.out.println("Desconexão; "+ event.desconnect());

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
     * @return arquivo.getAbsolutePath(); É a String que armazena o diretório da imagem salva
     *         ↑ pode servir de retorno ↑
     */
    public static String baixarImagen(String urlImagem, String nomeImagem){
        try{
            URL url2 = new URL(urlImagem);
            RenderedImage imagem = ImageIO.read(url2);
            System.out.println("Imagem : " + imagem);
            Path caminhoArquivo = Paths.get(nomeImagem + ".png");
            String caminhoPasta = System.getProperty("user.dir") + File.separator + "download\\";
            File arquivo = new File(caminhoPasta + caminhoArquivo);

            boolean sucesso = ImageIO.write(imagem, "png", arquivo);

            if (sucesso) {
                System.out.println("Imagem salva com sucesso em: " + arquivo.getAbsolutePath());
            } else {
                System.out.println("ERRO");
            }

            return arquivo.getAbsolutePath();
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
        // Criar catch para pegar imagem padrão quando não houver imagem ao poalestrante em questão.
        
    }

}
