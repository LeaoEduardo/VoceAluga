package application;

import java.io.IOException;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
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
    private Tab abaVeiculos;
    
    @FXML
    private Tab abaFuncionarios;
    
    ConnectionSQL con = new ConnectionSQL();
    
    @FXML
    void initialize() {
    	
    	ObservableList<String> escolhas = FXCollections.observableArrayList("Por CPF", "Por passaporte"); 
        cb_cpfPassaporte.setItems(escolhas);
        ReadOnlyIntegerProperty property = cb_cpfPassaporte.getSelectionModel().selectedIndexProperty();
        FormatadorTexto formatador = Contexto.getInstancia().getFormatador();
        property.addListener((value, oldValue, newValue)->tf_cpfPassaporte.setTextFormatter(formatador.CpfPassaporte((Integer)newValue,tf_cpfPassaporte)));
        cb_cpfPassaporte.getSelectionModel().selectFirst();
       
    	Usuario usuario = Contexto.getInstancia().getUsuario();
    	tf_username.setText(usuario.getUsuario());
    	tf_nivel.setText(usuario.getNomeNivel());
		tf_filial.setText(usuario.getNomeFilial());
		
    	if (usuario.getNivel() != 1) {
    		
    		TabPane tabPane = abaVeiculos.getTabPane();
        	tabPane.getTabs().remove(abaVeiculos);
        	tabPane.getTabs().remove(abaFuncionarios);
        }
    }
    
    @FXML
    void pesquisarCliente(ActionEvent event) throws IOException {
    	
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
    	
    	// Aqui deve ser checado se o CPF esta registrado no BD
    	
    	// se estiver la
    	if (!cpf.equals("") && con.ConsultaClienteCPF(cpf)) {
    		main.showTelaCliente();
    		
    		// Prints de teste
    		System.out.println("cpf: " + Contexto.getInstancia().getCliente().getCPF());
            System.out.println("passaporte: " + Contexto.getInstancia().getCliente().getPassaporte());
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
    	
    	// Aqui deve ser checado se o passaporte esta registrado no BD
    	
    	// se estiver la
    	if (!passaporte.equals("") && con.ConsultaClientePassaporte(passaporte)) {
    		main.showTelaCliente();
    		
    		// Prints de teste
    		System.out.println("cpf: " + Contexto.getInstancia().getCliente().getCPF());
            System.out.println("passaporte: " + Contexto.getInstancia().getCliente().getPassaporte());
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
    	Parent novoCliente = FXMLLoader.load(getClass().getResource("NovoCliente.fxml"));
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
