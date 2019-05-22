package application.dao;

import java.sql.*;

import application.ConnectionSQL;
import application.entity.TipoFuncionario;

public class TipoFuncionarioDAO {
	public static TipoFuncionario find( int id ) {
		TipoFuncionario 	ret = null;
		Connection 			con = null;
		PreparedStatement 	statement = null;
		ResultSet 			result = null;
		
		String sql = "SELECT * FROM tipoFuncionario WHERE id="+id+";";
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);
			result = statement.executeQuery();
			if( result.next() ) {
				ret = tipoFuncionarioFromResultSet(result);
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
	
	private static TipoFuncionario tipoFuncionarioFromResultSet( ResultSet result ) {
		TipoFuncionario ret = new TipoFuncionario();
		try {
			ret.setId(result.getInt("id"));
			ret.setNome(result.getString("nome"));
		} catch (Exception e ) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}
