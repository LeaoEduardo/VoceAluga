package application.dao;

import java.sql.*;
import application.entity.EstadoCarro;

public class EstadoCarroDAO extends DAO<EstadoCarro> {
	
	{
		table_name = "estadoCarro";
	}
	
	protected  EstadoCarro getEntityFromResultSet( ResultSet result ) {
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
