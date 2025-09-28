package src;

import java.beans.Statement;
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
            System.err.println(e.getMessage());
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

    /*
     * Conecta ao banco de dados
     * Cria um caso não exista
     */
    public boolean connect(){
        try {
            String caminhoPasta = System.getProperty("user.dir");
            String url = "jdbc:sqlite:"+ caminhoPasta +"\\db\\" + this.nomeDB + ".db";

            this.conn = DriverManager.getConnection(url);
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
