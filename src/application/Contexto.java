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
    private String cpfCliente = new String();
    private String passaporteCliente = new String();
    /*private String filial = new String(); //Filial esta em usuario */

    public Usuario getUsuario() {
        return usuario;
    }
    
    public Cliente getCliente() {
    	return cliente;
    }
    
    public String getCpfCliente() {
    	return cpfCliente;
    }
    
    public String getPassaporteCliente() {
    	return passaporteCliente;
    }
    
    /*public String getFilial() {
    	return filial;
    }*/
    
    public void setUsuario(String user, String nomeNivel, int nivel, String nomeFilial) {
    	this.usuario = new Usuario(user,nomeNivel,nivel, nomeFilial);
    }
    
    public void setCliente(String id, String nome, String cpf, String passaporte, LocalDate dataNascimento, String nascionalidade,
			String telefone, String cnh, LocalDate datacnh ) {
    	//dataNascimento.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    	this.cliente = new Cliente(id,nome,cpf,passaporte,dataNascimento,nascionalidade,telefone,cnh,datacnh);
    }
    
    public void setUsuario(Usuario usuario) {
    	this.usuario = usuario;
    }
    
    public void setCliente(Cliente cliente) {
    	this.cliente = cliente;
    }
    
    public void setCpfCliente(String cpfCliente) {
    	this.cpfCliente = cpfCliente;
    }
    
    public void setPassaporteCliente(String passaporteCliente) {
    	this.passaporteCliente = passaporteCliente;
    }
    
    /*public void setFilial(String filial) {
    	this.filial = filial;
    }*/
}