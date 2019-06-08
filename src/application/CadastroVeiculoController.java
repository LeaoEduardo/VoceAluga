package application;

import java.io.IOException;
import java.util.ArrayList;

import application.dao.CarroDAO;
import application.dao.EstadoCarroDAO;
import application.dao.FilialDAO;
import application.dao.ModeloCarroDAO;
import application.entity.Carro;
import application.entity.EstadoCarro;
import application.entity.Filial;
import application.entity.ModeloCarro;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    	ArrayList<ModeloCarro> todos_modelos = (new ModeloCarroDAO()).findAll();
    	for( ModeloCarro modelo_carro : todos_modelos ) {
    		if(modelo_carro.getMarca().equals(marca)) {
    			nome_modelos.add(modelo_carro.getModelo());
    		}
    	}
		cb_modelo.setItems(nome_modelos);
    }
    
    @FXML
    void cadastrarVeiculo(ActionEvent event) throws IOException {
    	
    	CarroDAO 	carro_dao 	= new CarroDAO();
    	String 		nome_filial = cb_filial.getSelectionModel().getSelectedItem();
    	String 		nome_estado = "Disponível";
    	String 		nome_modelo = cb_modelo.getSelectionModel().getSelectedItem();

    	
    	if ( tf_placa.getText().equals("") || cb_marca.getSelectionModel().isEmpty() || cb_modelo.getSelectionModel().isEmpty() 
    			|| dp_dataCompra.getValue() == null || dp_dataManutencao.getValue() == null 
    			|| cb_filial.getSelectionModel().isEmpty()) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Erro");
        	alert.setHeaderText("Erro no cadastro.");
        	alert.setContentText("Todos os campos devem ser preenchidos!");
        	alert.showAndWait();
    	}
    	
    	else {
    		Filial 		filial = (new FilialDAO()).find("nomeFilial", nome_filial).get(0);
    		EstadoCarro estado_carro = (new EstadoCarroDAO()).find("tipo",nome_estado).get(0);
    		ModeloCarro modelo_carro = (new ModeloCarroDAO()).find("modelo",nome_modelo).get(0);

        	Carro carro = new Carro();
        	carro.setQuilometragem( Integer.parseInt( tf_quilometragem.getText() ) );
        	carro.setIdFilial( filial.getId() );
        	carro.setPlaca(tf_placa.getText());
        	carro.setIdEstado( estado_carro.getId() );
    		carro.setDataCompra( dp_dataCompra.getValue() );
    		carro.setDataManutencao( dp_dataManutencao.getValue() );
        	carro.setIdModelo( modelo_carro.getId() );
    		
        	boolean cadastrou = carro_dao.insert( carro );
        	
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