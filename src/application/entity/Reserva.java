package application.entity;

import java.time.LocalDate;

import application.dao.CarroDAO;
import application.dao.ClienteDAO;

public class Reserva {
	private int id = -1;
	private int idCliente;
	private int idCarro;
	private LocalDate dataLocacao;
	
	public Reserva() {}
	
	public Reserva(int idCliente, int idCarro, LocalDate dataLocacao) {
		this.idCarro = idCarro;
		this.idCliente = idCliente;
		this.dataLocacao = dataLocacao;
	}
	
	public Carro getCarro() {
		return ((new CarroDAO()).find(idCarro));
	}
	
	public Cliente getCliente() {
		return ((new ClienteDAO()).find(idCliente));
	}

	public int getId() {
		return id;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public int getIdCarro() {
		return idCarro;
	}

	public LocalDate getDataLocacao() {
		return dataLocacao;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setIdCarro(int idCarro) {
		this.idCarro = idCarro;
	}

	public void setDataLocacao(LocalDate dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
}
