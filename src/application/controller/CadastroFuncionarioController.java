package application.controller;

import application.dao.FuncionarioDAO;
import application.entity.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CadastroFuncionarioController {

	@FXML
	private CheckBox checkbox_ativo;
	@FXML
	private ChoiceBox<?> cb_nivel;
	@FXML
	private TextField tf_nome;
	@FXML
	private ChoiceBox<?> cb_filial;
	@FXML
	private TextField tf_login;
	@FXML
	private PasswordField tf_senha;

	@FXML
	void cadastrarFuncionario(ActionEvent event) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(tf_nome.getText());
		funcionario.setUsuario(tf_login.getText());
		funcionario.setSenha(tf_senha.getText());
		funcionario.setIdFilial(1);
		funcionario.setIdTipo(1);
		funcionario.setAtivo(true);

		if ((new FuncionarioDAO()).insert(funcionario)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Cadastro efetuado");
			alert.setContentText("Funcionário cadastrado com sucesso.");
			alert.showAndWait();
			((Stage) tf_nome.getScene().getWindow()).close();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Erro");
			alert.setContentText("Eu ainda não sei dizer o que deu errado!");
			alert.showAndWait();
		}

	}

	@FXML
	void cancelar(ActionEvent event) {
		((Stage) tf_nome.getScene().getWindow()).close();
	}

}
