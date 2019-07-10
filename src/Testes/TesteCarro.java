package Testes;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import application.controller.CadastroVeiculoController;
import application.controller.CadastroModeloController;
import application.dao.CarroDAO;
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
		res = cv.criarVeiculo(carro,res);
		System.out.println(res);
		Assert.assertTrue(res.equals("Falta o Estado"));
	}
	
	@Test
	public void testeCriarCarroSucess(){
		String res = "";
		Carro carro = new Carro();
		carro.setPlaca("AAA1111");
		carro.setIdModelo(1);
		carro.setDataCompra(LocalDate.parse("1993-11-29"));
		carro.setDataManutencao(LocalDate.parse("1993-11-29"));
		carro.setIdFilial(1);		
		carro.setIdEstado(1);
		res = cv.criarVeiculo(carro,res);
		System.out.println(res);
		if(res.isEmpty())
			(new CarroDAO()).delete(carro);
		Assert.assertTrue(res.isEmpty());
	}
	
	@Test
	public void testeCriarCarroDuplicate(){
		String res = "";
		Carro carro = new Carro();
		carro.setPlaca("AAA1111");
		carro.setIdModelo(1);
		carro.setDataCompra(LocalDate.parse("1993-11-29"));
		carro.setDataManutencao(LocalDate.parse("1993-11-29"));
		carro.setIdFilial(1);		
		carro.setIdEstado(1);
		res = cv.criarVeiculo(carro,res);
		res = cv.criarVeiculo(carro,res);
		System.out.println(res);
		if(res.equals("Carro ja existe"))
			(new CarroDAO()).delete(carro);
		Assert.assertTrue(res.equals("Carro ja existe"));
	}
}