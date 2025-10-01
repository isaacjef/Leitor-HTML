package ifgoiano;

import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

public class TratarDados {

    public ArrayList<Palestrante> readTxt() throws FileNotFoundException, URISyntaxException, IOException {

        //Definição das expressões regulares, para filtrar os dados desejados.
        Pattern regexParticipante = Pattern.compile("id=\"Palestrante\\d+\"");
        Pattern regexImagem = Pattern.compile("<img src=\"\\/.+\\.(png|jpg)\" alt=\"Palestrante\"");
        Pattern regexNome = Pattern.compile("<h4>.+");
        Pattern regexInstituicao = Pattern.compile("<h6>.+");
        Pattern regexEmail = Pattern.compile("<p>.+");
        //O scanner lê cada linha até o fim -> Após ler o primeiro participante, já garantimos que o nome (h4), o instituto (h6)
        //E os demais dados serão os respectivos a cada participante.

        String diretorioProjeto = System.getProperty("user.dir");
        Scanner sc = new Scanner(new File(diretorioProjeto + "/eventos_ifgoiano.txt"));

        ArrayList<Palestrante> array_aux = new ArrayList<>();
        Palestrante palestrante = new Palestrante("", "", "", "", "");

        //variavel de controle do switch
        int status = 0;

        //Leitura linha por linha do arquivo txt
        while (sc.hasNextLine()) {
            String texto = sc.nextLine();

            switch (status) {
                case 0 -> {
                    Matcher matcherParticipante = regexParticipante.matcher(texto);
                    if (matcherParticipante.find()) {
                        String[] id = matcherParticipante.group(0).split("\"Palestrante|\"");

                        palestrante.setId(id[1]);
                        status = 1;
                    }
                }
                case 1 -> {
                    Matcher matcherImagem = regexImagem.matcher(texto);
                    if (matcherImagem.find()) {
                        // /media/static/palestrantes/[nome...].png
                        String[] imagem = matcherImagem.group(0).split("<img src=\"" + "|\" alt=\"Palestrante\"");
                        String urlImg = "https://eventos.ifgoiano.edu.br/" + imagem[1];
                        String[] palestranteImg = imagem[1].split("/media/static/palestrantes/"+"|/static//assets/images/"+ "|.png");
                        
                        String diretorio = this.baixarImagem(urlImg, palestranteImg[1]);
                        if (diretorio != null) {
                            palestrante.setDiretorioImage(diretorio);
                        } else {
                            palestrante.setDiretorioImage("Sem diretório");
                        }

                        status = 2;
                    }
                }
                case 2 -> {
                    Matcher matcherNome = regexNome.matcher(texto);
                    if (matcherNome.find()) {
                        String[] name = matcherNome.group(0).split("<h4>|</h4>");

                        palestrante.setName(name[1]);
                        status = 3;
                    }
                }
                case 3 -> {
                    Matcher matcherInstituicao = regexInstituicao.matcher(texto);
                    if (matcherInstituicao.find()) {
                        String[] work = matcherInstituicao.group(0).split("<h6>|</h6>");

                        palestrante.setWork(work[1]);
                        status = 4;
                    }
                }
                case 4 -> {
                    Matcher matcherEmail = regexEmail.matcher(texto);
                    if (matcherEmail.find()) {
                        String[] email = matcherEmail.group(0).split("<p>|</p>");

                        palestrante.setEmail(email[1]);
                        array_aux.add(new Palestrante(palestrante.getId(), palestrante.getName(), palestrante.getWork(), palestrante.getEmail(), palestrante.getDiretorioImage()));

                        status = 0;
                    }
                }
            }
        }
        sc.close();

        return new ArrayList<>(array_aux);
    }

    public void baixarHTML(String urlPagina, String nomeArquivo) {
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

    public String baixarImagem(String urlImagem, String nomeImagem) throws URISyntaxException, IOException {
        File arquivo = null;
        RenderedImage imagem;
        String diretorioProjeto = System.getProperty("user.dir");
        File arquivoImg = new File(diretorioProjeto + "/download/" + nomeImagem + ".png");

        if(arquivoImg.exists()){
            System.out.println("Imagem já existe!");
            return arquivoImg.toString();
        } else {
            try{
                URL url = new URI(urlImagem).toURL();
                imagem = ImageIO.read(url);
            } catch(IIOException e) {
                System.err.println("\nErro no carregamento da URL -> javax.imageio.IIOException: Can't get input stream from URL!");
                URL url = new URI("https://ifgoiano.edu.br/home/images/TRIN/selecoes/pss2026_tec_tri/noticia_2026.png").toURL();
                imagem = ImageIO.read(url);
            }
            
            try {
                Path caminhoArquivo = Paths.get(nomeImagem + ".png");
                String caminhoPasta = System.getProperty("user.dir") + File.separator + "download\\";
                arquivo = new File(caminhoPasta + caminhoArquivo);

                //Caso a URL não for inválida, e não possuir nenhuma imagem.
                if (imagem == null) {
                    System.err.println("URL inválida! -> java.lang.IllegalArgumentException: image == null!");
                    URL url = new URI("https://ifgoiano.edu.br/home/images/TRIN/selecoes/pss2026_tec_tri/noticia_2026.png").toURL();
                    imagem = ImageIO.read(url);
                }

                boolean sucesso = ImageIO.write(imagem, "png", arquivo);

                if (sucesso) {
                    System.out.println("Imagem salva com sucesso em: " + arquivo.getAbsolutePath());
                } else {
                    System.out.println("ERRO! Imagem não foi salva.");
                    arquivo = null;
                }
            } catch(IllegalArgumentException e) {
                e.printStackTrace();
            }  catch(Exception e){
                e.printStackTrace();
            }
        }

        return arquivo.getAbsolutePath();
    }
}
