package Testes;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import application.controller.NovoClienteController;
import application.controller.AtualizarClienteController;
import application.dao.ClienteDAO;
import application.entity.Cliente;

public class TesteCliente {

	NovoClienteController nvc = new NovoClienteController();
	AtualizarClienteController avc = new AtualizarClienteController();
	@Test
	public void testeCriarClienteFail(){
		String res = "";
		Cliente cl = new Cliente();
		cl.setNome("Daniel Jimenez");
		cl.setNacionalidade("Colombiano");
		cl.setTelefone("5281473");
		cl.setCnh("");
		cl.setPassaporte("18254888183");
		cl.setDataNasc(LocalDate.parse("1993-11-29"));
		cl.setDataCnh(LocalDate.parse("1993-11-29"));		
		res = nvc.criarCliente(cl,res);
		System.out.println(res);
		Assert.assertTrue(res.equals("Falta o CNH"));
	}
	
	@Test
	public void testeCriarClienteSucess(){
		String res = "";
		Cliente cl = new Cliente();
		cl.setNome("Daniel Jimenez");
		cl.setNacionalidade("Colombiano");
		cl.setTelefone("5281473");
		cl.setCnh("1");
		cl.setPassaporte("18254888183");
		cl.setDataNasc(LocalDate.parse("1993-11-29"));
		cl.setDataCnh(LocalDate.parse("1993-11-29"));		
		res = nvc.criarCliente(cl,res);
		System.out.println(res);
		if(res.isEmpty())
			(new ClienteDAO()).delete(cl);
		Assert.assertTrue(res.isEmpty());
	}
	
	@Test
	public void testeUpdateClienteFail(){
		String res = "";
		Cliente cl = new Cliente();
		cl.setNome("Daniel Jimenez");
		cl.setNacionalidade("Colombiano");
		cl.setTelefone("5281473");
		cl.setCnh("1");
		cl.setCpf("123123");
		cl.setDataNasc(LocalDate.parse("1993-11-29"));
		cl.setDataCnh(LocalDate.parse("1993-11-29"));		
		res = nvc.criarCliente(cl,res);
		cl.setCnh("");
		res = avc.atualizarCliente(cl,res);
		System.out.println(res);
		(new ClienteDAO()).delete(cl);
		Assert.assertTrue(res.equals("Falta o CNH"));
	}
	
	@Test
	public void testeUpdateClienteSucess(){
		String res = "";
		Cliente cl = new Cliente();
		cl.setNome("Daniel Jimenez");
		cl.setNacionalidade("Colombiano");
		cl.setTelefone("5281473");
		cl.setCnh("1");
		cl.setCpf("123123");
		cl.setDataNasc(LocalDate.parse("1993-11-29"));
		cl.setDataCnh(LocalDate.parse("1993-11-29"));		
		res = nvc.criarCliente(cl,res);
		cl.setCnh("2");
		res = avc.atualizarCliente(cl,res);		
		System.out.println(res);
		(new ClienteDAO()).delete(cl);
		Assert.assertTrue(res.isEmpty());
	}
}