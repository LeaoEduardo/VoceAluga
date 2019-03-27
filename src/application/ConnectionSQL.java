package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

// Documentacao 
// https://docs.oracle.com/javase/7/docs/api/java/sql/package-frame.html

public class ConnectionSQL{
    final private static String driver_name = "com.mysql.cj.jdbc.Driver";
    final private static String server_addr = "localhost"; 
    final private static String db_user = "root"; 
    final private static String db_password = "";
    final private static String db_name = "vocealuga";
    
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet result = null;
    public ConnectionSQL(){
        
        // "Vamos testar se o Driver consegue ser carregado"
        try {
            Class.forName(driver_name).newInstance();
            System.out.println("Driver carregado successfully");
        } catch (Exception e) {
            System.err.println("Nao foi possivel carregar o driver do mysql :( ");
            System.err.println(e);
        }

        // Agora vamos tentar criar uma conexao com o banco de dados no endereço especificado em server_addr
        try {
            connection = DriverManager.getConnection( 
            		"jdbc:mysql://"+server_addr+"/"+db_name+"?useTimezone=true&serverTimezone=UTC" , db_user , db_password );
            System.out.println("Conexao com o banco de dados estabelecida successfully");   
        } catch (Exception e) {
            System.err.println("Nao foi possivel estabelecer uma conexao com o banco de dados...");
            System.err.println(e);
        }
    }
    private boolean OpenConnection() {
    	try {
    		statement = connection.createStatement();
    		return true;
    	}
    	catch (Exception e) {
            System.err.println("Erro conectando...");
            System.err.println(e);
            return false;
        }
    	
    }
    
    private boolean CloseConnection() {      
        // Vi na documentacao q eh uma boa ideia fazer isso pra liberar recurso
        try{
            result.close();
            result = null;
            statement.close();
            statement = null;
            return true;
        }
        catch(Exception e){
        	System.err.println("Erro fechando...");
            System.err.println(e);
            return false;
        }

    }
    
    public boolean LoginFuncionario(String User, String senha) {
    	
    	String query = "Select f.User, tf.Id as IdNivel, tf.Nombre from funcionario f inner join tipofuncionario tf on f.IdTipo = tf.Id where User = '"+User+"' and Senha = '"+senha+"'";

    	if(OpenConnection()) {
            try {
            	result = statement.executeQuery(query);
            	System.out.println("Resultado de '"+ query +"':");
                /*result.first();
                while( !result.isAfterLast() ){
                    System.out.println(result.getString(1));
                    result.next();
                }*/
            	//while (rs.next()) {
                    //String coffeeName = rs.getString("COF_NAME");
            	if (result.next()) {
            		Contexto.getInstancia().setUsuario(
            				result.getString("User"), 
            				result.getString("Nombre"), 
            				Integer.parseInt(result.getString("IdNivel"))
            				);
                    return true;
                }
            } catch (Exception e) {
                System.err.println(e);
            } finally {
                
            }
    	}
    	return false;
    }
}