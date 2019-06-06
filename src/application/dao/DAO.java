package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import application.ConnectionSQL;

public class DAO {
	
	protected Object find( String table_name , int id ) {
		Connection 			con = null;
		PreparedStatement 	statement = null;
		ResultSet 			result = null;
		
		String sql = "SELECT * FROM "+ table_name +" WHERE id="+id;
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);
			result = statement.executeQuery();
			if( result.next() ) {
				return getEntityFromResultSet(result);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			try{ if(con!=null)con.close();}catch(Exception e) {}
			try{ if(statement!=null)statement.close();}catch(Exception e) {}
			try{ if(result!=null)result.close();}catch(Exception e) {}
		}
		
		return null;
	}
	
	protected ArrayList<Object> findAll( String table_name ) {
		Connection 			con = null;
		PreparedStatement 	statement = null;
		ResultSet 			result = null;
		
		ArrayList<Object> ret = new ArrayList<Object>();
		
		String sql = "SELECT * FROM "+ table_name ;
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);
			result = statement.executeQuery();
			while( result.next() ) {
				ret.add( getEntityFromResultSet(result) );
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
	
	
	protected Object getEntityFromResultSet( ResultSet rs ) {
		return null;
	}
	
}
