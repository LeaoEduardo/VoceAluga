package application.entity;

import java.util.ArrayList;

import application.dao.FilialDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Filial {
	private	int 	id;
	private String 	nome;
	
	public Filial() {
		id = -1;
		nome = "null";
	}

	public static ObservableList<String> getAllFilialNomes(){
    	ObservableList<String> nome_filiais = FXCollections.observableArrayList();
    	ArrayList<Filial> todas_filiais = FilialDAO.findAll();
    	for( int i = 0 ; i < todas_filiais.size() ; i++ ) {
    		nome_filiais.add(todas_filiais.get(i).getNome());
    	}
    	return nome_filiais;
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
