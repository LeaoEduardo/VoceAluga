package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneId;

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

        // Agora vamos tentar criar uma conexao com o banco de dados no endereco especificado em server_addr
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
    	
    	String query = "Select f.User, tf.Id as IdNivel, tf.Nombre, fi.nomeFilial from funcionario f inner join tipofuncionario tf on f.IdTipo = tf.Id inner join filial fi on f.IdFilial = fi.id where User = '"+User+"' and Senha = '"+senha+"'";

    	if(OpenConnection()) {
            try {
            	result = statement.executeQuery(query);
            	System.out.println("Resultado de '"+ query +"':");
            	
            	if (result.next()) {
            		Contexto.getInstancia().setUsuario(
            				result.getString("User"), 
            				result.getString("Nombre"), 
            				Integer.parseInt(result.getString("IdNivel")),
            				result.getString("nomeFilial")
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
    
    public boolean ConsultaCliente(String documento, String valor) {
    	
    	String query = "Select Id, Nome, CPF, Passaporte, DataNascimento, Nacionalidade, Telefone, CNH, DataCNH from cliente where Ativo = 1 and ";

        if(documento.equals("cpf"))
            query += "CPF = '" + valor + "'";
        else if(documento.equals("passaporte"))
            query += "Passaporte = '" + valor + "'";

    	if(OpenConnection()) {
            try {
            	result = statement.executeQuery(query);
            	System.out.println("Resultado de '"+ query +"':");

            	if (result.next()) {
            		Contexto.getInstancia().setCliente(
            				result.getString("Id"),
            				result.getString("Nome"), 
            				result.getString("CPF"), 
            				result.getString("Passaporte"),
            				Instant.ofEpochMilli(result.getDate("DataNascimento").getTime()).atZone(ZoneId.of("UTC")).toLocalDate(),
            				result.getString("Nacionalidade"),
            				result.getString("Telefone"),
            				result.getString("CNH"),
            				Instant.ofEpochMilli(result.getDate("DataCNH").getTime()).atZone(ZoneId.of("UTC")).toLocalDate());
                    return true;
                }
            } catch (Exception e) {
                System.err.println(e);
            } finally {
                
            }
    	}
    	return false;
    }        

    public boolean CadastrarCliente(String nome, String cpf, String passaporte, String dataNasc, String nacionalidade, String telefone, String cnh, String datacnh) {
    	
    	String query = "INSERT INTO cliente (Id, Nome, CPF, Passaporte, DataNascimento, Nacionalidade, Telefone, CNH, DataCNH) "
    			+ "VALUES (NULL, '" +nome+ "', '" +cpf+ "', '"+passaporte+"', '"+dataNasc+"', '"+nacionalidade+"', '"+telefone+"', '"+cnh+"', '"+datacnh+"');";
    		
    	System.out.println(query);
    	if(OpenConnection()) {
            try {
            	statement.execute(query);
            	System.out.println("Cliente cadastrado");
            	return true;
            } catch (Exception e) {
                System.err.println(e);
            } finally {
                
            }
    	}
    	return false;
    } 
    
    public boolean AtualizaCliente(String idCliente, String nome, String cpf, String passaporte, String dataNasc, String nacionalidade, String telefone, String cnh, String datacnh) {
    	
    	String query = "UPDATE cliente SET Nome = '"+nome+"', CPF = '"+cpf+"', Passaporte = '"+passaporte+
    				"', DataNascimento = '"+dataNasc+"', Nacionalidade = '"+nacionalidade+"', Telefone = '"+telefone+
    				"', CNH = '"+cnh+"', DataCNH = '"+datacnh+"' WHERE cliente.Id = "+idCliente+";";
    		
    	System.out.println(query);
    	if(OpenConnection()) {
            try {
            	statement.executeUpdate(query);
            	System.out.println("Cliente cadastrado");
            	return true;
            } catch (Exception e) {
                System.err.println(e);
            } finally {
                
            }
    	}
    	return false;
    }

    public boolean RemoverCliente(String idCliente) {
        
        String query = "UPDATE cliente SET Ativo = 0 WHERE cliente.Id = "+idCliente+";";
            
        System.out.println(query);
        if(OpenConnection()) {
            try {
                statement.executeUpdate(query);
                System.out.println("Cliente apagado");
                return true;
            } catch (Exception e) {
                System.err.println(e);
            } finally {
                
            }
        }
        return false;
    }
}