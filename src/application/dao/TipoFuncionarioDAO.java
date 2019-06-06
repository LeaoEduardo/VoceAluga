package application.dao;

import java.sql.*;
import java.util.ArrayList;

import application.entity.TipoFuncionario;

public class TipoFuncionarioDAO extends DAO {
	
	public static TipoFuncionarioDAO tipo_funcionario_dao = new TipoFuncionarioDAO();

	public static TipoFuncionario find( int id ) {
		return (TipoFuncionario) tipo_funcionario_dao.find("tipoFuncionario",id);
	}
	public static ArrayList<TipoFuncionario> findAll(){
		ArrayList<Object> obj_list = tipo_funcionario_dao.findAll("tipoFuncionario");
		ArrayList<TipoFuncionario> ret = new ArrayList<TipoFuncionario>();
		for( int i = 0 ; i < obj_list.size(); i++ ) {
			ret.add( (TipoFuncionario)obj_list.get(i) );
		}
		return ret;
	}

	protected  Object getEntityFromResultSet( ResultSet result ) {
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
