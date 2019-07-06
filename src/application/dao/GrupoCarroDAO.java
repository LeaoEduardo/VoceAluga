package application.dao;

import java.sql.*;
import application.entity.GrupoCarro;

public class GrupoCarroDAO extends DAO<GrupoCarro> {

	{
		table_name = "grupoCarro";
	}

	protected GrupoCarro getEntityFromResultSet(ResultSet result) {
		GrupoCarro ret = new GrupoCarro();
		try {
			ret.setId(result.getInt("id"));
			ret.setGrupo(result.getString("grupo"));
		} catch (Exception e) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}
