package application.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import application.entity.Locacao;

public class LocacaoDAO extends DAO<Locacao> {

	{
		table_name = "locacoes";
	}

	protected PreparedStatement createInsertStatement(Connection con, Locacao locacao) throws SQLException {
		String sql = "INSERT INTO locacoes " + " (id_cliente,id_carro,dataInicial,dataFinal,devolvido,nota) " + " values (?,?,?,?,?,?)";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		statement.setInt(1, locacao.getIdCliente());
		statement.setInt(2, locacao.getIdCarro());
		statement.setString(3, locacao.getDataInicial().format(formatter) );
		statement.setString(4, locacao.getDataFinal().format(formatter) );
		statement.setBoolean(5 ,  locacao.isDevolvido() );
		statement.setInt(6, locacao.getNota());

		return statement;
	}

	protected PreparedStatement createUpdateStatement(Connection con, Locacao locacao) throws SQLException {
		String sql =
				"UPDATE locacoes "
						+ " SET id_cliente=?,id_carro=?,dataInicial=?,dataFinal=?,devolvido=?,nota=? "
						+ " WHERE id=? ";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		statement.setInt(1, locacao.getIdCliente());
		statement.setInt(2, locacao.getIdCarro());
		statement.setString(3, locacao.getDataInicial().format(formatter) );
		statement.setString(4, locacao.getDataFinal().format(formatter) );
		statement.setBoolean(5, locacao.isDevolvido() );
		statement.setInt(6, locacao.getNota() );
		statement.setInt(7, locacao.getId() );

		return statement;
	}

	protected String createDeleteStatement(Locacao entity) {
		return "DELETE FROM " + table_name + " WHERE id = " + String.valueOf(entity.getId());
	}

	protected Locacao getEntityFromResultSet(ResultSet result) {
		Locacao ret = new Locacao();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			ret.setId(result.getInt("id"));
			ret.setIdCarro(result.getInt("id_carro"));
			ret.setIdCliente(result.getInt("id_cliente"));
			ret.setDevolvido(result.getBoolean("devolvido"));
			ret.setNota(result.getInt("nota"));
			ret.setDataInicial(	LocalDateTime.parse( result.getString("dataInicial") , formatter )  );
			ret.setDataFinal(	LocalDateTime.parse( result.getString("dataFinal") , formatter )  );
		} catch (Exception e) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}