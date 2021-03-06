package application.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import application.dao.ModeloCarroDAO;
import application.dao.GrupoCarroDAO;
import application.dao.ClienteDAO;

public class Reserva {
	private int id = -1;
	private int idCliente;
	private int idGrupo;
	private int idModelo;
	private LocalDateTime dataLocacao;
	private LocalDateTime dataDevolucao;

	public Reserva() {
	}

	public Reserva(int idCliente, int idGrupo, int idModelo, LocalDateTime dataLocacao, LocalDateTime dataDevolucao) {
		this.idGrupo = idGrupo;
		this.idModelo = idModelo;
		this.idCliente = idCliente;
		this.dataLocacao = dataLocacao;
		this.dataDevolucao= dataDevolucao;

		if (idGrupo == -1 && idModelo != -1) {
			idGrupo = (new ModeloCarroDAO()).find(idModelo).getIdGrupo();
		}
	}

	public GrupoCarro getGrupoCarro() {
		return (new GrupoCarroDAO()).find(idGrupo);
	}

	public ModeloCarro getModeloReserva() {
		return ((new ModeloCarroDAO()).find(idModelo));
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

	public LocalDateTime getDataLocacao() {
		return dataLocacao;
	}

	public int getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}

	public int getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public LocalDateTime getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDateTime dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setDataLocacao(LocalDateTime dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public String getEstadoReserva() {
		return "Reservado";
	}
}
