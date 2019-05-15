package application;

import java.time.LocalDate;
import javafx.collections.ObservableList;

public class Contexto {
	
	// Essa classe eh usada para fazer a integracao de dados entre os diferentes controllers (classes)
	
    private final static Contexto instancia = new Contexto();

    public static Contexto getInstancia() {
        return instancia;
    }
    
    private Usuario usuario;
    private Cliente cliente;
    private Veiculo veiculo;
    private FormatadorTexto formatador = new FormatadorTexto();
    private ObservableList<Veiculo> listaVeiculos;
    private ObservableList<String> listaMarcas;
    private ObservableList<String> listaModelosDaMarca;
    private ObservableList<String> listaFiliais;
    private boolean voltandoParaVeiculos;

    public Usuario getUsuario() {
        return usuario;
    }
    
    public Cliente getCliente() {
    	return cliente;
    }
    
    public Veiculo getVeiculo() {
    	return veiculo;
    }
    
    public FormatadorTexto getFormatador() {
    	return formatador;
    }
    
    public ObservableList<Veiculo> getListaVeiculos() {
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
    
    public void setUsuario(String user, String nomeNivel, int nivel, String nomeFilial) {
    	this.usuario = new Usuario(user,nomeNivel,nivel, nomeFilial);
    }
    
    public void setUsuario(Usuario usuario) {
    	this.usuario = usuario;
    }
    
    public void setCliente(String id, String nome, String cpf, String passaporte, LocalDate dataNascimento, String nascionalidade,
			String telefone, String cnh, LocalDate datacnh ) {
    	this.cliente = new Cliente(id,nome,cpf,passaporte,dataNascimento,nascionalidade,telefone,cnh,datacnh);
    }
    
    public void setCliente(Cliente cliente) {
    	this.cliente = cliente;
    }
    
    public void setVeiculo(int id, String placa, LocalDate dataManutencao, LocalDate dataCompra, int quilometragem, 
    						String marca, String modelo, String grupo, String filial, String estado) {
    	
    	this.veiculo = new Veiculo(id, placa, quilometragem, marca, modelo, filial, grupo, estado, dataCompra, dataManutencao);
    	
    	System.out.println("VEICULO DADOS:");
    	System.out.println("id: " + id);
    	System.out.println("placa: " + placa);
    	System.out.println("quilometragem: " + quilometragem);
    	System.out.println("marca: " + marca);
    	System.out.println("modelo: " + modelo);
    	System.out.println("filial: " + filial);
    	System.out.println("grupo: " + grupo);
    	System.out.println("estado: " + estado);
    	System.out.println("dataCompra: " + dataCompra);
    	System.out.println("dataManutencao: " + dataManutencao);
    }
    
    public void setVeiculo(Veiculo veiculo) {
    	this.veiculo = veiculo;
    }
    
    public void setListaVeiculos(ObservableList<Veiculo> listaVeiculos) {
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
}