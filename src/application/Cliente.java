package application;

import java.time.LocalDate;

public class Cliente {

	private String id;
	private String nome;
	private String passaporte;
	private String cpf;
	private String nacionalidade;
	private String telefone;
	private String cnh;
	private LocalDate dataNasc;
    private LocalDate validadeCNH;
	
	public Cliente(String id, String nome, String cpf, String passaporte, LocalDate dataNascimento, String nacionalidade,
			String telefone, String cnh, LocalDate datacnh ) {
		
		this.id = id;
		this.nome = nome;
		this.passaporte = passaporte;
		this.dataNasc = dataNascimento;
		this.cpf = cpf;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.cnh = cnh;
		this.validadeCNH = datacnh;
		
	}

	public String getIdCliente() {
		return id;
	}
	
	public String getNomeCliente() {
		return nome;
	}

	public String getPassaporte() {
		return passaporte;
	}
	
	public String getCPF() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNasc;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCnh() {
		return cnh;
	}

	public LocalDate getDatacnh() {
		return validadeCNH;
	}
}
