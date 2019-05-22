package application.dao;

import java.sql.*;

import application.ConnectionSQL;
import application.entity.Funcionario;

public class FuncionarioDAO {
	public static Funcionario find( int id ) {
		Funcionario 		ret = null;
		Connection 			con = null;
		PreparedStatement 	statement = null;
		ResultSet 			result = null;
		
		String sql = "SELECT * FROM funcionario WHERE id="+id;
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);
			result = statement.executeQuery();
			if( result.next() ) {
				ret = funcionarioFromResultSet(result);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			try{ if(con!=null)con.close();}catch(Exception e) {}
			try{ if(statement!=null)statement.close();}catch(Exception e) {}
			try{ if(result!=null)result.close();}catch(Exception e) {}
		}
		
		return ret;
	}
	
	public static boolean insertFuncionario( Funcionario funcionario ) {
		boolean ret = true;
		Connection 			con = null;
		PreparedStatement 	statement = null;
		
		String sql = "INSERT INTO funcionario (nome,usuario,senha,idFilial,idTipo,ativo) values (?,?,?,?,?,?);";
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);
			
			statement.setString(1, funcionario.getNome());
			statement.setString(2, funcionario.getUsuario());
			statement.setString(3, funcionario.getSenha());
			statement.setInt(4, funcionario.getIdFilial());
			statement.setInt(5, funcionario.getIdTipo());
			statement.setBoolean(6, funcionario.isAtivo());
			
			// TO-DO: Fazer a validação adequada
			
			statement.execute();
			ret = statement.getUpdateCount() > 0;
		} catch ( Exception e ) {
			ret = false;
			e.printStackTrace();
		} finally {
			try{ if(con!=null)con.close();}catch(Exception e) {}
			try{ if(statement!=null)statement.close();}catch(Exception e) {}
		}
		
		return ret;
	}
	
	public static boolean updateFuncionario( Funcionario funcionario ) {
		boolean ret = true;
		Connection 			con = null;
		PreparedStatement 	statement = null;
		
		String sql = "UPDATE TABLE funcionario "
				+ "SET nome=?,usuario=?,senha=?,idFilial=?,idTipo=?,ativo=?"
				+ "WHERE id=?";
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);
			
			statement.setString(1, funcionario.getNome());
			statement.setString(2, funcionario.getUsuario());
			statement.setString(3, funcionario.getSenha());
			statement.setInt(4, funcionario.getIdFilial());
			statement.setInt(5, funcionario.getIdTipo());
			statement.setBoolean(6, funcionario.isAtivo());
			statement.setInt(7, funcionario.getId());
			
			ret = statement.execute();
		} catch ( Exception e ) {
			ret = false;
			e.printStackTrace();
		} finally {
			try{ if(con!=null)con.close();}catch(Exception e) {}
			try{ if(statement!=null)statement.close();}catch(Exception e) {}
		}
		
		return ret;
	}
	
	
	
	private static Funcionario funcionarioFromResultSet( ResultSet result ) {
		Funcionario ret = new Funcionario();
		try {
			ret.setId(result.getInt("id"));
			ret.setIdFilial(result.getInt("idFilial"));
			ret.setIdTipo(result.getInt("idTipo"));
			ret.setNome(result.getString("nome"));
			ret.setUsuario(result.getString("usuario"));
			ret.setSenha(result.getString("senha"));
			ret.setAtivo(result.getBoolean("ativo"));
		} catch (Exception e ) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}
