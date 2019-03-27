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
    private PasswordField tf_password;

    @FXML
    private TextField tf_username;

    @FXML
	void processaLoginBotao(ActionEvent event) throws IOException {
    	this.fazerLogin();    	
    }
    
    @FXML
	void processaLoginEnter(ActionEvent event) throws IOException {
    	this.fazerLogin();
    }

    private void fazerLogin() throws IOException {
    	
    	String usuario = tf_username.getText();
    	String senha = tf_password.getText();
    		
    	// Prints de teste
        System.out.println(usuario);
    	System.out.println(senha);
    	
    	ConnectionSQL con = new ConnectionSQL();
    	
    	// Aqui deve ser feito o teste para checar se o funcionario esta cadastrado no BD
    	if (con.LoginFuncionario(usuario, senha)) {
    		
    		// O nivel do usuario (agente/gerente) deve ser pego do BD
    		main.showTelaPrincipal();
    	}
    	
    	/* else if (usuario.equals("user") && senha.equals("123")) {
    		Contexto.getInstancia().setUsuario("user", "Agente", 2);
    		main.showTelaPrincipal();
    	}
    	
    	else if (usuario.equals("admin") && senha.equals("123")) {
    		Contexto.getInstancia().setUsuario("admin", "Gerente", 1);
    		main.showTelaPrincipal();
    	} */
    	
    	else {
    		
    		// Caso nao esteja
		    Alert alert = new Alert(AlertType.ERROR);
		    alert.setTitle("Erro");
		    alert.setHeaderText("O nome de usuario ou a senha estao incorretos.");
		    alert.setContentText("Tente novamente.");
		    alert.showAndWait();
		    tf_username.setText("");
		    tf_password.setText("");
    	}
    }
}
