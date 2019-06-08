package application.entity;

public class EstadoCarro {
	int 	id = -1;
	String 	tipo;
	
	public EstadoCarro() {
	}
	public EstadoCarro( String tipo ) {
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
