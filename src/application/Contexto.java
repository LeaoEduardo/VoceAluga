package application;

import java.time.LocalDate;

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
    						String marca, String modelo, String filial,	String estado) {
    	this.veiculo = new Veiculo(id, placa, quilometragem, marca, modelo, filial, estado, dataCompra, dataManutencao);
    	System.out.println("VEICULO DADOS:");
    	System.out.println(id);
    	System.out.println(placa);
    	System.out.println(quilometragem);
    	System.out.println(marca);
    	System.out.println(modelo);
    	System.out.println(filial);
    	System.out.println(estado);
    	System.out.println(dataCompra);
    	System.out.println(dataManutencao);
    }
    
    public void setVeiculo(Veiculo veiculo) {
    	this.veiculo = veiculo;
    }
}