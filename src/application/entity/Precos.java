package application.entity;

import application.dao.GrupoCarroDAO;

public class Precos {

	private int id = -1;
	private float valor;
	private int id_grupo;

	public Precos() {}

	public Precos(int id_grupo, float valor) {
		this.id_grupo = id_grupo;
		this.valor = valor;
	}

	public GrupoCarro getGrupo() {
		return (new GrupoCarroDAO()).find(id_grupo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}

}
