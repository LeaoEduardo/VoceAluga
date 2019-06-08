package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

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
        
        // Vamos testar se o Driver consegue ser carregado
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
    public static Connection getConnection() {
    	try {
            return DriverManager.getConnection( 
            		"jdbc:mysql://"+server_addr+"/"+db_name+"?useTimezone=true&serverTimezone=UTC" , db_user , db_password );
        } catch (Exception e) {
            System.err.println("Nao foi possivel estabelecer uma conexao com o banco de dados...");
            e.printStackTrace();
        }
    	return null;
    }
    private boolean OpenConnection() {
    	try {
    		statement = connection.createStatement();
    		return true;
    	}
    	catch (Exception e) {
            System.err.println("Erro ao tentar se conectar com o database " + db_name + " no endereço " + server_addr );
            System.err.println(e);
            return false;
        }
    }
    
    private boolean CloseConnection() {      
        // Vi na documentacao q eh uma boa ideia fazer isso pra liberar recurso
        try{
            //result.close();
            //result = null;
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
    
    // A meta é sumir com todos os métodos daqui pra baixo
    
    
    public boolean CadastraFuncionario() {
    	return false;
    }
    
    public boolean UpdateFuncionario() {
    	return false;
    }
    
    public boolean ReativaFuncionario() {
    	return false;
    }
    
    

    public boolean RemoverCliente(int id) {
    	boolean ret = false;
        String query = "UPDATE cliente SET ativo = 0 WHERE cliente.Id = "+ String.valueOf(id) +";";
            
        System.out.println(query);
        if(OpenConnection()) {
            try {
                statement.executeUpdate(query);
                System.out.println("Cliente apagado");
                ret = true;
            } catch (Exception e) {
                System.err.println(e);
            } finally {
            	CloseConnection();
            }
        }
        return ret;
    }
    
    
    public boolean CadastroModelo(String marca, String modelo, String idGrupo) {
    	boolean ret = false;
    	String query = "SELECT id FROM grupoCarro WHERE grupo = '"+idGrupo+"'";
    		
    	System.out.println(query);
    	if(OpenConnection()) {
            try {
            	result = statement.executeQuery(query);
            	System.out.println("Grupo consultado");
            	if(result.next()) {
            		idGrupo = result.getString("id");
            	}
            	
            	try {
            		query = "INSERT INTO modeloCarro (id, marca, modelo, idGrupo) "
            				+ "VALUES (NULL, '" +marca+ "', '"+modelo+"', "+idGrupo+");";
            		System.out.println(query);
                	statement.execute(query);
            		System.out.println("Modelo de carro cadastrado");
                	ret = true;
            	}
            	catch(Exception e) {
                	System.err.println(e);            
            	}
            } catch (Exception e) {
            	System.err.println(e);            
            } finally {
            	CloseConnection();
            }
    	}
    	return ret;
    }
    
    
    public boolean UpdateVeiculo(String placa, int quilometragem, String marca, String modelo, String filial,
								String estado, LocalDate dataCompra, LocalDate dataManutencao) {
    	boolean ret = false;
    	String query  = "SELECT id, tipo FROM estadoCarro WHERE tipo = \""+estado+"\" UNION SELECT id, nomeFilial"
    			+ " FROM filial WHERE nomeFilial = \""+filial+"\" UNION SELECT m.id, m.marca FROM modeloCarro m "
    			+ "WHERE marca = \""+marca+"\" AND modelo = \""+modelo+"\" UNION SELECT id, placa FROM carro WHERE " 
    			+ "placa = \""+placa+"\"";
    	
    	System.out.println(query);
    	if(OpenConnection()) {
            try {
                result = statement.executeQuery(query);
                System.out.println("Consulta de dados para atualizar veiculo ok");
            	
                String idModelo = "";
            	String idFilial = "";
            	String idEstado = "";
            	String idCarro = "";
                
                if (result.next()) { 
                	idEstado = result.getString("Id");
                	System.out.println("passou 1");
                	if(result.next()) {
                		idFilial = result.getString("Id");
                    	System.out.println("passou 2");
                		if(result.next()) {
                			idModelo = result.getString("Id");
                        	System.out.println("passou 3");
                        	if(result.next()) {
                    			idCarro = result.getString("Id");
                            	System.out.println("passou 4");
                    		}
                		}
                	}
                }
            	
                try {
                	query = "UPDATE carro SET placa = '"+placa+"', dataManutencao = '"+dataManutencao+"', dataCompra = '"+dataCompra+
            				"', quilometragem = '"+quilometragem+"', idModelo = '"+idModelo+"', idFilial = '"+idFilial+
            				"', idEstado = '"+idEstado+"' WHERE carro.id = "+idCarro;
                	System.out.println(query);
                	statement.execute(query);
                	System.out.println("Veiculo atualizado");
                	ret = true;
                	//set new veiculo em contexto
                }
                catch (Exception e) {
                    System.err.println(e);
                }
            } catch (Exception e) {
                System.err.println(e);
            } finally {
            	CloseConnection();
            }
        }
    	
    	return ret;
    }
    
    public boolean VendeVeiculo(String placa) {
    	boolean ret = false;
    	String query = "UPDATE carro SET idEstado = 4 WHERE carro.placa = '"+placa+"'";
    	
    	System.out.println(query);
        if(OpenConnection()) {
            try {
                statement.executeUpdate(query);
                System.out.println("Estado do veiculo alterado para Vendido");
                ret = true;
                //set veiculo estado = vendido no contexto
            } catch (Exception e) {
                System.err.println(e);
            } finally {
            	CloseConnection();
            }
        }
        
        return ret;    	
    }
    
    public boolean ReativaVeiculo(String placa) {
    	boolean ret = false;
    	String query = "UPDATE carro SET idEstado = 3 WHERE carro.placa = '"+placa+"'";
    	
    	System.out.println(query);
        if(OpenConnection()) {
            try {
                statement.executeUpdate(query);
                System.out.println("Estado do veiculo alterado para Disponivel");
                ret = true;
            } catch (Exception e) {
                System.err.println(e);
            } finally {
            	CloseConnection();
            }
        }
        
        return ret;    
    }
    
}





