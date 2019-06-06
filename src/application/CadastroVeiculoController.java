package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import application.dao.ModeloCarroDAO;
import application.entity.Filial;
import application.entity.ModeloCarro;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
    	
    	cb_filial.setItems( Filial.getAllFilialNomes() );
    	cb_marca.setItems( ModeloCarro.getAllMarcas() );
		
		ReadOnlyObjectProperty<String> property = cb_marca.getSelectionModel().selectedItemProperty();
		property.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> atualizaModelos(newValue));
	
    	
    	tf_quilometragem.setTextFormatter(Contexto.getInstancia().getFormatador().Quilometragem());
    }
    
    void atualizaModelos(String marca) {

    	ObservableList<String> nome_modelos = FXCollections.observableArrayList();
    	ArrayList<ModeloCarro> todos_modelos = ModeloCarroDAO.findAll();
    	for( ModeloCarro modelo_carro : todos_modelos ) {
    		if(modelo_carro.getMarca().equals(marca)) {
    			nome_modelos.add(modelo_carro.getModelo());
    		}
    	}
		cb_modelo.setItems(nome_modelos);
    }
    
    @FXML
    void cadastrarVeiculo(ActionEvent event) throws IOException {
    	
    	ConnectionSQL con = new ConnectionSQL();
    	
    	String placa = tf_placa.getText();
    	Integer quilometragem = 0;
    	String filial = cb_filial.getSelectionModel().getSelectedItem();
    	String estado = "Disponível";
    	
    	if (placa.equals("") || tf_quilometragem.getText().isEmpty() || cb_marca.getSelectionModel().isEmpty() || cb_modelo.getSelectionModel().isEmpty() || dp_dataCompra.getValue() == null || 
    			dp_dataManutencao.getValue() == null || cb_filial.getSelectionModel().isEmpty()) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Erro");
        	alert.setHeaderText("Erro no cadastro.");
        	alert.setContentText("Todos os campos devem ser preenchidos!");
        	alert.showAndWait();
    	}
    	
    	else {
    		quilometragem = Integer.parseInt(tf_quilometragem.getText());
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