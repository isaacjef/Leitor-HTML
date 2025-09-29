package src;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Declaração de variaveis
        String urlPagina = "https://eventos.ifgoiano.edu.br/integra2025/";
        TratarDados tratador = new TratarDados();
        ArrayList<Palestrante> pales = new ArrayList<>();
        Database event = new Database("event2");

        try {
            tratador.baixarHTML(urlPagina, "eventos_ifgoiano.txt");
            pales = tratador.readTxt();
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Generic Error: " + e.getMessage());
        }

        System.out.println("Conexão; "+ event.connect());
        event.inserirPalestrantes(pales);
        System.out.println("Desconexão; "+ event.desconnect());
        
    }
}
