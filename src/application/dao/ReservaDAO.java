package application.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import application.entity.Reserva;

public class ReservaDAO extends DAO<Reserva> {

	{
		table_name = "reservas";
	}

	protected PreparedStatement createInsertStatement(Connection con, Reserva reserva) throws SQLException {
		String sql = "INSERT INTO reservas " + " (id_cliente,id_grupo,id_modelo,dataLocacao,dataDevolucao) "
				+ " values (?,?,?,?,?)";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setInt(1, reserva.getIdCliente());
		statement.setInt(2, reserva.getIdGrupo());
		statement.setInt(3, reserva.getIdModelo());
		statement.setString(4, reserva.getDataLocacao().format(formatter));
		statement.setString(5, reserva.getDataDevolucao().format(formatter));

		return statement;
	}

	protected PreparedStatement createUpdateStatement(Connection con, Reserva reserva) throws SQLException {
		String sql = "UPDATE reservas " + " SET id_cliente=?,id_grupo=?,id_modelo=?,dataLocacao=?,dataDevolucao=? "
				+ " WHERE id=? ";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setInt(1, reserva.getIdCliente());
		statement.setInt(2, reserva.getIdGrupo());
		statement.setInt(3, reserva.getIdModelo());
		statement.setString(4, reserva.getDataLocacao().format(formatter));
		statement.setString(5, reserva.getDataDevolucao().format(formatter));

		return statement;
	}

	protected String createDeleteStatement(Reserva entity) {
		return "DELETE FROM " + table_name + " WHERE id = " + String.valueOf(entity.getId());
	}

	protected Reserva getEntityFromResultSet(ResultSet result) {
		Reserva ret = new Reserva();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			ret.setId(result.getInt("id"));
			ret.setIdGrupo(result.getInt("id_grupo"));
			ret.setIdModelo(result.getInt("id_modelo"));
			ret.setIdCliente(result.getInt("id_cliente"));			
			ret.setDataLocacao(LocalDateTime.parse(result.getString("dataLocacao"), formatter));
			ret.setDataDevolucao(LocalDateTime.parse(result.getString("dataDevolucao"), formatter));
		} catch (Exception e) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}