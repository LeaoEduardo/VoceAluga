package Testes;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import application.Contexto;
import application.controller.ReservarVeiculoController;
import application.dao.ReservaDAO;
import application.entity.Reserva;

public class TesteLocacoes {

	ReservarVeiculoController nvc = new ReservarVeiculoController();
	@Test
	public void testeCriarReservaSucess(){
		String res = "";
		Reserva reserva = new Reserva();
		LocalDateTime data_hora_locacao = LocalDateTime.now();
		LocalDateTime data_hora_devolucao = LocalDateTime.now();
		reserva.setDataLocacao(data_hora_locacao);
		reserva.setIdCliente(1);
		reserva.setIdGrupo(1);
		reserva.setIdModelo(1);
		reserva.setDataDevolucao(data_hora_devolucao);
		ReservaDAO reserva_dao = new ReservaDAO();
		res = nvc.criarReserva(reserva,res);
		System.out.println(res);
		if(res.isEmpty()) {
			(new ReservaDAO()).delete((new ReservaDAO()).find("id_cliente", "1").get(0));
		}
		Assert.assertTrue(res.isEmpty());
	}
	
	@Test
	public void testeCriarReservaFail(){
		String res = "";
		Reserva reserva = new Reserva();
		LocalDateTime data_hora_locacao = LocalDateTime.now();
		LocalDateTime data_hora_devolucao = LocalDateTime.now();
		reserva.setDataLocacao(data_hora_locacao);
		reserva.setIdCliente(1);
		reserva.setIdModelo(1);
		reserva.setDataDevolucao(data_hora_devolucao);
		ReservaDAO reserva_dao = new ReservaDAO();
		res = nvc.criarReserva(reserva,res);
		System.out.println(res);
		if(res.isEmpty()) {
			(new ReservaDAO()).delete((new ReservaDAO()).find("id_cliente", "1").get(0));
		}
		Assert.assertTrue(res.equals("Falta o Grupo"));
	}
}