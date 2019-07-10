package application.controller;

import java.io.IOException;
import java.time.LocalDate;
import application.Contexto;
import application.Main;
import application.dao.CarroDAO;
import application.dao.ManutencaoDAO;
import application.entity.Carro;
import application.entity.Manutencao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TelaManutencaoController {
	
	private Main main = new Main();

    @FXML
    private Label lb_placa;

    @FXML
    private DatePicker dp_ultimaManutencao;

    @FXML
    private Label lb_modelo;

    @FXML
    private Button botaoCancelar;

    @FXML
    private Label lb_marca;

    @FXML
    private DatePicker dp_dataInicio;

    @FXML
    private TextField tf_orcamento;

    @FXML
    private Label lb_quilometragem;

    @FXML
    private Label lb_grupo;

    @FXML
    private DatePicker dp_dataTermino;
    
    Carro carro;

    @FXML
    void initialize() {
        
    	carro = Contexto.getInstancia().getVeiculo();
    	String placaCarro = carro.getPlaca();
    	String marcaCarro = carro.getModeloCarro().getMarca();
    	String modeloCarro = carro.getModeloCarro().getModelo();
    	String grupoCarro = carro.getModeloCarro().getGrupoCarro().getGrupo();
    	String quilometragem = Integer.toString(carro.getQuilometragem());
    	LocalDate ultimaManutencao = carro.getDataManutencao();
    	LocalDate dataAtual = LocalDate.now();
    	
    	lb_placa.setText(placaCarro);
    	lb_marca.setText(marcaCarro);
    	lb_modelo.setText(modeloCarro);
    	lb_grupo.setText(grupoCarro);
    	lb_quilometragem.setText(quilometragem);
    	dp_ultimaManutencao.setValue(ultimaManutencao);
    	dp_dataInicio.setValue(dataAtual);
    }
    
    @FXML
    void confirmar(ActionEvent event) throws IOException {
    	
    	if ( dp_dataTermino.getValue() != null && !tf_orcamento.getText().equals("") ) {
    		
    		int orcamento = Integer.parseInt(tf_orcamento.getText());
    		LocalDate dataInicio = dp_dataInicio.getValue();
    		LocalDate dataTermino = dp_dataTermino.getValue();
    		
    		
    		Manutencao manutencao = new Manutencao(carro.getId(), dataInicio, dataTermino, orcamento);
    		
    		boolean enviou = (new ManutencaoDAO()).insert(manutencao);
    		
    		if (enviou) {
    			
    			carro.setIdEstado(2);
    			boolean atualizouCarro = (new CarroDAO()).update(carro);
    			
    			if (atualizouCarro) {
    			
	    			Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Enviado com sucesso");
					alert.setContentText("Veiculo enviado para a oficina.");
					alert.showAndWait();
					Stage stage = (Stage) botaoCancelar.getScene().getWindow();
					stage.close();
					Contexto.getInstancia().setVoltandoParaVeiculos(true);
					main.showTelaPrincipal();
    			}
    			
    			else {
    				
    				Alert alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Erro");
        			alert.setHeaderText("Erro no envio para a oficina");
        			alert.setContentText("Falha na atualização do carro!");
        			alert.showAndWait();
    			}
    		}
    		
    		else {
    			
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Erro");
    			alert.setHeaderText("Erro no envio para a oficina");
    			alert.setContentText("Dados inconsistentes!");
    			alert.showAndWait();
    		}
    	}
    	
    	else {
    	
	    	Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Erro no envio para a oficina");
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