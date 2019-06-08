package application;

import application.dao.ClienteDAO;
import application.entity.Cliente;
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
    	
    	Cliente cliente = new Cliente();
    	cliente.setNome(tf_nome.getText());
    	cliente.setNacionalidade(tf_nacionalidade.getText());
    	cliente.setTelefone(tf_telefone.getText());
    	cliente.setCnh(tf_cnh.getText());
        
        // se o campo selecionado for CPF
        if (cb_cpfPassaporte.getSelectionModel().getSelectedIndex() == 0){
            cliente.setCpf(tf_cpfPassaporte.getText());
        }
        // se nao, selecionou passaporte
        else {
        	cliente.setPassaporte(tf_cpfPassaporte.getText());
        }
        
        // Se algum campo estiver vazio
        if ( cliente.getNome().equals("") || tf_cpfPassaporte.getText().equals("") || tf_dataNasc.getValue() == null 
        		|| cliente.getNacionalidade().equals("") || cliente.getTelefone().equals("") || cliente.getCnh().equals("") 
        		|| tf_validadeCNH.getValue() == null ) {
        	
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Erro");
        	alert.setHeaderText("Erro no cadastro.");
        	alert.setContentText("Todos os campos devem ser preenchidos!");
        	alert.showAndWait();
        }
        	
        // Se estiver tudo certo
        else {
        	
        	cliente.setDataNasc( tf_dataNasc.getValue() );
        	cliente.setDataCnh( tf_validadeCNH.getValue() );

        	boolean cadastrou = (new ClienteDAO()).insert(cliente);
        	
        	if(cadastrou) {
        		// Prints de teste
            	System.out.println("nome: " + cliente.getNome());
                System.out.println("cpf: " + cliente.getCpf());
                System.out.println("passaporte: " + cliente.getPassaporte());
                System.out.println("dataNasc: " + cliente.getDataNasc().toString());
                System.out.println("nacionalidade: " + cliente.getNacionalidade());
                System.out.println("telefone: " + cliente.getTelefone());
                System.out.println("cnh: " + cliente.getCnh());
                System.out.println("validadeCNH: " + cliente.getDataCnh().toString());
            	
    	        Alert alert = new Alert(AlertType.INFORMATION);
    	        alert.setHeaderText("Cadastro efetuado");
    	        alert.setContentText("Cliente cadastrado com sucesso.");
    	        alert.showAndWait();
    	        Stage stage = (Stage) botaoCadastrarCliente.getScene().getWindow();
    	        stage.close();
        	}
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
    	
    	Stage stage = (Stage) botaoCancelar.getScene().getWindow();
        stage.close();
    }
}