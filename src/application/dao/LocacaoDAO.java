package application.dao;

import java.sql.*;

import application.entity.Locacao;

public class LocacaoDAO extends DAO<Locacao> {
	
	{
		table_name = "locacoes";
	}
	
	protected PreparedStatement createInsertStatement( Connection con , Locacao locacao ) throws SQLException{
		String sql = "INSERT INTO locacoes "
				+ " (id_cliente,id_carro,dataInicial,dataFinal) "
				+ " values (?,?,?,?)";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setInt(1, locacao.getIdCliente());
		statement.setInt(2, locacao.getIdCarro());
		statement.setDate(3, Date.valueOf(locacao.getDataInicial()));
		statement.setDate(4, Date.valueOf(locacao.getDataFinal()));
		
		return statement;
	}
	
	protected PreparedStatement createUpdateStatement( Connection con , Locacao locacao) throws SQLException{
		String sql = "UPDATE locacoes "
				+ " SET id_cliente=?,id_carro=?,dataInicial=?,dataFinal=? "
				+ " WHERE id=? ";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setInt(1, locacao.getIdCliente());
		statement.setInt(2, locacao.getIdCarro());
		statement.setDate(3, Date.valueOf(locacao.getDataInicial()));
		statement.setDate(4, Date.valueOf(locacao.getDataFinal()));
		
		return statement;
	}
	
	protected String createDeleteStatement( Locacao entity ) {
		return "DELETE FROM " + table_name + " WHERE id = " + String.valueOf( entity.getId() );
	}
	
	protected Locacao getEntityFromResultSet( ResultSet result ) {
		Locacao ret = new Locacao();
		try {
			ret.setId( result.getInt("id") );
			ret.setIdCarro( result.getInt("id_carro") );
			ret.setIdCliente( result.getInt("id_cliente") );
			ret.setDataInicial( result.getDate("dataInicial").toLocalDate() );
			ret.setDataFinal( result.getDate("dataFinal").toLocalDate() );
		} catch (Exception e ) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}