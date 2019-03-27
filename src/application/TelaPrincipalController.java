package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
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
    private TextField tf_cpfPassaporte;
    
    @FXML
    private ChoiceBox<String> cb_cpfPassaporte;
    
    @FXML
    private TabPane telaPrincipalTabPane;
    
    @FXML
    private Tab abaCarro;
    
    @FXML
    private Tab abaFuncionarios;
    
    
    @FXML
    void initialize() {
    	
    	ObservableList<String> escolhas = FXCollections.observableArrayList("Por CPF", "Por passaporte"); 
        cb_cpfPassaporte.setItems(escolhas);
        cb_cpfPassaporte.getSelectionModel().selectFirst();
       
    	Usuario usuario = Contexto.getInstancia().getUsuario();
    	String filial = Contexto.getInstancia().getFilial();
    	tf_username.setText(usuario.getUsuario());
    	tf_nivel.setText(usuario.getNomeNivel());
		tf_filial.setText(filial);
    	
		
    	if (usuario.getNivel() != 1) {
    		//telaPrincipalTabPane.getTabs().add(abaCarro);
        	abaCarro.setDisable(true);
        	abaFuncionarios.setDisable(true);
        }
    }
    
    @FXML
    void pesquisarCliente(ActionEvent event) throws IOException {
    	
    	// Mudar tela principal para selecionar entre pesquisa por CPF ou passaporte
    	
    	if (cb_cpfPassaporte.getSelectionModel().getSelectedIndex() == 0) {
    		
    		String cpf = tf_cpfPassaporte.getText();
    		pesquisaPorCpf(cpf);
    	}
    	
    	else {
    		
    		String passaporte = tf_cpfPassaporte.getText();
    		pesquisaPorPassaporte(passaporte);
    	}
    }
    
    void pesquisaPorCpf(String cpf) throws IOException {
    	
    	// Seta os valores no Contexto para facil acesso, passaporte sera ""
    	Contexto.getInstancia().setCpfCliente(cpf);
    	Contexto.getInstancia().setPassaporteCliente("");
    	System.out.println("cpf: " + cpf);
        System.out.println("passaporte: " + Contexto.getInstancia().getPassaporteCliente());
    	
    	// Aqui deve ser checado se o CPF esta registrado no BD
    	
    	// se estiver la
    	if (cpf.equals("123.456.789-00")) {
    		main.showTelaCliente();
    	}
    	
    	//se nao estiver
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erro");
    		alert.setHeaderText("Cliente nao encontrado.");
    		alert.setContentText("Para cadastra-lo, clique em \"Novo cliente\".");
    		alert.showAndWait();
    		tf_cpfPassaporte.setText("");
    	}
    }
    
    void pesquisaPorPassaporte(String passaporte) throws IOException {
    	
    	// Seta os valores no Contexto para facil acesso, CPF sera ""
    	Contexto.getInstancia().setCpfCliente("");
    	Contexto.getInstancia().setPassaporteCliente(passaporte);
    	
    	// Aqui deve ser checado se o passaporte esta registrado no BD
    	
    	// se estiver la
    	
    	if (passaporte.equals("AB123456")) {
    		main.showTelaCliente();
    	}
    	
    	//se nao estiver
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erro");
    		alert.setHeaderText("Cliente nao encontrado.");
    		alert.setContentText("Para cadastra-lo, clique em \"Novo cliente\".");
    		alert.showAndWait();
    		tf_cpfPassaporte.setText("");
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
