package application.entity;

public class TipoFuncionario {
	private	int 	id;
	private String 	nome;
	
	public TipoFuncionario() {
		id = -1;
		nome = "null";
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
}
