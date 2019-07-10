package application.dao;

import java.sql.*;

import application.entity.Manutencao;

public class ManutencaoDAO extends DAO<Manutencao> {

	{
		table_name = "manutencao";
	}

	protected PreparedStatement createInsertStatement(Connection con, Manutencao manutencao) throws SQLException {
		String sql = "INSERT INTO manutencao " + " (id_carro,dataInicio,dataFim,orcamento) " + " values (?,?,?,?)";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setInt(1, manutencao.getIdCarro());
		statement.setDate(2, Date.valueOf(manutencao.getDataInicio()));
		statement.setDate(3, Date.valueOf(manutencao.getDataFim()));
		statement.setInt(4, manutencao.getOrcamento());

		return statement;
	}

	protected PreparedStatement createUpdateStatement(Connection con, Manutencao manutencao) throws SQLException {
		String sql = "UPDATE manutencao " + " SET id_Carro=?,dataInicio=?,dataFim=?,orcamento=? " + " WHERE id=? ";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setInt(1, manutencao.getIdCarro());
		statement.setDate(2, Date.valueOf(manutencao.getDataInicio()));
		statement.setDate(3, Date.valueOf(manutencao.getDataFim()));
		statement.setInt(4, manutencao.getOrcamento());

		return statement;
	}

	protected String createDeleteStatement(Manutencao entity) {
		return "DELETE FROM " + table_name + " WHERE id = " + String.valueOf(entity.getId());
	}

	protected Manutencao getEntityFromResultSet(ResultSet result) {
		Manutencao ret = new Manutencao();
		try {
			ret.setId(result.getInt("id"));
			ret.setIdCarro(result.getInt("id_carro"));
			ret.setDataInicio(result.getDate("dataInicio").toLocalDate());
			ret.setDataFim(result.getDate("dataFim").toLocalDate());
			ret.setOrcamento(result.getInt("orcamento"));
		} catch (Exception e) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}