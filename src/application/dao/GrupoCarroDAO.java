package application.dao;

import java.sql.*;
import java.util.ArrayList;

import application.entity.GrupoCarro;

public class GrupoCarroDAO extends DAO {
	
	public static GrupoCarroDAO grupo_carro_dao = new GrupoCarroDAO();

	public static GrupoCarro find( int id ) {
		return (GrupoCarro) grupo_carro_dao.find("grupoCarro",id);
	}
	public static ArrayList<GrupoCarro> findAll(){
		ArrayList<Object> obj_list = grupo_carro_dao.findAll("grupoCarro");
		ArrayList<GrupoCarro> ret = new ArrayList<GrupoCarro>();
		for( int i = 0 ; i < obj_list.size(); i++ ) {
			ret.add( (GrupoCarro)obj_list.get(i) );
		}
		return ret;
	}

	protected  Object getEntityFromResultSet( ResultSet result ) {
		GrupoCarro ret = new GrupoCarro();
		try {
			ret.setId(result.getInt("id"));
			ret.setGrupo(result.getString("grupo"));
		} catch (Exception e ) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}
