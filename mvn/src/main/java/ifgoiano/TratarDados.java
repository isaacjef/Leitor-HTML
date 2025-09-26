package ifgoiano;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//https://www.baeldung.com/java-matcher-find-vs-matches

public class TratarDados {
    
    //public void read(BufferedReader reader) {
    public void readTxt() throws FileNotFoundException {
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
        Scanner sc = new Scanner(new File("/eventos_ifgoiano.txt"));

        //variavel para controlar o switch
        int status = 0;
        while (sc.hasNextLine()) {
            String texto = sc.nextLine();

            switch (status) {
                case 0:
                    Matcher matcherParticipante = regexParticipante.matcher(texto);
                    if (matcherParticipante.find()) {
                        System.out.println(matcherParticipante.group());
                        status = 1;
                    }
                    break;
                case 1:
                    Matcher matcherImagem = regexImagem.matcher(texto);
                    if (matcherImagem.find()) {
                        System.out.println(matcherImagem.group());
                        status = 2;
                    }
                    break;
                case 2:
                    Matcher matcherNome = regexNome.matcher(texto);
                    if (matcherNome.find()) {
                        System.out.println("Nome" + matcherNome.group(0));
                        status = 3;
                    }
                    break;
                case 3:
                    Matcher matcherInstituicao = regexInstituicao.matcher(texto);
                    if (matcherInstituicao.find()) {
                        System.out.println(matcherInstituicao.group());
                        status = 4;
                    }
                    break;
                case 4:
                    Matcher matcherEmail = regexEmail.matcher(texto);
                    if (matcherEmail.find()) {
                        System.out.println(matcherEmail.group());
                        System.out.println("");
                        status = 0;
                    }
                    break;
            }
        }

        sc.close();
    }
}
