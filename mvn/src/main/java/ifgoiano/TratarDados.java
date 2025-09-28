package ifgoiano;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//https://www.baeldung.com/java-matcher-find-vs-matches

public class TratarDados {

    public ArrayList<Palestrante> readTxt() throws FileNotFoundException {

        //Definição das expressões regulares
        Pattern regexParticipante = Pattern.compile("id=\"Palestrante\\d+\"");
        //alt=" + regexParticipante utilizado para evitar que outras imagens que nao sejam a dos participantes, sejam coletadas.
        Pattern regexImagem = Pattern.compile("<img src=\"\\/.+\\.(png|jpg)\" alt=\"Palestrante\"");

        //Tentei usar esse padrão pra encontrar a div onde o h4 e o h6 estavam, mas nao precisa, pois o scanner lê cada linha até o fim
        //Após ler o primeiro participante, ja garantimos que o nome (h4) e o instituto (h6) serão os respectivos a cada participante
        //Pattern regexModal = Pattern.compile("<div claas=\"modal-title\">, <h4>(.+), <h6>(.+)");
        Pattern regexNome = Pattern.compile("<h4>.+");
        Pattern regexInstituicao = Pattern.compile("<h6>.+");

        Pattern regexEmail = Pattern.compile("<p>.+");

        //Ler o arquivo .txt convertido
        //Podemos passar o diretório via parâmetro
        String diretorioProjeto = System.getProperty("user.dir");
        Scanner sc = new Scanner(new File(diretorioProjeto + "/eventos_ifgoiano.txt"));

        //variavel para controlar o switch
        int status = 0;
        ArrayList<Palestrante> array_aux = new ArrayList<>();
        Palestrante palestrante = new Palestrante("", "", "", "", "imagem");

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
                        //System.out.println(matcherImagem.group());
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
}
