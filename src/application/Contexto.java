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
    private FormatadorTexto formatador = new FormatadorTexto();

    public Usuario getUsuario() {
        return usuario;
    }
    
    public Cliente getCliente() {
    	return cliente;
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
}