package application.controller;

import java.io.IOException;

import application.Contexto;
import application.Main;
import application.entity.Locacao;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ConfirmarPagamentoController {
	
	private Main main = new Main();

    @FXML
    private TextField tf_nomeCartao;

    @FXML
    private Button botaoCancelar;

    @FXML
    private ChoiceBox<String> cb_metodoPagamento;

    @FXML
    private TextField tf_codSegCartao;

    @FXML
    private DatePicker dp_validadeCartao;

    @FXML
    private TextField tf_numCartao;
    
    @FXML
    private Label lb_valorPagamento;

    @FXML
    void initialize() {
    	
    	ObservableList<String> escolhas = FXCollections.observableArrayList("Cartao cadastrado", "Novo cartao", "Dinheiro");
    	cb_metodoPagamento.setItems(escolhas);
    	cb_metodoPagamento.getSelectionModel().selectFirst();
    	tf_numCartao.setDisable(true);
    	tf_nomeCartao.setDisable(true);
    	dp_validadeCartao.setDisable(true);
    	tf_codSegCartao.setDisable(true);
    	
    	ReadOnlyIntegerProperty selectProperty = cb_metodoPagamento.getSelectionModel().selectedIndexProperty();
		selectProperty.addListener((value, oldValue, newValue) -> { 
			if (newValue.intValue() == 1) {
				tf_numCartao.setDisable(false);
		    	tf_nomeCartao.setDisable(false);
		    	dp_validadeCartao.setDisable(false);
		    	tf_codSegCartao.setDisable(false);
			}
	    	else {
	    		tf_numCartao.setDisable(true);
		    	tf_nomeCartao.setDisable(true);
		    	dp_validadeCartao.setDisable(true);
		    	tf_codSegCartao.setDisable(true);
	    	}
		});
    }
    
    @FXML
    void confirmar(ActionEvent event) throws IOException {
    	
    	if ( cb_metodoPagamento.getSelectionModel().getSelectedIndex() == 1 ) {
    		
    		if ( !tf_numCartao.getText().equals("") && !tf_nomeCartao.getText().equals("") && dp_validadeCartao.getValue() != null && 
    				!tf_codSegCartao.getText().equals("") ) {
    			
    			Locacao loc = Contexto.getInstancia().getLocacao();
    			
    			if( loc.confirmaDevolucao( Contexto.getInstancia().getFuncionario().getFilial() ) ) {
    				
    				Alert alert = new Alert(AlertType.INFORMATION);
        			alert.setHeaderText("Pagamento efetuado");
        			alert.setContentText("Aluguel pago com sucesso.");
        			alert.showAndWait();
        			Stage stage = (Stage) botaoCancelar.getScene().getWindow();
        			Contexto.getInstancia().setVoltandoParaDevolucao(true);
        			stage.close();
    		    	main.showTelaPrincipal();
    	    		System.out.println("Devolucao confirmada");
    	    	}
    			
    			else {
    				
    				System.out.println("Devolucao nao confirmada");
    				Stage stage = (Stage) botaoCancelar.getScene().getWindow();
    				stage.close();
    			}
    		}
    		
    		else {
    			
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Erro");
    			alert.setHeaderText("Erro no pagamento");
    			alert.setContentText("Todos os campos devem ser preenchidos!");
    			alert.showAndWait();
    		}
    	}
    	
    	else {
    	
    		Locacao loc = Contexto.getInstancia().getLocacao();
			
			if( loc.confirmaDevolucao( Contexto.getInstancia().getFuncionario().getFilial() ) ) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("Pagamento efetuado");
    			alert.setContentText("Aluguel pago com sucesso.");
    			alert.showAndWait();
    			Stage stage = (Stage) botaoCancelar.getScene().getWindow();
    			System.out.println("Devolucao confirmada");
				Contexto.getInstancia().setVoltandoParaDevolucao(true);
				stage.close();
		    	main.showTelaPrincipal();
	    	}
			
			else {
				
				System.out.println("Devolucao nao confirmada");
				Stage stage = (Stage) botaoCancelar.getScene().getWindow();
				stage.close();
			}
    	}
    }
    
    @FXML
    void cancelar(ActionEvent event) {
    	
    	Stage stage = (Stage) botaoCancelar.getScene().getWindow();
		stage.close();
    }
}