package application.dao;

import java.sql.*;
import application.entity.TipoFuncionario;

public class TipoFuncionarioDAO extends DAO<TipoFuncionario> {
	
	{
		table_name = "tipoFuncionario";
	}
	
	protected  TipoFuncionario getEntityFromResultSet( ResultSet result ) {
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
