package application.entity;

import application.dao.GrupoCarroDAO;
import application.dao.PrecosDAO;

public class GrupoCarro {
	int id = -1;
	String grupo;

	public GrupoCarro() {
	}

	public GrupoCarro(String grupo) {
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

	public Precos getPreco(int id_grupo) {
		return (new PrecosDAO()).find("id_grupo",Integer.toString(id_grupo)).get(0);
	}

}
