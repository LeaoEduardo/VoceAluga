package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginFuncionarioController {
	
	private Main main = new Main();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
	public void processaLogin(ActionEvent event) throws IOException {
    	
    	String usuario = username.getText();
    	String senha = password.getText();
    	
    	System.out.println(usuario);
    	System.out.println(senha);
    	
    	if (usuario.equals("rodrigo") && senha.equals("123")) {
    		System.out.println("Usuario logado com sucesso.");
    		main.showTelaPrincipal();
    	}
    	
    	else {
    
		    Alert alert = new Alert(AlertType.ERROR);
		    alert.setTitle("Erro");
		    alert.setHeaderText("O nome de usuário ou a senha estão incorretos.");
		    alert.setContentText("Tente novamente.");
		    alert.showAndWait();
		    username.setText("");
		    password.setText("");
    	}
    }
    
    @FXML
    void initialize() {
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'LoginFuncionario.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'LoginFuncionario.fxml'.";

    }
}
