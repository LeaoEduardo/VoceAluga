package application.entity;

import java.util.ArrayList;

import application.dao.FilialDAO;
import application.dao.FuncionarioDAO;
import application.dao.TipoFuncionarioDAO;

public class Funcionario {

	private int id = -1;
	private String nome;
	private String usuario;
	private String senha;
	private int idFilial;
	private int idTipo;
	private boolean ativo;

	public Funcionario() {
	}

	// Retornam outras entidades:
	public Filial getFilial() {
		return (new FilialDAO()).find(idFilial);
	}

	public TipoFuncionario getTipoFuncionario() {
		return (new TipoFuncionarioDAO()).find(idTipo);
	}

	// Metodos auxiliares:

	public static Funcionario loginFuncionario(String User, String senha) {
		ArrayList<Funcionario> todos_funcionarios = (new FuncionarioDAO()).findAll();
		for (Funcionario funcionario : todos_funcionarios) {
			// Estamos comparando sem ser sensivel ao lower/upper case aqui
			if (funcionario.getUsuario().toUpperCase().equals(User.toUpperCase())
					&& funcionario.getSenha().toUpperCase().equals(senha.toUpperCase())) {
				// if( funcionario.getUsuario().equals(User) &&
				// funcionario.getSenha().equals(senha) ) {
				return funcionario;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(int idFilial) {
		this.idFilial = idFilial;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
