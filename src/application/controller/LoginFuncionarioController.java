package application.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import application.Contexto;
import application.Main;
import application.entity.Funcionario;
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
		fazerLogin();
	}

	@FXML
	void processaLoginEnter(ActionEvent event) throws IOException {
		fazerLogin();
	}

	private void fazerLogin() throws IOException {

		String usuario = tf_username.getText();
		String senha = tf_password.getText();

		Funcionario funcionario = Funcionario.loginFuncionario(usuario, senha);
		if (funcionario != null) {
			Contexto.getInstancia().setFuncionario(funcionario);
			main.showTelaPrincipal();
		} else {
			// Nao conseguindo logar
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
