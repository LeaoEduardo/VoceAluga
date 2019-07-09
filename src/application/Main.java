package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws IOException {

		Main.primaryStage = primaryStage;
		primaryStage.setTitle("Sistema VoceAluga");

		showLoginFuncionario();
		primaryStage.show();
	}

	public void showLoginFuncionario() throws IOException {

		// Carrega o login de funcionario.
		Parent loginFuncionario = FXMLLoader.load(getClass().getResource("controller/LoginFuncionario.fxml"));
		Scene scene = new Scene(loginFuncionario);
		primaryStage.setScene(scene);
	}

	public void showTelaPrincipal() throws IOException {

		// Carrega o menu principal funcionario
		Parent telaPrincipal = FXMLLoader.load(getClass().getResource("controller/TelaPrincipal.fxml"));
		Scene scene = new Scene(telaPrincipal);
		primaryStage.setScene(scene);
	}

	public void showTelaCliente() throws IOException {

		// Carrega a tela do cliente
		Parent telaCliente = FXMLLoader.load(getClass().getResource("controller/TelaCliente.fxml"));
		Scene scene = new Scene(telaCliente);
		primaryStage.setScene(scene);
	}

	public void showTelaVeiculo() throws IOException {

		// Carrega a tela do veiculo
		Parent telaVeiculo = FXMLLoader.load(getClass().getResource("controller/TelaVeiculo.fxml"));
		Scene scene = new Scene(telaVeiculo);
		primaryStage.setScene(scene);
	}
}