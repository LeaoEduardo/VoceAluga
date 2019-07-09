package application;

import application.entity.Cliente;
import application.entity.Funcionario;
import application.entity.Carro;
import application.entity.Locacao;
import application.entity.Reserva;
import application.entity.Manutencao;
import javafx.collections.ObservableList;

public class Contexto {

	// Essa classe eh usada para fazer a integracao de dados entre os diferentes
	// controllers (classes)

	private final static Contexto instancia = new Contexto();

	public static Contexto getInstancia() {
		return instancia;
	}

	private Funcionario funcionario;
	private Cliente cliente;
	private Carro veiculo;
	private FormatadorTexto formatador = new FormatadorTexto();
	private ObservableList<Reserva> listaReservas;
	private ObservableList<Manutencao> listaManutencoes;
	private ObservableList<Locacao> listaLocacoes;
	private ObservableList<Carro> listaVeiculos;
	private ObservableList<String> listaMarcas;
	private ObservableList<String> listaModelosDaMarca;
	private ObservableList<String> listaFiliais;
	private boolean voltandoParaVeiculos;
	private boolean voltandoParaCliente;
	private boolean voltandoParaLocacao;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Carro getVeiculo() {
		return veiculo;
	}

	public FormatadorTexto getFormatador() {
		return formatador;
	}

	public ObservableList<Carro> getListaVeiculos() {
		return listaVeiculos;
	}

	public ObservableList<String> getListaMarcas() {
		return listaMarcas;
	}

	public ObservableList<String> getListaModelosDaMarca() {
		return listaModelosDaMarca;
	}

	public ObservableList<String> getListaFiliais() {
		return listaFiliais;
	}

	public boolean getVoltandoParaVeiculos() {
		return voltandoParaVeiculos;
	}
	
	public boolean getVoltandoParaCliente() {
		return voltandoParaCliente;
	}
	
	public boolean getVoltandoParaLocacao() {
		return voltandoParaLocacao;
	}

	public ObservableList<Reserva> getListaReservas() {
		return listaReservas;
	}

	public ObservableList<Manutencao> getListaManutencoes() {
		return listaManutencoes;
	}

	public ObservableList<Locacao> getListaLocacoes() {
		return listaLocacoes;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setVeiculo(Carro veiculo) {
		this.veiculo = veiculo;
		System.out.println("VEICULO DADOS:");
		System.out.println("id: " + String.valueOf(veiculo.getId()));
		System.out.println("placa: " + veiculo.getPlaca());
		System.out.println("quilometragem: " + String.valueOf(veiculo.getQuilometragem()));
		System.out.println("filial: " + veiculo.getFilial().getNome());
		// System.out.println("estado: " + estado);
		System.out.println("dataCompra: " + veiculo.getDataCompra());
		System.out.println("dataManutencao: " + veiculo.getDataManutencao());
	}

	public void setListaVeiculos(ObservableList<Carro> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	public void setListaMarcas(ObservableList<String> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}

	public void setListaModelosDaMarca(ObservableList<String> listaModelosDaMarca) {
		this.listaModelosDaMarca = listaModelosDaMarca;
	}

	public void setListaFiliais(ObservableList<String> listaFiliais) {
		this.listaFiliais = listaFiliais;
	}

	public void setVoltandoParaVeiculos(boolean b) {
		this.voltandoParaVeiculos = b;
	}
	
	public void setVoltandoParaCliente(boolean b) {
		this.voltandoParaCliente = b;
	}
	
	public void setVoltandoParaLocacao(boolean b) {
		this.voltandoParaLocacao = b;
	}

	public void setListaReservas(ObservableList<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public void setListaManutencoes(ObservableList<Manutencao> listaManutencoes) {
		this.listaManutencoes = listaManutencoes;
	}

	public void setListaLocacoes(ObservableList<Locacao> listaLocacoes) {
		this.listaLocacoes = listaLocacoes;
	}
}