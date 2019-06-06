package application.dao;

import java.sql.*;
import java.util.ArrayList;

import application.ConnectionSQL;
import application.entity.Cliente;

public class ClienteDAO extends DAO {
	
	public static ClienteDAO cliente_dao = new ClienteDAO();
	
	public static Cliente find( int id ) {
		return (Cliente) cliente_dao.find("cliente",id);
	}
	public static ArrayList<Cliente> findAll(){
		ArrayList<Object> obj_list = cliente_dao.findAll("cliente");
		ArrayList<Cliente> ret = new ArrayList<Cliente>();
		for( int i = 0 ; i < obj_list.size(); i++ ) {
			ret.add( (Cliente)obj_list.get(i) );
		}
		return ret;
	}
	
	public static boolean insertCliente( Cliente cliente ) {
		boolean ret = true;
		Connection 			con = null;
		PreparedStatement 	statement = null;
		
		String sql = "INSERT INTO cliente (nome,CPF,passaporte,dataNascimento,nacionalidade,telefone,CNH,dataCNH,ativo) values (?,?,?,?,?,?,?,?,?);";
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);

			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getCpf());
			statement.setString(3, cliente.getPassaporte());
			statement.setDate(4, Date.valueOf(cliente.getDataNasc()));
			statement.setString(5, cliente.getNacionalidade());
			statement.setString(6, cliente.getTelefone());
			statement.setString(7, cliente.getCnh());
			statement.setDate(8, Date.valueOf(cliente.getDataCnh()) );
			statement.setBoolean(9, cliente.isAtivo());
			
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
	
	public static boolean updateCliente( Cliente cliente ) {
		boolean ret = true;
		Connection 			con = null;
		PreparedStatement 	statement = null;
		
		String sql = "UPDATE TABLE cliente "
				+ " SET nome=?,CPF=?,passaporte=?,dataNascimento=?,nacionalidade=?,telefone=?,CNH=?,dataCNH=?,ativo=? "
				+ " WHERE id=? ";
		try {
			con = ConnectionSQL.getConnection();
			statement = con.prepareStatement(sql);

			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getCpf());
			statement.setString(3, cliente.getPassaporte());
			statement.setDate(4, Date.valueOf(cliente.getDataNasc()));
			statement.setString(5, cliente.getNacionalidade());
			statement.setString(6, cliente.getTelefone());
			statement.setString(7, cliente.getCnh());
			statement.setDate(8, Date.valueOf(cliente.getDataCnh()) );
			statement.setBoolean(9, cliente.isAtivo());
			
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
		Cliente ret = new Cliente();
		try {
			ret.setId( result.getInt("id") );
			ret.setNome( result.getString("nome") );
			ret.setCpf( result.getString("CPF") );
			ret.setPassaporte( result.getString("passaporte") );
			ret.setDataNasc( result.getDate("dataNascimento").toLocalDate() );
			ret.setNacionalidade( result.getString("nacionalidade") );
			ret.setTelefone( result.getString("telefone") );
			ret.setCnh( result.getString("CNH") );
			ret.setDataCnh( result.getDate("dataCNH").toLocalDate() );
			ret.setAtivo( result.getBoolean("ativo") );
		} catch (Exception e ) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}
