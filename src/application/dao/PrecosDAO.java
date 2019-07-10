package application.dao;

import java.sql.*;
import application.entity.Precos;

public class PrecosDAO extends DAO<Precos> {

	{
		table_name = "precos";
	}

	protected PreparedStatement createInsertStatement(Connection con, Precos precos) throws SQLException {
		String sql = "INSERT INTO precos "
				+ " (id_grupo,valor) "
				+ " values (?,?)";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setInt(1, precos.getId_grupo());
		statement.setFloat(2, precos.getValor());

		return statement;
	}

	protected PreparedStatement createUpdateStatement(Connection con, Precos precos) throws SQLException {
		String sql = "UPDATE precos "
				+ " SET id_grupo=?,valor=? "
				+ " WHERE id=? ";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setInt(1, precos.getId_grupo());
		statement.setFloat(2, precos.getValor());
		statement.setInt(3, precos.getId());
		return statement;
	}

	protected String createDeleteStatement(Precos entity) {
		return "DELETE FROM " + table_name + " WHERE id = " + String.valueOf(entity.getId());
	}

	protected Precos getEntityFromResultSet(ResultSet result) {
		Precos ret = new Precos();
		try {
			ret.setId(result.getInt("id"));
			ret.setId_grupo(result.getInt("id_grupo"));
			ret.setValor(result.getFloat("valor"));

		} catch (Exception e) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}