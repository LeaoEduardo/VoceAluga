package application.dao;

import java.sql.*;
import application.ConnectionSQL;
import application.entity.Cliente;

public class ClienteDAO extends DAO<Cliente> {

	{
		table_name = "cliente";
	}

	protected PreparedStatement createInsertStatement(Connection con, Cliente cliente) throws SQLException {
		PreparedStatement statement = null;

		String sql = "INSERT INTO cliente (nome,CPF,passaporte,dataNascimento,nacionalidade,telefone,CNH,dataCNH,ativo) values (?,?,?,?,?,?,?,?,?);";
		con = ConnectionSQL.getConnection();
		statement = con.prepareStatement(sql);

		statement.setString(1, cliente.getNome());
		statement.setString(2, cliente.getCpf());
		statement.setString(3, cliente.getPassaporte());
		statement.setDate(4, Date.valueOf(cliente.getDataNasc()));
		statement.setString(5, cliente.getNacionalidade());
		statement.setString(6, cliente.getTelefone());
		statement.setString(7, cliente.getCnh());
		statement.setDate(8, Date.valueOf(cliente.getDataCnh()));
		statement.setBoolean(9, cliente.isAtivo());
		return statement;
	}

	protected PreparedStatement createUpdateStatement(Connection con, Cliente entity) throws SQLException {
		String sql = "UPDATE cliente "
				+ " SET nome=?,CPF=?,passaporte=?,dataNascimento=?,nacionalidade=?,telefone=?,CNH=?,dataCNH=?,ativo=? "
				+ " WHERE id=? ";
		PreparedStatement statement = con.prepareStatement(sql);

		statement.setString(1, entity.getNome());
		statement.setString(2, entity.getCpf());
		statement.setString(3, entity.getPassaporte());
		statement.setDate(4, Date.valueOf(entity.getDataNasc()));
		statement.setString(5, entity.getNacionalidade());
		statement.setString(6, entity.getTelefone());
		statement.setString(7, entity.getCnh());
		statement.setDate(8, Date.valueOf(entity.getDataCnh()));
		statement.setBoolean(9, entity.isAtivo());
		statement.setInt(10, entity.getId());
		return statement;
	}

	protected String createDeleteStatement(Cliente entity) {
		if(entity.getCpf() != null)
			return "DELETE FROM " + table_name + " WHERE CPF = " + String.valueOf(entity.getCpf());
		if(entity.getPassaporte() != null)
			return "DELETE FROM " + table_name + " WHERE passaporte = " + String.valueOf(entity.getPassaporte());
		return "DELETE FROM " + table_name + " WHERE id = " + String.valueOf(entity.getId());
		
	}

	protected Cliente getEntityFromResultSet(ResultSet result) {
		Cliente ret = new Cliente();
		try {
			ret.setId(result.getInt("id"));
			ret.setNome(result.getString("nome"));
			ret.setCpf(result.getString("CPF"));
			ret.setPassaporte(result.getString("passaporte"));
			ret.setDataNasc(result.getDate("dataNascimento").toLocalDate());
			ret.setNacionalidade(result.getString("nacionalidade"));
			ret.setTelefone(result.getString("telefone"));
			ret.setCnh(result.getString("CNH"));
			ret.setDataCnh(result.getDate("dataCNH").toLocalDate());
			ret.setAtivo(result.getBoolean("ativo"));
		} catch (Exception e) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
}
