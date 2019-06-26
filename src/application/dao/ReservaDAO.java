package application.dao;

import java.sql.*;

import application.entity.Reserva;

public class ReservaDAO extends DAO<Reserva> {

	{
		table_name = "reservas";
	}
	
	protected PreparedStatement createInsertStatement( Connection con , Reserva reserva ) throws SQLException{
		String sql = "INSERT INTO reservas "
				+ " (id_cliente,id_carro,dataLocacao) "
				+ " values (?,?,?)";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setInt(1, reserva.getIdCliente());
		statement.setInt(2, reserva.getIdCarro());
		statement.setDate(3, Date.valueOf(reserva.getDataLocacao()));
		
		return statement;
	}
	
	protected PreparedStatement createUpdateStatement( Connection con , Reserva reserva) throws SQLException{
		String sql = "UPDATE reservas "
				+ " SET id_cliente=?,id_carro=?,dataLocacao=? "
				+ " WHERE id=? ";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setInt(1, reserva.getIdCliente());
		statement.setInt(2, reserva.getIdCarro());
		statement.setDate(3, Date.valueOf(reserva.getDataLocacao()));
		
		return statement;
	}
	
	protected String createDeleteStatement( Reserva entity ) {
		return "DELETE FROM " + table_name + " WHERE id = " + String.valueOf( entity.getId() );
	}
	
	protected Reserva getEntityFromResultSet( ResultSet result ) {
		Reserva ret = new Reserva();
		try {
			ret.setId( result.getInt("id") );
			ret.setIdCarro( result.getInt("id_carro") );
			ret.setIdCliente( result.getInt("id_cliente") );
			ret.setDataLocacao( result.getDate("dataLocacao").toLocalDate() );
		} catch (Exception e ) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}