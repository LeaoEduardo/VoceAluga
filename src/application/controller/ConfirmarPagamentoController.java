package application.controller;

import java.io.IOException;
import java.time.Duration;

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
    private ChoiceBox<String> cb_estadoVeiculo;

    @FXML
    private TextField tf_custosAdicionais;

    @FXML
    private TextField tf_codSegCartao;

    @FXML
    private DatePicker dp_validadeCartao;

    @FXML
    private TextField tf_numCartao;

    @FXML
    private Label lb_valorPagamento;

    @FXML
    private Label lb_Pontos;

    @FXML
    void initialize() {

    	ObservableList<String> escolhas = FXCollections.observableArrayList("Cartao cadastrado", "Novo cartao", "Dinheiro", "Pontos de fidelidade");
    	cb_metodoPagamento.setItems(escolhas);
    	cb_metodoPagamento.getSelectionModel().selectFirst();
    	ObservableList<String> escolhasEstado = FXCollections.observableArrayList("Excelente", "Bom", "Razoável", "Ruim");
    	cb_estadoVeiculo.setItems(escolhasEstado);
    	tf_numCartao.setDisable(true);
    	tf_nomeCartao.setDisable(true);
    	dp_validadeCartao.setDisable(true);
    	tf_codSegCartao.setDisable(true);

    	Double custo = calculaCusto();
    	System.out.println("calculo do custo = " + custo);
    	//String custoLocacao = custo.toString().format("%.2f");
    	String custoLocacao = String.format("%.2f", custo);
    	lb_valorPagamento.setText("R$ " + custoLocacao);

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

    double calculaCusto() {
		//preço por dia * num dias
    	Locacao locacao = Contexto.getInstancia().getLocacao();
		//int dias = locacao.getDataFinal() - locacao.getDataInicial();
		int dias = (int) Duration.between(locacao.getDataInicial(), locacao.getDataFinal()).toDays();
		dias = dias + 1;
		System.out.println("dias alugado: " + dias );
		double total = dias * locacao.getCarro().getModeloCarro().getGrupoCarro().getPreco().getValor();

		return total;
	}


    @FXML
    void confirmar(ActionEvent event) throws IOException {

    	String estadoVeiculo = cb_estadoVeiculo.getSelectionModel().toString();
    	int custosAdicionais = Integer.parseInt(tf_custosAdicionais.getText());

    	Locacao loc = Contexto.getInstancia().getLocacao();

    	if ( cb_metodoPagamento.getSelectionModel().getSelectedIndex() == 1 ) {

    		if ( !tf_numCartao.getText().equals("") && !tf_nomeCartao.getText().equals("") && dp_validadeCartao.getValue() != null &&
    				!tf_codSegCartao.getText().equals("") && !cb_estadoVeiculo.getValue().equals("") ) {

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

    	else if ( !cb_estadoVeiculo.getValue().equals("") ) {

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

    	else {

    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Erro no pagamento");
			alert.setContentText("Estado de devolucao nao preenchido!");
			alert.showAndWait();
    	}
    }

    @FXML
    void cancelar(ActionEvent event) {

    	Stage stage = (Stage) botaoCancelar.getScene().getWindow();
		stage.close();
    }
}