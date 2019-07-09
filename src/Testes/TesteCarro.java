package Testes;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import application.controller.CadastroVeiculoController;
import application.controller.CadastroModeloController;
import application.dao.ClienteDAO;
import application.entity.Carro;

public class TesteCarro {

	CadastroModeloController cm = new CadastroModeloController();
	CadastroVeiculoController cv = new CadastroVeiculoController();
	@Test
	public void testeCriarCarroFail(){
		String res = "";
		Carro carro = new Carro();
		carro.setPlaca("ABC123");
		carro.setIdModelo(1);
		carro.setDataCompra(LocalDate.parse("1993-11-29"));
		carro.setDataManutencao(LocalDate.parse("1993-11-29"));
		carro.setIdFilial(1);
		
		carro.setPassaporte("18254888183");
		carro.setDataNasc(LocalDate.parse("1993-11-29"));
		carro.setDataCnh(LocalDate.parse("1993-11-29"));		
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
}