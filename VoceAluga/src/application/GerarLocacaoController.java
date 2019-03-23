package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GerarLocacaoController {
	
	private Main main = new Main();

    @FXML
    private TextField tf_cpf;

    @FXML
    void pesquisarCliente(ActionEvent event) throws IOException {
    	
    	String cpf = tf_cpf.getText();
    	
    	// Aqui deve ser checado se o CPF esta registrado no BD
    	
    	// se estiver la
    	if (cpf.equals("123.456.789-00")) {
    		
    		Contexto.getInstancia().setCpfCliente(cpf);
    		main.showTelaCliente();
    	}
    	
    	//se nao estiver
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erro");
    		alert.setHeaderText("Cliente não encontrado.");
    		alert.setContentText("Para cadastrá-lo, clique em \"Novo cliente\".");
    		alert.showAndWait();
    		tf_cpf.setText("");
    	}
    }
    
    @FXML
    void novoCliente(ActionEvent event) throws IOException {
    	
    	// Abre a janela de cadastro de novo cliente 
    	Stage stage = new Stage();
    	AnchorPane novoCliente = FXMLLoader.load(getClass().getResource("NovoCliente.fxml"));
    	Scene scene = new Scene(novoCliente);
    	
    	stage.setTitle("Cadastro de cliente");
    	stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void voltar(ActionEvent event) throws IOException {
    	
    	// Carrega a tela principal
    	main.showTelaPrincipal();
    }
}
