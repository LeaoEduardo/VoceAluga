package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TelaPrincipalController {
	
	Main main = new Main();
	
	@FXML
    private Label tf_username;

    @FXML
    private Label tf_nivel;
    
    @FXML
    void initialize() {
       
    	String usuario = Contexto.getInstancia().getUsuario();
    	String nivel = Contexto.getInstancia().getNivel();
    	tf_username.setText(usuario);
    	tf_nivel.setText(nivel);
    }
    
    @FXML
    void gerarLocacao(ActionEvent event) throws IOException {
    	
    	// Leva para a tela de gerar locacao
    	main.showGerarLocacao();
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
