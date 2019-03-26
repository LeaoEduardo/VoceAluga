package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	private Stage primaryStage;
    private static BorderPane rootLayout;
    
    
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sistema VoceAluga");
        
        initRootLayout();
        showLoginFuncionario();
	}
	
	
	public void initRootLayout() throws IOException {

            // Carrega o root layout do arquivo fxml.
            rootLayout = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
            
            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

	
	public void showLoginFuncionario() throws IOException {
		
            // Carrega o login de funcionario.
            AnchorPane loginFuncionario = FXMLLoader.load(getClass().getResource("LoginFuncionario.fxml"));
            
            // Define o login de funcionario dentro do root layout.
            rootLayout.setCenter(loginFuncionario);
    }
	
	
	public void showTelaPrincipal() throws IOException {
		
		// Carrega o menu principal funcionario
		AnchorPane telaPrincipal = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));
		rootLayout.setCenter(telaPrincipal);
	}
	
	
	
	
	public void showTelaCliente() throws IOException {
		
		// Carrega a tela do cliente
		AnchorPane telaCliente = FXMLLoader.load(getClass().getResource("TelaCliente.fxml"));
		rootLayout.setCenter(telaCliente);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
