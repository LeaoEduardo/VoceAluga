package application;

public class Usuario {
	
	private String usuario;
	private String nomeNivel;
	private int nivel;
	
	public Usuario(String usuario, String nomeNivel, int nivel ) {
		this.usuario = usuario;
		this.nivel = nivel;
		this.nomeNivel = nomeNivel;
	}

	public String getUsuario() {
		return usuario;
	}

	public int getNivel() {
		return nivel;
	}	
	
	public String getNomeNivel() {
		return nomeNivel;
	}	
}
