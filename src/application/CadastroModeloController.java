package application;

import application.dao.GrupoCarroDAO;
import application.dao.ModeloCarroDAO;
import application.entity.ModeloCarro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CadastroModeloController {

    @FXML
    private TextField tf_marca;

    @FXML
    private Button botaoCancelar;

    @FXML
    private TextField tf_modelo;

    @FXML
    private Button botaoCadastrarModelo;

    @FXML
    private ChoiceBox<String> cb_grupo;

    @FXML
    void initialize() {
        
    	ObservableList<String> grupos = FXCollections.observableArrayList("A", "B", "C");
    	cb_grupo.setItems(grupos);
    	cb_grupo.getSelectionModel().selectFirst();
    }
    
    @FXML
    void cadastrarModelo(ActionEvent event) {
    	
    	
    	String marca = tf_marca.getText();
    	String modelo = tf_modelo.getText();
    	String grupo = cb_grupo.getSelectionModel().getSelectedItem();
    	
    	if (marca.equals("") || modelo.equals("") ) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Erro");
        	alert.setHeaderText("Erro no cadastro.");
        	alert.setContentText("Todos os campos devem ser preenchidos!");
        	alert.showAndWait();
    	}
    	
    	else {
    		
    		ModeloCarro modelo_carro = new ModeloCarro();
    		modelo_carro.setIdGrupo( (new GrupoCarroDAO()).find("grupo", grupo).get(0).getId()  );
    		modelo_carro.setModelo(modelo);
    		modelo_carro.setMarca(marca);
    		boolean cadastrou = (new ModeloCarroDAO()).insert(modelo_carro);
    		
    		if (cadastrou) {
    			
    			// Prints de teste
    			System.out.println("marca: " + marca);
    			System.out.println("modelo: " + modelo);
    			System.out.println("grupo: " + grupo);
    			
    			Alert alert = new Alert(AlertType.INFORMATION);
    	        alert.setHeaderText("Cadastro efetuado");
    	        alert.setContentText("Modelo cadastrado com sucesso.");
    	        alert.showAndWait();
    	        Stage stage = (Stage) botaoCadastrarModelo.getScene().getWindow();
    	        stage.close();		
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