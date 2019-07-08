package application.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import application.Contexto;
import application.dao.CarroDAO;
import application.dao.EstadoCarroDAO;
import application.dao.FilialDAO;
import application.dao.ModeloCarroDAO;
import application.entity.Reserva;

public class Carro {

	private int id = -1;
	private String placa;
	private LocalDate dataCompra;
	private LocalDate dataManutencao;
	private int quilometragem;
	private int idModelo;
	private int idFilial;
	private int idEstado;

	public Carro() {
	}

	public Carro(String placa, int quilometragem, int id_modelo, int id_filial, int id_estado, LocalDate dataCompra,
			LocalDate dataManutencao) {
		this.placa = placa;
		this.quilometragem = quilometragem;
		this.idModelo = id_modelo;
		this.idFilial = id_filial;
		this.idEstado = id_estado;
		this.dataCompra = dataCompra;
		this.dataManutencao = dataManutencao;
	}

	// Retorna outras entidades
	public Filial getFilial() {
		return (new FilialDAO()).find(idFilial);
	}

	public ModeloCarro getModeloCarro() {
		return (new ModeloCarroDAO()).find(idModelo);
	}

	public EstadoCarro getEstadoCarro() {
		return (new EstadoCarroDAO()).find(idEstado);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}

	public LocalDate getDataManutencao() {
		return dataManutencao;
	}

	public void setDataManutencao(LocalDate dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	public int getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(int quilometragem) {
		this.quilometragem = quilometragem;
	}

	public int getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public int getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(int idFilial) {
		this.idFilial = idFilial;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	// Métodos auxiliares
	public ArrayList<Carro> getCarrosDisponiveis( Reserva reserva ){
		CarroDAO carro_dao = new CarroDAO();
		ArrayList<Carro> ret = new ArrayList<Carro>();;
		ArrayList<Carro> carros_presentes = carro_dao.find("idFilial" , String.valueOf(Contexto.getInstancia().getFuncionario().getIdFilial())  );
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
	
	
	
	
	
}
