package application.entity;

import java.util.ArrayList;

import application.dao.GrupoCarroDAO;
import application.dao.ModeloCarroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModeloCarro {
	int 		id = -1;
	String		marca;
	String		modelo;
	int			idGrupo;
	
	public ModeloCarro() {
	}
	public ModeloCarro( String marca , String modelo , int idGrupo ) {
		this.marca = marca;
		this.modelo = modelo;
		this.idGrupo = idGrupo;
	}
	
	// Retorna outras entidades 
	public GrupoCarro getGrupoCarro() {
		return (new GrupoCarroDAO()).find(idGrupo);
	}
	
	// Métodos auxiliares
	public static ObservableList<String> getAllMarcas(){
    	ObservableList<String> nome_marcas = FXCollections.observableArrayList();
    	ArrayList<ModeloCarro> todos_modelos = (new ModeloCarroDAO()).findAll();
    	for( int i = 0 ; i < todos_modelos.size() ; i++ ) {
    		// Pra não pegar repetido!
    		String nome_marca = todos_modelos.get(i).getMarca();
    		if( nome_marcas.indexOf(nome_marca) == -1 ) {
        		nome_marcas.add( todos_modelos.get(i).getMarca() );	
    		}
    	}
    	return nome_marcas;
	}
	public static ObservableList<String> getAllModelosDaMarca( String nome_marca ){
    	ObservableList<String> ret = FXCollections.observableArrayList();
    	ArrayList<ModeloCarro> modelos = (new ModeloCarroDAO()).find("marca",nome_marca);
    	for( ModeloCarro modelo_carro : modelos ) {
    		if( modelo_carro.getMarca().equals(nome_marca) ) {
    			System.out.println("achei umm");
    			ret.add( modelo_carro.getModelo() );	
    		}
    	}
    	return ret;
	}
	
	
	// Getters e setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	
	
}
