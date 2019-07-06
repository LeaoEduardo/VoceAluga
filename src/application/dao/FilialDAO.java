package application.dao;

import java.sql.*;

import application.entity.Filial;

public class FilialDAO extends DAO<Filial> {

	{
		table_name = "filial";
	}

	protected PreparedStatement createInsertStatement(Connection con, Filial filial) throws SQLException {
		String sql = "INSERT INTO filial (nome) values (?);";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, filial.getNome());
		return statement;
	}

	protected String createDeleteStatement(Filial entity) {
		return "DELETE FROM " + table_name + " WHERE id = " + String.valueOf(entity.getId());
	}

	protected Filial getEntityFromResultSet(ResultSet result) {
		Filial ret = new Filial();
		try {
			ret.setId(result.getInt("id"));
			ret.setNome(result.getString("nomeFilial"));
		} catch (Exception e) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}
