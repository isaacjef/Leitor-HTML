package src;

//import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    private Connection conn;
    private String nomeDB;

    public Database(String nomeDb){
        this.nomeDB = nomeDb;
    }

    public void inserirPalestrantes(ArrayList<Palestrante> palestrantes){
        for(Palestrante palestrante : palestrantes) {
            inserirDados(palestrante.getName(), palestrante.getEmail(), palestrante.getWork(), palestrante.getDiretorioImage());
        }

        System.out.println("Palestrantes adicionados com secuesso!");
    }

    public void inserirDados(String name, String email, String work, String image){
        String insertSql = "INSERT INTO palestrant (name, email, work, image) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = this.conn.prepareStatement(insertSql);

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, work);
            pstmt.setString(4, image);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO: handle exception
            System.err.println("SQLException! Erro na inserção de dados: " + e.getMessage());
        }
    }

    public void deletarTabela(){
        try {
            var stmt = this.conn.createStatement();

            String sql = "DELETE FROM palestrant;";

            stmt.executeUpdate(sql);
            System.out.println("Tabela deletada!");

        } catch (SQLException e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
    }

    public void criarTabela(){
        try {
            var stmt = this.conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS palestrant ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name VARCHAR(255) NOT NULL,"
                + "work VARCHAR(255) NOT NULL,"
                + "email VARCHAR(255) NOT NULL,"
                + "image VARCHAR(255) NOT NULL"
                + ");";

            stmt.execute(sql);
            System.out.println("Tabela criada ou já existente!");

        } catch (SQLException e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
    }

    public void listarPalestrante() throws SQLException {
        var stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Speaker WHERE ID=800");
        while(rs.next()) 
        {
            System.out.println("ID: " + rs.getInt("id") +  
                " Nome: " + rs.getString("name") +
                " Local de Trabalho: " + rs.getString("work") +
                " E-mail: " + rs.getString("email") + 
                " Imagem: " + rs.getString("image"));
        }
    }

    public void listarPalestrantes() throws SQLException {
        var stmt = this.conn.createStatement();
        
        // Verificar se rs é null, e tratar -> "Nenhum retorno para a query"
        ResultSet rs = stmt.executeQuery("SELECT * FROM Speaker");
        try {
            while(rs.next()) 
            {
                System.out.println("ID: " + rs.getInt("id") +  
                    " Nome: " + rs.getString("name") +
                    " Local de Trabalho: " + rs.getString("work") +
                    " E-mail: " + rs.getString("email") + 
                    " Imagem: " + rs.getString("image"));
            }
        } catch (SQLException e) {
            System.err.println("SQLException -> " + e.getMessage());
        }
    }

    /*
     * Conecta ao banco de dados
     * Cria um caso não exista
     */
    public boolean connect(){
        try {
            String caminhoPasta = System.getProperty("user.dir");
            String url = "jdbc:sqlite:"+ caminhoPasta +"\\db\\" + this.nomeDB + ".db";

            this.conn = DriverManager.getConnection(url);
            this.deletarTabela();
            this.criarTabela();
        } catch (SQLException e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean desconnect(){
        try {
            if(this.conn.isClosed() == false)
                this.conn.close();
            
        } catch (SQLException e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
            return false;
        }

        return true;
    }
}
