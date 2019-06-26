package application.entity;

import java.time.LocalDate;

import application.dao.CarroDAO;

public class Manutencao {
	private int			id = -1;
	private int 		idCarro;
	private LocalDate 	dataInicio;
	private LocalDate 	dataFim;
	private int 		orcamento;
	
	public Manutencao() {}
	
	public Manutencao(int idCarro, LocalDate dataInicio, LocalDate dataFim, int orcamento) {
		this.idCarro = idCarro;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.orcamento = orcamento;
	}
	
	public Carro getCarro() {
		return (new CarroDAO()).find(idCarro);
	}

	public int getId() {
		return id;
	}

	public int getIdCarro() {
		return idCarro;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public int getOrcamento() {
		return orcamento;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setIdCarro(int idCarro) {
		this.idCarro = idCarro;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public void setOrcamento(int orcamento) {
		this.orcamento = orcamento;
	}
	
}
