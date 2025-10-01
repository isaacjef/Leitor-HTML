package ifgoiano;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Declaração de variaveis
        String urlPagina = "https://eventos.ifgoiano.edu.br/integra2025/";
        TratarDados tratador = new TratarDados();
        Database event = new Database("event");
        ArrayList<Palestrante> pales = new ArrayList<>();
        
        try {
            tratador.baixarHTML(urlPagina, "eventos_ifgoiano.txt");

            // Criar conexão com o banco de dados
            event.connect();
            
            pales = tratador.readTxt();
            event.inserirPalestrantes(pales);
            System.out.println("Conexão; "+ event.connect());
            event.inserirPalestrantes(pales);
            event.listarPalestrantes();
            //event.deletarTabela();
            System.out.println("Desconexão; "+ event.disconnect());
        } catch (Exception e) {
            System.err.println("Generic Error: " + e.getMessage());
        }
    }
}
