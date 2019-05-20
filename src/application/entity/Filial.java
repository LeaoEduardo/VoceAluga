package application.entity;

public class Filial {
	private	int 	id;
	private String 	nome;
	
	public Filial() {
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
