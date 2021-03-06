package application.dao;

import java.sql.*;

import application.entity.ModeloCarro;

public class ModeloCarroDAO extends DAO<ModeloCarro> {

	{
		table_name = "modelocarro";
	}

	protected PreparedStatement createInsertStatement(Connection con, ModeloCarro modelo_carro) throws SQLException {
		String sql = "INSERT INTO " + table_name + " (marca,modelo,idGrupo) values (?,?,?);";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, modelo_carro.getMarca());
		statement.setString(2, modelo_carro.getModelo());
		statement.setInt(3, modelo_carro.getIdGrupo());
		return statement;
	}

	protected String createDeleteStatement(ModeloCarro entity) {
		return "DELETE FROM " + table_name + " WHERE id = " + String.valueOf(entity.getId());
	}

	protected ModeloCarro getEntityFromResultSet(ResultSet result) {
		ModeloCarro ret = new ModeloCarro();
		try {
			ret.setId(result.getInt("id"));
			ret.setMarca(result.getString("marca"));
			ret.setModelo(result.getString("modelo"));
			ret.setIdGrupo(result.getInt("idGrupo"));
		} catch (Exception e) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}

}
