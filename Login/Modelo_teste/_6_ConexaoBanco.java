package Login.Modelo_teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class _6_ConexaoBanco {

    private static final String URL = "jdbc:mysql://localhost:3306/hamburgueria_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexão direta com o MySQL realizada com sucesso!");
            return conexao;
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver do MySQL não encontrado: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar ao MySQL: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        conectar();
    }
}