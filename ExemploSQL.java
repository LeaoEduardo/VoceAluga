import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

// Documentacao 
// https://docs.oracle.com/javase/7/docs/api/java/sql/package-frame.html

public class ExemploSQL{
    final private static String driver_name = "com.mysql.cj.jdbc.Driver";
    final private static String server_addr = "127.0.0.1"; 
    final private static String db_user = "user"; 
    final private static String db_password = "password";
    final private static String db_name = "test";
    final private static String exemplo_query = "SHOW TABLES";
    
    public static void main( String[] args ){
        
        // "Vamos testar se o Driver consegue ser carregado"
        try {
            Class.forName(driver_name).newInstance();
            System.out.println("Driver carregado successfully");
        } catch (Exception e) {
            System.err.println("Nao foi possivel carregar o driver do mysql :( ");
            System.err.println(e);
        }

        // Agora vamos tentar criar uma conexao com o banco de dados no endereço especificado em server_addr
        Connection connection = null;
        try {
            connection = DriverManager.getConnection( "jdbc:mysql://"+server_addr+"/"+db_name , db_user , db_password );
            System.out.println("Conexao com o banco de dados estabelecida successfully");   
        } catch (Exception e) {
            System.err.println("Nao foi possivel estabelecer uma conexao com o banco de dados...");
            System.err.println(e);
        }

        // Vamos ver quais tabelas existem no banco
        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(exemplo_query);
            // Outra maneira de pegar result:
            // statement.execute(exemplo_query);
            // result = statement.getResultSet();

            // Imprimindo o resultado
            System.out.println("Resultado de '"+ exemplo_query +"':");
            result.first();
            while( !result.isAfterLast() ){
                System.out.println(result.getString(1));
                result.next();
            }
            
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            
        }

        // Vi na documentacao q eh uma boa ideia fazer isso pra liberar recurso
        try{
            result.close();
            result = null;
            statement.close();
            statement = null;
        }
        catch(Exception e){
            System.err.println(e);
        }

    }
}