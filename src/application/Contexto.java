package application;

public class Contexto {
	
	// Essa classe eh usada para fazer a integracao de dados entre os diferentes controllers (classes)
	
    private final static Contexto instancia = new Contexto();

    public static Contexto getInstancia() {
        return instancia;
    }
    
    private Usuario usuario;
    private String cpfCliente = new String();
    private String passaporteCliente = new String();
    private String filial = new String();

    public Usuario getUsuario() {
        return usuario;
    }
    
    public String getCpfCliente() {
    	return cpfCliente;
    }
    
    public String getPassaporteCliente() {
    	return passaporteCliente;
    }
    
    public String getFilial() {
    	return filial;
    }
    
    public void setUsuario(String user, String nomeNivel, int nivel) {
    	this.usuario = new Usuario(user,nomeNivel,nivel);
    }
    
    public void setUsuario(Usuario usuario) {
    	this.usuario = usuario;
    }
    
    public void setCpfCliente(String cpfCliente) {
    	this.cpfCliente = cpfCliente;
    }
    
    public void setPassaporteCliente(String passaporteCliente) {
    	this.passaporteCliente = passaporteCliente;
    }
    
    public void setFilial(String filial) {
    	this.filial = filial;
    }
}