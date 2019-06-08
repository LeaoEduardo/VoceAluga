package application.entity;

import java.time.LocalDate;

import application.dao.EstadoCarroDAO;
import application.dao.FilialDAO;
import application.dao.ModeloCarroDAO;

public class Carro {
	
	private int 		id = -1;
	private String 		placa;
	private LocalDate 	dataCompra;
	private LocalDate 	dataManutencao;
	private int 		quilometragem;
	private int 		idModelo;
	private int 		idFilial;
	private int 		idEstado;
	
	public Carro() {
	}
	public Carro( String placa, int quilometragem, int id_modelo, int id_filial,
					int id_estado, LocalDate dataCompra, LocalDate dataManutencao){
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

	
}
