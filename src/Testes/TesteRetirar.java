package Testes;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import application.Contexto;
import application.controller.AlugarVeiculoController;
import application.controller.CadastroVeiculoController;
import application.controller.NovoClienteController;
import application.dao.LocacaoDAO;
import application.dao.ClienteDAO;
import application.dao.CarroDAO;
import application.entity.Carro;
import application.entity.Cliente;
import application.entity.Reserva;

public class TesteRetirar {

	AlugarVeiculoController nvc = new AlugarVeiculoController();
	NovoClienteController nvc2 = new NovoClienteController();
	CadastroVeiculoController cv = new CadastroVeiculoController();
	
	@Test
	public void testeRetirarVeiculo(){
		String res = "";
		Reserva reserva = new Reserva();
		
		Carro carro = new Carro();
		carro.setPlaca("AAA1111");
		carro.setIdModelo(1);
		carro.setDataCompra(LocalDate.parse("1993-11-29"));
		carro.setDataManutencao(LocalDate.parse("1993-11-29"));
		carro.setIdFilial(1);		
		carro.setIdEstado(3);
		res = cv.criarVeiculo(carro,res);
		System.out.println(res);
		
		Cliente cl = new Cliente();
		cl.setNome("Daniel Jimenez");
		cl.setNacionalidade("Colombiano");
		cl.setTelefone("5281473");
		cl.setCnh("1");
		cl.setPassaporte("18254888183");
		cl.setDataNasc(LocalDate.parse("1993-11-29"));
		cl.setDataCnh(LocalDate.parse("1993-11-29"));		
		res = nvc2.criarCliente(cl,res);
		System.out.println(res);
		
		Cliente cliente = (new ClienteDAO()).find("passaporte", cl.getPassaporte()).get(0);
		Carro carroAlugado = (new CarroDAO()).find("placa", carro.getPlaca()).get(0);
		
		LocalDateTime data_hora_locacao = LocalDateTime.now();
		LocalDateTime data_hora_devolucao = LocalDateTime.now();
		boolean alugou = carroAlugado.confirmaLocacao(cliente, data_hora_locacao , data_hora_devolucao);
		if(alugou) {
			(new LocacaoDAO()).delete((new LocacaoDAO()).find("id_cliente", Integer.toString(cliente.getId())).get(0));
			(new ClienteDAO()).delete(cl);
			(new CarroDAO()).delete(carro);
		}
		Assert.assertTrue(alugou);
	}	
}