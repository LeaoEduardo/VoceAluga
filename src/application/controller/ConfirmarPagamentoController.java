package application.controller;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import application.Contexto;
import application.Main;
import application.dao.ClienteDAO;
import application.dao.LocacaoDAO;
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
    private Label lb_pontos;

    @FXML
    void initialize() {

    	ObservableList<String> escolhas = FXCollections.observableArrayList("Cartao cadastrado", "Novo cartao", "Dinheiro", "Pontos de fidelidade");
    	cb_metodoPagamento.setItems(escolhas);
    	cb_metodoPagamento.getSelectionModel().selectFirst();
    	ObservableList<String> escolhasEstado = FXCollections.observableArrayList("Excelente", "Bom", "Razoavel", "Ruim");
    	cb_estadoVeiculo.setItems(escolhasEstado);
    	cb_estadoVeiculo.getSelectionModel().selectFirst();
    	tf_numCartao.setDisable(true);
    	tf_nomeCartao.setDisable(true);
    	dp_validadeCartao.setDisable(true);
    	tf_codSegCartao.setDisable(true);
    	
    	int custo = calculaCusto();
    	System.out.println("calculo do custo = " + custo);
    	String custoLocacao = Integer.toString(custo);
    	lb_valorPagamento.setText("R$ " + custoLocacao);
    	
    	int pontos = calculaFidelidade();
    	System.out.println("pontos calculados: " + pontos);
    	lb_pontos.setText(Integer.toString(pontos));

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

    int calculaCusto() {
		//preco por dia * num dias
    	Locacao locacao = Contexto.getInstancia().getLocacao();
		int dias = (int) Duration.between(locacao.getDataInicial(), locacao.getDataFinal()).toDays();
		dias = dias + 1;
		System.out.println("dias alugado: " + dias );
		double total = dias * locacao.getCarro().getModeloCarro().getGrupoCarro().getPreco().getValor();

		return (int) Math.round(total);
	}

    int calculaFidelidade() {
    	int pontos = 0;
    	int nLocacoesValidas = 0;
    	//TODO: verificar se possui + de 3 locacoes no ultimo ano
    	//TODO: verificar se a locacao esta na validade de 1 ano
    	int id_cliente = Contexto.getInstancia().getLocacao().getIdCliente();
    	System.out.println("Cliente no calculo de fidelidade: " + (new ClienteDAO()).find(id_cliente).getNome());
    	ArrayList<Locacao> locacoes = (new LocacaoDAO()).find("id_cliente", Integer.toString(id_cliente));
    	
    	for(Locacao loc : locacoes) {
    		if(loc.getDevolvido()) {
	    		int dias = (int) Duration.between(loc.getDataInicial(), loc.getDataFinal()).toDays();
	    		dias = dias + 1;
	    		if(dias < 365) {
	    			nLocacoesValidas += 1;
		    		if(loc.getFidelidade()) {
		    			pontos = pontos - (loc.getCusto() / 10);
		    		}
		    		else {
		    			pontos = pontos + (loc.getCusto() / 10);
		    		}
	    		}
    		}
    	}
    	
    	if(nLocacoesValidas < 3) pontos = 0;
    	    	
    	System.out.println("Pontos do Cliente: " + pontos);
    	
    	return pontos;
    }

    @FXML
    void confirmar(ActionEvent event) throws IOException {

    	String estadoVeiculo = cb_estadoVeiculo.getSelectionModel().toString();
    	int custosAdicionais = 0;
    	if (!tf_custosAdicionais.getText().equals(""))
    		custosAdicionais = Integer.parseInt(tf_custosAdicionais.getText());
    	
    	Locacao loc = Contexto.getInstancia().getLocacao();

    	if ( cb_metodoPagamento.getSelectionModel().getSelectedIndex() == 1 ) {

    		if ( !tf_numCartao.getText().equals("") && !tf_nomeCartao.getText().equals("") && dp_validadeCartao.getValue() != null &&
    				!tf_codSegCartao.getText().equals("") && !cb_estadoVeiculo.getValue().equals("") ) {

    			if( loc.confirmaDevolucao( Contexto.getInstancia().getFuncionario().getFilial() , 
    					4-cb_estadoVeiculo.getSelectionModel().getSelectedIndex(),
    					calculaCusto() + custosAdicionais,false) ) {

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

			if( loc.confirmaDevolucao( Contexto.getInstancia().getFuncionario().getFilial() , 
					4-cb_estadoVeiculo.getSelectionModel().getSelectedIndex(),
					calculaCusto() + custosAdicionais,false) ) {

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