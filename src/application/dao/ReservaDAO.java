package application.dao;

import java.sql.*;

import application.entity.Reserva;

public class ReservaDAO extends DAO<Reserva> {

	{
		table_name = "reservas";
	}

	protected PreparedStatement createInsertStatement(Connection con, Reserva reserva) throws SQLException {
		String sql = "INSERT INTO reservas " + " (id_cliente,id_grupo,id_modelo,dataLocacao,dataDevolucao) "
				+ " values (?,?,?,?,?)";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setInt(1, reserva.getIdCliente());
		statement.setInt(2, reserva.getIdGrupo());
		statement.setInt(3, reserva.getIdModelo());
		statement.setDate(4, Date.valueOf(reserva.getDataLocacao()));
		statement.setDate(5, Date.valueOf(reserva.getDataDevolucao()));

		return statement;
	}

	protected PreparedStatement createUpdateStatement(Connection con, Reserva reserva) throws SQLException {
		String sql = "UPDATE reservas " + " SET id_cliente=?,id_grupo=?,id_modelo=?,dataLocacao=?,dataDevolucao=? "
				+ " WHERE id=? ";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setInt(1, reserva.getIdCliente());
		statement.setInt(2, reserva.getIdGrupo());
		statement.setInt(3, reserva.getIdModelo());
		statement.setDate(4, Date.valueOf(reserva.getDataLocacao()));
		statement.setDate(5, Date.valueOf(reserva.getDataDevolucao()));

		return statement;
	}

	protected String createDeleteStatement(Reserva entity) {
		return "DELETE FROM " + table_name + " WHERE id = " + String.valueOf(entity.getId());
	}

	protected Reserva getEntityFromResultSet(ResultSet result) {
		Reserva ret = new Reserva();
		try {
			ret.setId(result.getInt("id"));
			ret.setIdGrupo(result.getInt("id_grupo"));
			ret.setIdModelo(result.getInt("id_modelo"));
			ret.setIdCliente(result.getInt("id_cliente"));
			ret.setDataLocacao(result.getDate("dataLocacao").toLocalDate());
			ret.setDataLocacao(result.getDate("dataDevolucao").toLocalDate());
		} catch (Exception e) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}