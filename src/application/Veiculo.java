package application;

import java.time.LocalDate;

public class Veiculo {
	
	private int id;
	private String placa;
	private int quilometragem;
	private String marca;
	private String modelo;
	private String filial;
	private String grupo;
	private String estado;
	private LocalDate dataCompra;
	private LocalDate dataManutencao;
	
	public Veiculo(int id, String placa, int quilometragem, String marca, String modelo, String filial, String grupo,
					String estado, LocalDate dataCompra, LocalDate dataManutencao){
		this.id = id;
		this.placa = placa;
		this.quilometragem = quilometragem;
		this.marca = marca;
		this.modelo = modelo;
		this.filial = filial;
		this.grupo = grupo;
		this.estado = estado;
		this.dataCompra = dataCompra;
		this.dataManutencao = dataManutencao;
	}

	public int getId() {
		return id;
	}

	public String getPlaca() {
		return placa;
	}

	public int getQuilometragem() {
		return quilometragem;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public String getModelo() {
		return modelo;
	}

	public String getFilial() {
		return filial;
	}

	public String getGrupo() {
		return grupo;
	}

	public String getEstado() {
		return estado;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public LocalDate getDataManutencao() {
		return dataManutencao;
	}
}
