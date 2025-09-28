package ifgoiano;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String urlPagina = "https://eventos.ifgoiano.edu.br/integra2025/"; // Substitua pelo URL da página que quer baixar
        String nomeArquivo = "/eventos_ifgoiano.txt";
        TratarDados tratador = new TratarDados();
        
        try {
            // Baixar página html e converter para txt
            //baixarHTML(urlPagina, nomeArquivo);

            // Cria conexão com o banco de dados
            Database event = new Database("event");
            event.connect();
            
            ArrayList<Palestrante> palestrantes = tratador.readTxt();
            event.inserirPalestrantes(palestrantes);
            //event.deletarTabela();
            event.listarPalestrantes();

            //tratador.baixarImagem("https://ifgoiano.edu.br/home/images/TRIN/selecoes/pss2026_tec_tri/noticia_2026.png", "download_teste");
        } catch (Exception e) {
            System.err.println("Generic Error: " + e.getMessage());
        }
    }
}
