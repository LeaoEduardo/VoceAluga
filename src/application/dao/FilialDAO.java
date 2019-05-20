package application.dao;

import java.sql.*;

import application.ConnectionSQL;
import application.entity.Filial;

public class FilialDAO {
	public static Filial find( int id ) {
		Filial 				ret = null;
		Connection 			con = null;
		PreparedStatement 	statement = null;
		ResultSet 			result = null;
		
		String sql = "SELECT * FROM filial WHERE id="+id+";";
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);
			result = statement.executeQuery();
			if( result.next() ) {
				ret = filialFromResultSet(result);
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
	
	private static Filial filialFromResultSet( ResultSet result ) {
		Filial ret = new Filial();
		try {
			ret.setId(result.getInt("id"));
			ret.setNome(result.getString("nomeFilial"));
		} catch (Exception e ) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}
