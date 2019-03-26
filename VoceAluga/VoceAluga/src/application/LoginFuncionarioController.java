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
	void processaLogin(ActionEvent event) throws IOException {
    	
    	String usuario = tf_username.getText();
    	String senha = tf_password.getText();
    	String nivel = new String();
    		
    	// Prints de teste
        System.out.println(usuario);
    	System.out.println(senha);
    	
    	// Aqui deve ser feito o teste para checar se o funcionario esta cadastrado no BD
    	if (usuario.equals("user") && senha.equals("123")) {
    		
    		// O nivel do usuario (agente/gerente) deve ser pego do BD
    		nivel = "Funcionario";
    		Contexto.getInstancia().setUsuario(usuario);
    		Contexto.getInstancia().setNivel(nivel);
    		main.showTelaPrincipal();
    	}
    	
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
