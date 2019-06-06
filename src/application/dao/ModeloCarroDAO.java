package application.dao;

import java.sql.*;
import java.util.ArrayList;

import application.ConnectionSQL;
import application.entity.ModeloCarro;

public class ModeloCarroDAO extends DAO {
	
	public static ModeloCarroDAO modelo_carro_dao = new ModeloCarroDAO();
	
	public static ModeloCarro find( int id ) {
		return (ModeloCarro) modelo_carro_dao.find("modeloCarro",id);
	}
	public static ArrayList<ModeloCarro> findAll(){
		ArrayList<Object> obj_list = modelo_carro_dao.findAll("modeloCarro");
		ArrayList<ModeloCarro> ret = new ArrayList<ModeloCarro>();
		for( int i = 0 ; i < obj_list.size(); i++ ) {
			ret.add( (ModeloCarro)obj_list.get(i) );
		}
		return ret;
	}
	

	public static boolean insertFIlial( ModeloCarro modelo_carro ) {
		boolean ret = true;
		Connection 			con = null;
		PreparedStatement 	statement = null;
		
		String sql = "INSERT INTO modeloCarro (marca,modelo,idGrupo) values (?,?,?);";
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);

			statement.setString(1, modelo_carro.getMarca());
			statement.setString(2, modelo_carro.getModelo());
			statement.setInt(3, modelo_carro.getIdGrupo());
			
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
		ModeloCarro ret = new ModeloCarro();
		try {
			ret.setId(result.getInt("id"));
			ret.setMarca(result.getString("marca"));
			ret.setModelo(result.getString("modelo"));
			ret.setIdGrupo(result.getInt("idGrupo"));
		} catch (Exception e ) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}
