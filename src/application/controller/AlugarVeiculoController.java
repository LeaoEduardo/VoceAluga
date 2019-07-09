package application.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import application.Contexto;
import application.Main;
import application.entity.Carro;
import application.entity.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AlugarVeiculoController {
	
	private Main main = new Main();

    @FXML
    private DatePicker dp_dataDevolucao;
    
    @FXML
    private TextField tf_nomeCartao;
    
    @FXML
    private DatePicker dp_validadeCartao;
    
    @FXML
    private TextField tf_codSegCartao;
    
    @FXML
    private TextField tf_numCartao;

    @FXML
    private Label lb_modelo;

    @FXML
    private TextField tf_minutoLocacao;

    @FXML
    private DatePicker dp_dataLocacao;

    @FXML
    private Label lb_nomeCliente;

    @FXML
    private Label lb_marca;
    
    @FXML
    private Label lb_placa;

    @FXML
    private TextField tf_minutoDevolucao;

    @FXML
    private TextField tf_horaLocacao;

    @FXML
    private TextField tf_horaDevolucao;

    @FXML
    private Label lb_grupo;
    
    @FXML
    private Button botaoCancelar;

    @FXML
    void initialize() {
    	
    	String nomeCliente = Contexto.getInstancia().getCliente().getNome();
    	String placaCarro = Contexto.getInstancia().getVeiculo().getPlaca();
    	String marcaCarro = Contexto.getInstancia().getVeiculo().getModeloCarro().getMarca();
    	String modeloCarro = Contexto.getInstancia().getVeiculo().getModeloCarro().getModelo();
    	String grupoCarro = Contexto.getInstancia().getVeiculo().getModeloCarro().getGrupoCarro().getGrupo();
    	
    	LocalDate dataAtual = LocalDate.now();  
    	LocalTime horaAtual = LocalTime.now();
    	
    	lb_nomeCliente.setText(nomeCliente);
    	lb_placa.setText(placaCarro);
    	lb_marca.setText(marcaCarro);
    	lb_modelo.setText(modeloCarro);
    	lb_grupo.setText(grupoCarro);
    	dp_dataLocacao.setValue(dataAtual);
    	tf_horaLocacao.setText(Integer.toString(horaAtual.getHour()));
    	tf_minutoLocacao.setText(Integer.toString(horaAtual.getMinute()));
    }
    
    @FXML
    void confirmar(ActionEvent event) throws IOException {
    	
    	if ( dp_dataDevolucao.getValue() != null && dp_validadeCartao.getValue() != null && 
    		!tf_numCartao.getText().equals("") && !tf_nomeCartao.getText().equals("") && !tf_codSegCartao.getText().equals("") ) {
    	
	    	LocalDate dataLocacao = dp_dataLocacao.getValue();
			LocalDate dataDevolucao = dp_dataDevolucao.getValue();
			String horaLocacao = tf_horaLocacao.getText();
			String minutoLocacao = tf_minutoLocacao.getText();
			String horaDevolucao = tf_horaDevolucao.getText();
			String minutoDevolucao = tf_minutoDevolucao.getText();
			String numeroCartao = tf_numCartao.getText();
			String nomeCartao = tf_nomeCartao.getText();
			String codSegCartao = tf_codSegCartao.getText();
			LocalDate validadeCartao = dp_validadeCartao.getValue();
			
			Carro carroAlugado = Contexto.getInstancia().getVeiculo();
			Cliente cliente = Contexto.getInstancia().getCliente();
			
			if (horaLocacao.equals(""))
				horaLocacao = "0";
			if (minutoLocacao.equals(""))
				minutoLocacao = "0";
			if (horaDevolucao.equals(""))
				horaDevolucao = "0";
			if (minutoDevolucao.equals(""))
				minutoDevolucao = "0";
			
			LocalDateTime dataHoraLocacao = dataLocacao.atTime(Integer.valueOf(horaLocacao), 
					Integer.valueOf(minutoLocacao));
			LocalDateTime dataHoraDevolucao = dataDevolucao.atTime(Integer.valueOf(horaDevolucao), 
					Integer.valueOf(minutoDevolucao));
	    	
	    	boolean alugou = carroAlugado.confirmaLocacao(cliente, dataHoraLocacao , dataHoraDevolucao);
	    	
	    	if (alugou) {
	
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Aluguel efetuado");
				alert.setContentText("Veiculo alugado com sucesso.");
				alert.showAndWait();
				Stage stage = (Stage) botaoCancelar.getScene().getWindow();
				stage.close();
				Contexto.getInstancia().setVoltandoParaLocacao(true);
				main.showTelaPrincipal();
			}
	
			else {
	
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText("Erro no aluguel");
				alert.setContentText("Dados inconsistentes!");
				alert.showAndWait();
			}
    	}
    	
    	else {
    		
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Erro no aluguel");
			alert.setContentText("Todos os campos devem ser preenchidos!");
			alert.showAndWait();
    	}
    }
    
    @FXML
    void cancelar(ActionEvent event) {
    	
    	Stage stage = (Stage) botaoCancelar.getScene().getWindow();
		stage.close();
    }
}
