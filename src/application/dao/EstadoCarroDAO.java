package application.dao;

import java.sql.*;
import java.util.ArrayList;

import application.entity.EstadoCarro;

public class EstadoCarroDAO extends DAO {
	
	public static EstadoCarroDAO estado_carro_dao = new EstadoCarroDAO();

	public static EstadoCarro find( int id ) {
		return (EstadoCarro) estado_carro_dao.find("estadoCarro",id);
	}
	public static ArrayList<EstadoCarro> findAll(){
		ArrayList<Object> obj_list = estado_carro_dao.findAll("estadoCarro");
		ArrayList<EstadoCarro> ret = new ArrayList<EstadoCarro>();
		for( int i = 0 ; i < obj_list.size(); i++ ) {
			ret.add( (EstadoCarro)obj_list.get(i) );
		}
		return ret;
	}

	protected  Object getEntityFromResultSet( ResultSet result ) {
		EstadoCarro ret = new EstadoCarro();
		try {
			ret.setId(result.getInt("id"));
			ret.setTipo(result.getString("tipo"));
		} catch (Exception e ) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}
