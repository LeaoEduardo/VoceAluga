package application;

import java.io.IOException;
import java.time.LocalDate;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CadastroVeiculoController {
	
	private Main main = new Main(); 

    @FXML
    private Button botaoCancelar;

    @FXML
    private TextField tf_quilometragem;
    
    @FXML
    private ChoiceBox<String> cb_modelo;

    @FXML
    private ChoiceBox<String> cb_marca;

    @FXML
    private Button botaoCadastrarVeiculo;

    @FXML
    private DatePicker dp_dataManutencao;

    @FXML
    private DatePicker dp_dataCompra;

    @FXML
    private TextField tf_placa;

    @FXML
    private ChoiceBox<String> cb_filial;
    
    @FXML
    void initialize() {
    	
    	ConnectionSQL con = new ConnectionSQL();
    	
    	boolean consultou = con.ConsultaFiliais();
    	
    	if (consultou) {
    		
    		ObservableList<String> listaFiliais = Contexto.getInstancia().getListaFiliais();
    		cb_filial.setItems(listaFiliais);
    	}
    	
    	else {
    		System.out.println("Erro na consulta de filiais!");
    	}

    	consultou = con.ConsultaMarcas();
    	
    	if (consultou) {
    		
    		ObservableList<String> listaMarcas = Contexto.getInstancia().getListaMarcas();
    		cb_marca.setItems(listaMarcas);
    		ReadOnlyObjectProperty<String> property = cb_marca.getSelectionModel().selectedItemProperty();
    		property.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> atualizaModelos(newValue));
    	}
    	
    	else {
    		System.out.println("Erro na consulta de marca!");
    	}
    	
    	tf_quilometragem.setTextFormatter(Contexto.getInstancia().getFormatador().Quilometragem());
    }
    
    void atualizaModelos(String marca) {
    	
    	ConnectionSQL con = new ConnectionSQL();
    	
    	boolean consultou = con.ConsultaModelosDaMarca(marca);
    	
    	if (consultou) {
    		
    		ObservableList<String> listaModelosDaMarca = Contexto.getInstancia().getListaModelosDaMarca();
    		cb_modelo.setItems(listaModelosDaMarca);
    	}
    	
    	else {
    		System.out.println("Erro na consulta de modelos!");
    	}
    }
    
    @FXML
    void cadastrarVeiculo(ActionEvent event) throws IOException {
    	
    	ConnectionSQL con = new ConnectionSQL();
    	
    	String placa = tf_placa.getText();
    	Integer quilometragem = Integer.parseInt(tf_quilometragem.getText());
    	String filial = cb_filial.getSelectionModel().getSelectedItem();
    	String estado = "Disponível";
    	
    	if (placa.equals("") || cb_marca.getSelectionModel().isEmpty() || cb_modelo.getSelectionModel().isEmpty() || dp_dataCompra.getValue() == null || 
    			dp_dataManutencao.getValue() == null || cb_filial.getSelectionModel().isEmpty()) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Erro");
        	alert.setHeaderText("Erro no cadastro.");
        	alert.setContentText("Todos os campos devem ser preenchidos!");
        	alert.showAndWait();
    	}
    	
    	else {
    		
    		String marca = cb_marca.getSelectionModel().getSelectedItem();
        	String modelo = cb_modelo.getSelectionModel().getSelectedItem();
    		LocalDate dataCompra = dp_dataCompra.getValue();
        	LocalDate dataManutencao = dp_dataManutencao.getValue();
        	
        	boolean cadastrou = con.CadastraVeiculo(placa, quilometragem, marca, modelo, filial, estado, dataCompra, dataManutencao);
        	
        	if (cadastrou) {
        		
        		Alert alert = new Alert(AlertType.INFORMATION);
    	        alert.setHeaderText("Cadastro efetuado");
    	        alert.setContentText("Veículo cadastrado com sucesso.");
    	        alert.showAndWait();
    	        Stage stage = (Stage) botaoCadastrarVeiculo.getScene().getWindow();
    	        stage.close();
    	        Contexto.getInstancia().setVoltandoParaVeiculos(true);
    	        main.showTelaPrincipal(); 
        	}
        	
        	else {
        		
        		Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("Erro");
            	alert.setHeaderText("Erro no cadastro.");
            	alert.setContentText("Dados inconsistentes!");
            	alert.showAndWait();
        	}
    	}
    }

    @FXML
    void cancelar(ActionEvent event) {
    	
    	Stage stage = (Stage) botaoCancelar.getScene().getWindow();
        stage.close();
    }
}