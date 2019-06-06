package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import application.dao.ClienteDAO;
import application.dao.FuncionarioDAO;
import application.entity.Cliente;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
    
    public boolean LoginFuncionario(String User, String senha) {
    	boolean ret = false;
    	String query = "SELECT DISTINCT "
    			+ "funcionario.id as id,funcionario.nome,funcionario.usuario,filial.nomeFilial, tipoFuncionario.nome as nomeTipo,tipoFuncionario.id as idNivel "
    			+ "FROM "
    			+ "funcionario "
    				+ "INNER JOIN tipoFuncionario "
    					+ "ON funcionario.idTipo = tipoFuncionario.id "
					+ "INNER JOIN filial "
						+ "ON filial.id = funcionario.idFilial "
					+ "WHERE funcionario.usuario = '"+User+"' AND funcionario.senha = '"+senha+"' ;";

    	if(OpenConnection()) {
            try {
            	System.out.println("Resultado de '"+ query +"':");
            	result = statement.executeQuery(query);
            	
            	if (result.next()) {
            		Contexto.getInstancia().setFuncionario( FuncionarioDAO.find(result.getInt("id")) );
            		ret = true;
                }
            } catch (Exception e) {
                System.err.println(e);
            } finally {
            	CloseConnection();
            }
    	}
    	return ret;
    }
    
    public boolean CadastraFuncionario() {
    	return false;
    }
    
    public boolean UpdateFuncionario() {
    	return false;
    }
    
    public boolean ReativaFuncionario() {
    	return false;
    }
    
    public boolean ConsultaCliente(String documento, String valor) {
    	boolean ret = false;
    	String query = "SELECT DISTINCT "
    			+ " Id, Nome, CPF, Passaporte, DataNascimento, Nacionalidade, Telefone, CNH, DataCNH "
    			+ " FROM "
    				+ " cliente "
    			+ " WHERE Ativo = 1 AND ";

        if(documento.equals("cpf"))
            query += "CPF = '" + valor + "'";
        else if(documento.equals("passaporte"))
            query += "Passaporte = '" + valor + "'";

    	if(OpenConnection()) {
            try {
            	result = statement.executeQuery(query);
            	System.out.println("Resultado de '"+ query +"':");

            	if (result.next()) {
            		Cliente cliente = ClienteDAO.find( result.getInt("id"));
            		Contexto.getInstancia().setCliente( cliente );
                    ret = true;
                }
            } catch (Exception e) {
                System.err.println(e);
            } finally {
            	CloseConnection();
            }
    	}
    	return ret;
    }        

    public boolean CadastrarCliente(String nome, String cpf, String passaporte, String dataNasc, String nacionalidade, String telefone, String cnh, String datacnh) {
    	boolean ret = false;
    	String cpftemp = cpf==""?null:"'"+cpf+"'";
    	String passaportetemp = passaporte==""?null:"'"+passaporte+"'";
    	String query = "INSERT INTO cliente (id, nome, CPF, passaporte, dataNascimento, nacionalidade, telefone, CNH, dataCNH) "
    			+ "VALUES (NULL, '" +nome+ "', " +cpftemp+ ", "+passaportetemp+", '"+dataNasc+"', '"+nacionalidade+"', '"+telefone+"', '"+cnh+"', '"+datacnh+"');";
    		
    	System.out.println(query);
    	if(OpenConnection()) {
            try {
            	statement.execute(query);
            	System.out.println("Cliente cadastrado");
            	ret = true;
            } catch (SQLException e) {
            	System.err.println(e);
        		Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("Erro");
            	alert.setHeaderText("Erro no cadastro.");
            	alert.setContentText("CPF ou Passaporte ja cadastrado!");
            	alert.showAndWait();
            } catch (Exception e) {
            	System.err.println(e);
        		Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("Erro");
            	alert.setHeaderText("Erro no cadastro.");
            	alert.setContentText("Dados inconsistentes!");
            	alert.showAndWait();                
            } finally {
            	CloseConnection();
            }
    	}
    	return ret;
    } 
    
    public boolean AtualizaCliente( int idCliente, String nome, String cpf, String passaporte, String dataNasc, String nacionalidade, String telefone, String cnh, String datacnh) {
    	boolean ret = false;
    	String query = "UPDATE cliente SET nome = '"+nome+"', CPF = '"+cpf+"', passaporte = '"+passaporte+
    				"', dataNascimento = '"+dataNasc+"', nacionalidade = '"+nacionalidade+"', telefone = '"+telefone+
    				"', CNH = '"+cnh+"', dataCNH = '"+datacnh+"' WHERE cliente.id = "+ String.valueOf(idCliente) +";";
    		
    	System.out.println(query);
    	if(OpenConnection()) {
            try {
            	statement.executeUpdate(query);
            	System.out.println("Cliente atualizado");
            	ret = true;
            } catch (Exception e) {
                System.err.println(e);
            } finally {
            	CloseConnection();
            }
    	}
    	return ret;
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
    
    public boolean CadastraVeiculo(String placa, int quilometragem, String marca, String modelo, String filial,
			String estado, LocalDate dataCompra, LocalDate dataManutencao) {
    	boolean ret = false;
    	String query = "SELECT id, tipo FROM estadoCarro WHERE tipo = \""+estado+"\" UNION SELECT id, nomeFilial"
    			+ " FROM filial WHERE nomeFilial = \""+filial+"\" UNION SELECT m.id, m.marca FROM modeloCarro m "
    			+ "WHERE marca = \""+marca+"\" AND modelo = \""+modelo+"\"";
    	
    	System.out.println(query);
    	if(OpenConnection()) {
            try {
                result = statement.executeQuery(query);
                System.out.println("Consulta de dados para cadastar veiculo ok");
            	
                String idModelo = "";
            	String idFilial = "";
            	String idEstado = "";
                
                if (result.next()) { 
                	idEstado = result.getString("Id");
                	System.out.println("passou 1");
                	if(result.next()) {
                		idFilial = result.getString("Id");
                    	System.out.println("passou 2");
                		if(result.next()) {
                			idModelo = result.getString("Id");
                        	System.out.println("passou 3");
                		}
                	}
                }
            	
                try {
                	query = "INSERT INTO carro (Id, placa, dataManutencao, dataCompra, quilometragem, idModelo, idFilial, idEstado) "
                			+ "VALUES (NULL, '" +placa+ "', '"+dataManutencao+"', '"+dataCompra+"', '"+quilometragem+"', '"+idModelo+"', '"+idFilial+"', '"+idEstado+"');";
                	System.out.println(query);
                	statement.execute(query);
                	System.out.println("Veiculo cadastrado");
                	ret = true;
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





