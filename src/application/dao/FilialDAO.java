package application.dao;

import java.sql.*;
import java.util.ArrayList;

import application.ConnectionSQL;
import application.entity.Filial;

public class FilialDAO extends DAO {
	
	public static FilialDAO filial_dao = new FilialDAO();
	
	public static Filial find( int id ) {
		return (Filial) filial_dao.find("filial",id);
	}
	public static ArrayList<Filial> findAll(){
		ArrayList<Object> obj_list = filial_dao.findAll("filial");
		ArrayList<Filial> ret = new ArrayList<Filial>();
		for( int i = 0 ; i < obj_list.size(); i++ ) {
			ret.add( (Filial)obj_list.get(i) );
		}
		return ret;
	}
	

	public static boolean insertFIlial( Filial filial ) {
		boolean ret = true;
		Connection 			con = null;
		PreparedStatement 	statement = null;
		
		String sql = "INSERT INTO filial (nome) values (?);";
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);

			statement.setString(1, filial.getNome());
			
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
	
	
	protected  Object getEntityFromResultSet( ResultSet result ) {
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
