package application.dao;

import java.sql.*;
import application.entity.Carro;

public class CarroDAO extends DAO<Carro> {
	
	{
		table_name = "carro";
	}
	

	protected PreparedStatement createInsertStatement( Connection con , Carro carro ) throws SQLException{
		String sql = "INSERT INTO carro "
				+ " (placa,dataManutencao,dataCompra,quilometragem,idModelo,idFilial,idEstado) "
				+ " values (?,?,?,?,?,?,?)";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setString(1, carro.getPlaca() );
		statement.setDate(2, Date.valueOf(carro.getDataManutencao()) );
		statement.setDate(3, Date.valueOf(carro.getDataCompra()) );
		statement.setInt(4, carro.getQuilometragem() );
		statement.setInt(5, carro.getIdModelo() );
		statement.setInt(6, carro.getIdFilial() );
		statement.setInt(7, carro.getIdEstado() );
		return statement;
	}
	
	protected PreparedStatement createUpdateStatement( Connection con , Carro carro) throws SQLException{
		String sql = "UPDATE carro "
				+ " SET placa=?,dataManutencao=?,dataCompra=?,quilometragem=?,idModelo=?,idFilial=?,idEstado=? "
				+ " WHERE id=? ";

		PreparedStatement statement = con.prepareStatement(sql);
		statement = con.prepareStatement(sql);

		statement.setString(1, carro.getPlaca() );
		statement.setDate(2, Date.valueOf(carro.getDataManutencao()) );
		statement.setDate(3, Date.valueOf(carro.getDataCompra()) );
		statement.setInt(4, carro.getQuilometragem() );
		statement.setInt(5, carro.getIdModelo() );
		statement.setInt(6, carro.getIdFilial() );
		statement.setInt(7, carro.getIdEstado() );
		statement.setInt(8, carro.getId() );
		return statement;
	}

	protected String			createDeleteStatement( Carro entity ) {
		return "DELETE FROM " + table_name + " WHERE id = " + String.valueOf( entity.getId() );
	}
	protected Carro getEntityFromResultSet( ResultSet result ) {
		Carro ret = new Carro();
		try {
			ret.setId( result.getInt("id") );
			ret.setPlaca( result.getString("placa"));
			ret.setDataManutencao( result.getDate("dataManutencao").toLocalDate() );
			ret.setDataCompra( result.getDate("dataCompra").toLocalDate() );
			ret.setQuilometragem( result.getInt("quilometragem"));
			ret.setIdModelo( result.getInt("idModelo"));
			ret.setIdFilial( result.getInt("idFilial"));
			ret.setIdEstado( result.getInt("idEstado"));
			
		} catch (Exception e ) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}
