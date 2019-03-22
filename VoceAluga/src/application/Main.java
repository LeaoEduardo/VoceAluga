package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	private Stage primaryStage;
    private static BorderPane rootLayout;
    
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sistema VoceAluga");
        
        initRootLayout();
        showLoginFuncionario();
		
		//Pane root = FXMLLoader.load(getClass().getResource("LoginFuncionario.fxml"));
		
		//Scene scene = new Scene(root,800,600);
		//primaryStage.setScene(scene);
		//primaryStage.show();
	}
	
	public void initRootLayout() throws IOException {

            // Carrega o root layout do arquivo fxml.
            //FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
            
            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

	public void showLoginFuncionario() throws IOException {
		
            // Carrega o login de funcionario.
            // FXMLLoader loader = new FXMLLoader();
            // loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane loginFuncionario = FXMLLoader.load(getClass().getResource("LoginFuncionario.fxml"));
            
            // Define o login de funcionario dentro do root layout.
            rootLayout.setCenter(loginFuncionario);
    }
	
	public void showTelaPrincipal() throws IOException {

		// LoginFuncionarioController controller = new LoginFuncionarioController();
		
		AnchorPane telaPrincipal = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));
		rootLayout.setCenter(telaPrincipal);		
	}
	
	//private void processaLogin() throws IOException  {
		
		// se Login for correto:
		//showTelaPrincipal();
		
	//}
	
	public static void main(String[] args) {
		launch(args);
	}
}
