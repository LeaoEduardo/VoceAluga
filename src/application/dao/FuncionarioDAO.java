package application.dao;

import java.sql.*;

import application.entity.Funcionario;

public class FuncionarioDAO extends DAO<Funcionario> {

	{
		table_name = "funcionario";
	}

	protected PreparedStatement createInsertStatement( Connection con , Funcionario funcionario) throws SQLException{
		String sql = "INSERT INTO funcionario (nome,usuario,senha,idFilial,idTipo,ativo) values (?,?,?,?,?,?);";
		PreparedStatement 	statement = con.prepareStatement(sql);
		statement.setString(1, funcionario.getNome());
		statement.setString(2, funcionario.getUsuario());
		statement.setString(3, funcionario.getSenha());
		statement.setInt(4, funcionario.getIdFilial());
		statement.setInt(5, funcionario.getIdTipo());
		statement.setBoolean(6, funcionario.isAtivo());
		return statement;
	}
	
	protected PreparedStatement createUpdateStatement( Connection con , Funcionario funcionario ) throws SQLException{

		String sql = "UPDATE funcionario "
				+ " SET nome=?,usuario=?,senha=?,idFilial=?,idTipo=?,ativo=? "
				+ " WHERE id=? ";
		PreparedStatement statement = con.prepareStatement(sql);
		
		statement.setString(1, funcionario.getNome());
		statement.setString(2, funcionario.getUsuario());
		statement.setString(3, funcionario.getSenha());
		statement.setInt(4, funcionario.getIdFilial());
		statement.setInt(5, funcionario.getIdTipo());
		statement.setBoolean(6, funcionario.isAtivo());
		statement.setInt(7, funcionario.getId());
		return statement;
	}

	protected String			createDeleteStatement( Funcionario entity ) {
		return "DELETE FROM " + table_name + " WHERE id = " + String.valueOf( entity.getId() );
	}
	
	protected Funcionario getEntityFromResultSet( ResultSet result ) {
		Funcionario ret = new Funcionario();
		try {
			ret.setId(result.getInt("id"));
			ret.setIdFilial(result.getInt("idFilial"));
			ret.setIdTipo(result.getInt("idTipo"));
			ret.setNome(result.getString("nome"));
			ret.setUsuario(result.getString("usuario"));
			ret.setSenha(result.getString("senha"));
			ret.setAtivo(result.getBoolean("ativo"));
		} catch (Exception e ) {
			ret = null;
			e.printStackTrace();
		}
		return ret;
	}
	
}
