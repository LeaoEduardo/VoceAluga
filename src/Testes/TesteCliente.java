package Testes;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import application.controller.NovoClienteController;
import application.dao.ClienteDAO;
import application.entity.Cliente;

public class TesteCliente {

	NovoClienteController nv = new NovoClienteController();
	@Test
	public void testeCriarCliente(){
		String res = "";
		Cliente cl = new Cliente();
		cl.setNome("Daniel Jimenez");
		cl.setNacionalidade("Colombiano");
		cl.setTelefone("5281473");
		cl.setCnh("1");
		cl.setPassaporte("18254888183");
		cl.setDataNasc(LocalDate.parse("1993-11-29"));
		cl.setDataCnh(LocalDate.parse("1993-11-29"));		
		res = nv.criarCliente(cl,res);
		System.out.println(res);
		if(res.isEmpty())
			(new ClienteDAO()).delete(cl);
		Assert.assertTrue(res.isEmpty());
	}
	
	@Test
	public void testeUpdateCliente(){
		String res = "";
		Cliente cl = new Cliente();
		cl.setNome("Daniel Jimenez");
		cl.setNacionalidade("Colombiano");
		cl.setTelefone("5281473");
		cl.setCnh("1");
		cl.setPassaporte("18254888183");
		cl.setDataNasc(LocalDate.parse("1993-11-29"));
		cl.setDataCnh(LocalDate.parse("1993-11-29"));		
		res = nv.criarCliente(cl,res);
		System.out.println(res);
		if(res.isEmpty())
			(new ClienteDAO()).delete(cl);
		Assert.assertTrue(res.isEmpty());
	}
}