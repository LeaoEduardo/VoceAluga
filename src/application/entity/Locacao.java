package application.entity;

import java.time.LocalDateTime;

import application.dao.CarroDAO;
import application.dao.ClienteDAO;
import application.dao.EstadoCarroDAO;
import application.dao.LocacaoDAO;

public class Locacao {
	private int id = -1;
	private int idCliente;
	private int idCarro;
	private LocalDateTime 	dataInicial;
	private LocalDateTime 	dataFinal;
	private boolean		devolvido;
	private int nota = 0;
	private int custo = 0;
	private boolean fidelidade = false; //se o pagamento fi feito com programa de fidelidade

	public Locacao() {
	}

	public Locacao(int idCliente, int idCarro, LocalDateTime dataInicial, LocalDateTime dataFinal , boolean devolvido, int nota, int custo, boolean fidelidade ) {
		this.idCarro = idCarro;
		this.idCliente = idCliente;
		this.dataFinal = dataFinal;
		this.dataInicial = dataInicial;
		this.devolvido = devolvido;
		this.nota = nota;
		this.custo = custo;
		this.fidelidade = fidelidade;
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

	public int getNota() {
		return nota;
	}
	
	public boolean getDevolvido() {
		return devolvido;
	}

	public int getCusto() {
		return custo;
	}

	public boolean getFidelidade() {
		return fidelidade;
	}

	public LocalDateTime getDataInicial() {
		return dataInicial;
	}

	public LocalDateTime getDataFinal() {
		return dataFinal;
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

	public void setNota(int nota) {
		this.nota = nota;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}

	public void setFidelidade(boolean fidelidade) {
		this.fidelidade = fidelidade;
	}

	public void setDataInicial(LocalDateTime dataInicial) {
		this.dataInicial = dataInicial;
	}

	public void setDataFinal(LocalDateTime dataHoraDevolucao) {
		this.dataFinal = dataHoraDevolucao;
	}

	public boolean isDevolvido() {
		return devolvido;
	}

	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}

	// ---
	public boolean confirmaDevolucao( Filial filial_devolvida, int nota, int custo, boolean fidelidade ) {
		Carro carro = getCarro();
		int id_estado_disponivel = (new EstadoCarroDAO()).find("tipo","Disponível").get(0).getId();
		if( carro.getIdEstado() == id_estado_disponivel ) {
			System.out.println("WARNING: Carro devolvido j� esta como 'Disponível'");
		}
		carro.setIdEstado(id_estado_disponivel);
		carro.setIdFilial(filial_devolvida.getId());
		(new CarroDAO()).update( carro );

		setDevolvido(true);
		setNota(nota);
		setCusto(custo);
		setFidelidade(fidelidade);
		setDataFinal( LocalDateTime.now() );
		return (new LocacaoDAO()).update(this);
	}
}
