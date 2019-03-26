package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaPrincipalController {
	
	Main main = new Main();
	
	@FXML
    private Label tf_username;

    @FXML
    private Label tf_nivel;
    
    @FXML
    private Label tf_filial;
    
    @FXML
    private TextField tf_cpf;
    
    @FXML
    private Tab abaCarro;
    
    @FXML
    private Tab abaFuncionarios;
    
    @FXML
    void initialize() {
       
    	String usuario = Contexto.getInstancia().getUsuario();
    	String nivel = Contexto.getInstancia().getNivel();
    	String filial = Contexto.getInstancia().getFilial();
    	tf_username.setText(usuario);
    	tf_nivel.setText(nivel);
		tf_filial.setText(filial);
    	
    	if(nivel.equals("Funcionario")) {
        	abaCarro.setDisable(true);
        	abaFuncionarios.setDisable(true);
        }
    }
    
    
    
    
    
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
    		alert.setHeaderText("Cliente nao encontrado.");
    		alert.setContentText("Para cadastra-lo, clique em \"Novo cliente\".");
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
    void ativarLocacao(ActionEvent event) throws IOException {
    	
    	// Sera feito no Sprint de aluguel
    }
    
    
    @FXML
    void consultasGerais(ActionEvent event) throws IOException {
    	
    	// Aqui serao feitas as consultas gerais, e talvez o acesso ao financeiro da empresa
    }
    
    @FXML
    void sair(ActionEvent event) throws IOException {
    	
    	main.showLoginFuncionario();
    }
}
