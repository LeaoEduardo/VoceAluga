package application;

import java.time.LocalDate;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovoClienteController {

	@FXML
    private TextField tf_nome;
	@FXML
    private TextField tf_cpfPassaporte;
    @FXML
    private DatePicker tf_dataNasc;
    @FXML
    private TextField tf_nacionalidade;
    @FXML
    private TextField tf_telefone;
    @FXML
    private TextField tf_cnh;
    @FXML
    private DatePicker tf_validadeCNH;
    @FXML
    private Button botaoCancelar;
    @FXML
    private Button botaoCadastrarCliente;
    @FXML
    private ChoiceBox<String> cb_cpfPassaporte;
    
    @FXML
    public void initialize() {
    	
    	ObservableList<String> escolhas = FXCollections.observableArrayList("CPF", "Passaporte"); 
        cb_cpfPassaporte.setItems(escolhas);
        ReadOnlyIntegerProperty property = cb_cpfPassaporte.getSelectionModel().selectedIndexProperty();
        FormatadorTexto formatador = Contexto.getInstancia().getFormatador();
        property.addListener((value, oldValue, newValue)->tf_cpfPassaporte.setTextFormatter(formatador.CpfPassaporte((Integer)newValue,tf_cpfPassaporte)));
        cb_cpfPassaporte.getSelectionModel().selectFirst();
        tf_cnh.setTextFormatter(Contexto.getInstancia().getFormatador().CNH());
    }
    
    @FXML
    void cadastrarCliente(ActionEvent event) {
    	
    	ConnectionSQL con = new ConnectionSQL();
    	
    	String nome = tf_nome.getText();
    	String cpf = "";
    	String passaporte = "";
        String nacionalidade = tf_nacionalidade.getText();
        String telefone = tf_telefone.getText();
        String cnh = tf_cnh.getText();
        
        // se o campo selecionado for CPF
        if (cb_cpfPassaporte.getSelectionModel().getSelectedIndex() == 0){
            cpf = tf_cpfPassaporte.getText();
        }
        // se nao, selecionou passaporte
        else {
        	passaporte = tf_cpfPassaporte.getText();
        }
        
        // Se algum campo estiver vazio
        if (nome.equals("") || tf_cpfPassaporte.getText().equals("") || tf_dataNasc.getValue() == null || nacionalidade.equals("") || telefone.equals("") || cnh.equals("") || tf_validadeCNH.getValue() == null) {
        	
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Erro");
        	alert.setHeaderText("Erro no cadastro.");
        	alert.setContentText("Todos os campos devem ser preenchidos!");
        	alert.showAndWait();
        }
        	
        // Se estiver tudo certo
        else {
        	
        	LocalDate dataNasc = tf_dataNasc.getValue();
        	LocalDate validadeCNH = tf_validadeCNH.getValue();
        	
        	// Aqui os dados do cliente devem ser registrados no BD
        	// (nome,cpf/passaporte,dataNasc,nacionalidade,telefone,cnh,validadeCNH)
        	boolean cadastrou = con.CadastrarCliente(nome, cpf, passaporte, dataNasc.toString(), nacionalidade,
        											telefone, cnh, validadeCNH.toString());
        	
        	if(cadastrou) {
        		// Prints de teste
            	System.out.println("nome: " + nome);
                System.out.println("cpf: " + cpf);
                System.out.println("passaporte: " + passaporte);
                System.out.println("dataNasc: " + dataNasc.toString());
                System.out.println("nacionalidade: " + nacionalidade);
                System.out.println("telefone: " + telefone);
                System.out.println("cnh: " + cnh);
                System.out.println("validadeCNH: " + validadeCNH.toString());
            	
    	        Alert alert = new Alert(AlertType.INFORMATION);
    	        alert.setHeaderText("Cadastro efetuado");
    	        alert.setContentText("Cliente cadastrado com sucesso.");
    	        alert.showAndWait();
    	        Stage stage = (Stage) botaoCadastrarCliente.getScene().getWindow();
    	        stage.close();
        	}
        	
        	else {
        		Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("Erro");
            	alert.setHeaderText("Erro no cadastro.");
            	alert.setContentText("Dados inconsistentes!");
            	alert.showAndWait();
        	}
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
    	
    	Stage stage = (Stage) botaoCancelar.getScene().getWindow();
        stage.close();
    }
}