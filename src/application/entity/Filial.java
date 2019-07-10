package application.entity;

import java.util.ArrayList;

import application.dao.CarroDAO;
import application.dao.EstadoCarroDAO;
import application.dao.FilialDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Filial {
	private int id = -1;
	private String nome;

	public Filial() {
		nome = "null";
	}

	public static ObservableList<String> getAllFilialNomes() {
		ObservableList<String> nome_filiais = FXCollections.observableArrayList();
		ArrayList<Filial> todas_filiais = (new FilialDAO()).findAll();
		for (int i = 0; i < todas_filiais.size(); i++) {
			nome_filiais.add(todas_filiais.get(i).getNome());
		}
		return nome_filiais;
	}


	public ArrayList<Carro> getCarrosDisponiveis( Reserva reserva ){
		CarroDAO carro_dao = new CarroDAO();
		ArrayList<Carro> ret = new ArrayList<Carro>();
		ArrayList<Carro> carros_presentes = carro_dao.find("idFilial" , String.valueOf( this.id )  );
		int id_estado_disponivel = (new EstadoCarroDAO()).find("tipo", "Disponível").get(0).getId();
		
		// Caso uma reserva nao tenha sido especificada
		if( reserva == null ) {
			for( Carro carro : carros_presentes ) {
				if( carro.getIdEstado() == id_estado_disponivel ) {
					ret.add(carro);
				}
			}
		} 
		
		//Caso uma reserva tenha sido especificada
		else {
			for( Carro carro : carros_presentes ) {
				ModeloCarro modelo = carro.getModeloCarro();
				int id_grupo_carro = modelo.getIdGrupo();
				if( id_grupo_carro <= reserva.getIdGrupo() && carro.getIdEstado() == id_estado_disponivel ) {
					// Caso onde um modelo especifico tenha sido requisitado:
					if( reserva.getIdModelo() != -1 && reserva.getIdModelo() != modelo.getId() ) {
						continue;
					}
					ret.add(carro);
				}
			}
		}
			
		return ret;
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
