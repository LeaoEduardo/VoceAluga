package application.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import application.dao.CarroDAO;
import application.dao.EstadoCarroDAO;
import application.dao.FilialDAO;
import application.dao.LocacaoDAO;
import application.dao.ModeloCarroDAO;

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
	
	public boolean confirmaLocacao( Cliente cliente , LocalDateTime dataHoraLocacao , LocalDateTime dataHoraDevolucao ) {
		int id_estado_disponivel = (new EstadoCarroDAO()).find("tipo","Disponível").get(0).getId();
		int id_estado_alugado = (new EstadoCarroDAO()).find("tipo","Alugado").get(0).getId();
		if( this.idEstado != id_estado_disponivel ) {
			System.out.println("WARNING: Carro alugado não está 'Disponível' no banco de dados");
			return false;
		}
		this.idEstado = id_estado_alugado;
		
		//Cria uma entidade locacao no BD:
		Locacao nova_locacao = new Locacao();
		nova_locacao.setDataInicial( dataHoraLocacao );
		nova_locacao.setDataFinal( dataHoraDevolucao );
		nova_locacao.setIdCliente( cliente.getId() );
		nova_locacao.setIdCarro( this.id );
		nova_locacao.setDevolvido( false );
		(new LocacaoDAO()).insert( nova_locacao );
		
		return (new CarroDAO()).update( this );
	}
}
