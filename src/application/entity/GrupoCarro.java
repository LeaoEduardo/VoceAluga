package application.entity;

public class GrupoCarro {
	int 	id;
	String 	grupo;
	public GrupoCarro() {
		id = -1;
	}
	public GrupoCarro( String grupo ) {
		id = -1;
		this.grupo = grupo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	
}
