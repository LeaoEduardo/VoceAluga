package application.entity;

public class EstadoCarro {
	int 	id;
	String 	tipo;
	public EstadoCarro() {
		id = -1;
	}
	public EstadoCarro( String tipo ) {
		id = -1;
		this.tipo = tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
