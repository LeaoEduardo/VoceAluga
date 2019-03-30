package application;

public class Usuario {
	
	private String usuario;
	private String nomeNivel;
	private String nomeFilial;
	private int nivel;
	
	public Usuario(String usuario, String nomeNivel, int nivel, String nomeFilial ) {
		
		this.usuario = usuario;
		this.nomeNivel = nomeNivel;
		this.nivel = nivel;
		this.nomeFilial = nomeFilial;
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
	
	public String getNomeFilial() {
		return nomeFilial;
	}	
}
