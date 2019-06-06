package application.dao;

import java.sql.*;
import java.util.ArrayList;

import application.ConnectionSQL;
import application.entity.Carro;

public class CarroDAO extends DAO {
	
	public static CarroDAO carro_dao = new CarroDAO();
	
	public static Carro find( int id ) {
		return (Carro) carro_dao.find("carro",id);
	}
	public static ArrayList<Carro> findAll(){
		ArrayList<Object> obj_list = carro_dao.findAll("carro");
		ArrayList<Carro> ret = new ArrayList<Carro>();
		for( int i = 0 ; i < obj_list.size(); i++ ) {
			ret.add( (Carro)obj_list.get(i) );
		}
		return ret;
	}
	
	public static boolean insertCarro( Carro carro ) {
		boolean ret = true;
		Connection 			con = null;
		PreparedStatement 	statement = null;
		
		String sql = "INSERT INTO carro (placa,dataManutencao,dataCompra,quilometragem,idModelo,idFilial,idEstado) values (?,?,?,?,?,?,?);";
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);

			statement.setString(1, carro.getPlaca() );
			statement.setDate(2, Date.valueOf(carro.getDataManutencao()) );
			statement.setDate(3, Date.valueOf(carro.getDataCompra()) );
			statement.setInt(4, carro.getQuilometragem() );
			statement.setInt(5, carro.getIdModelo() );
			statement.setInt(6, carro.getIdFilial() );
			statement.setInt(7, carro.getIdEstado() );
			
			// TO-DO: Fazer a validação adequada
			
			statement.execute();
			ret = statement.getUpdateCount() > 0;
		} catch ( Exception e ) {
			ret = false;
			e.printStackTrace();
		} finally {
			try{ if(con!=null)con.close();}catch(Exception e) {}
			try{ if(statement!=null)statement.close();}catch(Exception e) {}
		}
		
		return ret;
	}
	
	public static boolean updateCarro( Carro carro ) {
		boolean ret = true;
		Connection 			con = null;
		PreparedStatement 	statement = null;
		
		String sql = " UPDATE TABLE carro "
				+ " SET placa=?,dataManutencao=?,dataCompra=?,quilometragem=?,idModelo=?,idFilial=?,idEstado=? "
				+ " WHERE id=? ";
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);

			statement.setString(1, carro.getPlaca() );
			statement.setDate(2, Date.valueOf(carro.getDataManutencao()) );
			statement.setDate(3, Date.valueOf(carro.getDataCompra()) );
			statement.setInt(4, carro.getQuilometragem() );
			statement.setInt(5, carro.getIdModelo() );
			statement.setInt(6, carro.getIdFilial() );
			statement.setInt(7, carro.getIdEstado() );
			
			ret = statement.execute();
		} catch ( Exception e ) {
			ret = false;
			e.printStackTrace();
		} finally {
			try{ if(con!=null)con.close();}catch(Exception e) {}
			try{ if(statement!=null)statement.close();}catch(Exception e) {}
		}
		
		return ret;
	}
	
	
	protected Object getEntityFromResultSet( ResultSet result ) {
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
