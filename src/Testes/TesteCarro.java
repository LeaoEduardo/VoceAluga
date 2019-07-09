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
		res = cv.criarVeiculo(carro,res);
		System.out.println(res);
		Assert.assertTrue(res.equals("Falta o CNH"));
	}
}