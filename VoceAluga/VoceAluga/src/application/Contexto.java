package application;

public class Contexto {
	
	// Essa classe eh usada para fazer a integracao de dados entre os diferentes controllers (classes)
	// No caso, ela guarda o usuario e o nivel do funcionario logado no momento
	// Guarda tambem o cpf inserido na pesquisa de cliente
	
    private final static Contexto instancia = new Contexto();

    public static Contexto getInstancia() {
        return instancia;
    }

    private String usuario = new String();
    private String nivel = new String();
    private String cpfCliente = new String();
    private String filial = new String();

    public String getUsuario() {
        return usuario;
    }
    
    public String getNivel() {
        return nivel;
    }
    
    public String getCpfCliente() {
    	return cpfCliente;
    }
    
    public void setUsuario(String usuario) {
    	this.usuario = usuario;
    }
    
    public void setNivel(String nivel) {
    	this.nivel = nivel;
    }
    
    public void setCpfCliente(String cpfCliente) {
    	this.cpfCliente = cpfCliente;
    }
    
    public void setFilial(String filial) {
    	this.filial = filial;
    }
}