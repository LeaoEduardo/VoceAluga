package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import application.ConnectionSQL;

public class DAO<T> {
	protected String table_name;
	
	public T find( int id ) {
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
	public ArrayList<T> 	find( String property , String value ) {
		Connection 			con = null;
		PreparedStatement 	statement = null;
		ResultSet 			result = null;
		
		ArrayList<T> ret = new ArrayList<T>();
		
		String sql = "SELECT * FROM "+ table_name + " WHERE " + property + " = '" + value+ "'" ;
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);
			System.out.println("sql query:\n" + statement.toString() );
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
	public ArrayList<T> findAll( ) {
		Connection 			con = null;
		PreparedStatement 	statement = null;
		ResultSet 			result = null;
		
		ArrayList<T> ret = new ArrayList<T>();
		
		String sql = "SELECT * FROM "+ table_name ;
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);
			System.out.println("sql query:\n" + statement.toString());
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
	
	public boolean update( T entity ) {
		boolean 			ret = false;
		Connection 			con = null;
		PreparedStatement 	update_statement = null;
		try {
			con = ConnectionSQL.getConnection();
			update_statement = createUpdateStatement( con , entity );
			
			System.out.println("query:\n" + update_statement.toString() );
			update_statement.execute();
			ret = true;
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			try{ if(con!=null)con.close();}catch(Exception e) {}
			try{ if(update_statement!=null)update_statement.close();}catch(Exception e) {}
		}
		
		return ret;
	}
	public boolean insert( T entity ) {
		boolean ret = false;
		Connection 			con = null;
		PreparedStatement insert_statement = null;
		try {
			con = ConnectionSQL.getConnection();
			insert_statement = createInsertStatement( con , entity );
			
			System.out.println("query:\n" + insert_statement.toString() );
			insert_statement.execute();
			ret = true;
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			try{ if(con!=null)con.close();}catch(Exception e) {}
			try{ if(insert_statement!=null)insert_statement.close();}catch(Exception e) {}
		}
		
		return ret;
	}
	public boolean delete( T entity ) {
		boolean ret = false;
		Connection 			con = null;
		PreparedStatement   delete_statement = null;
		try {
			String sql 			= createDeleteStatement(entity);
			con 				= ConnectionSQL.getConnection();
			delete_statement 	= con.prepareStatement(sql);
			
			System.out.println("query:\n" + delete_statement.toString() );
			delete_statement.execute();
			ret = true;
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			try{ if(con!=null)con.close();}catch(Exception e) {}
			try{ if(delete_statement!=null)delete_statement.close();}catch(Exception e) {}
		}
		
		return ret;
	}
	
	
	
	protected T getEntityFromResultSet( ResultSet rs ) {
		return null;
	}
	protected PreparedStatement createUpdateStatement( Connection con , T entity ) throws SQLException{
		return null;
	}
	protected PreparedStatement createInsertStatement( Connection con , T entity ) throws SQLException{
		return null;
	}
	protected String			createDeleteStatement( T entity ) {
		return null;
	}
	
}
