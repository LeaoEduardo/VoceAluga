package application;

import java.io.IOException;
import java.time.LocalDate;

import application.entity.Cliente;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AtualizarClienteController {

	private Main main = new Main();
	
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
    private Button botaoAtualizarCliente;
    @FXML
    private ChoiceBox<String> cb_cpfPassaporte;
    
    private String nome = new String();
    private String cpf = new String();
    private String passaporte = new String();
    private String nacionalidade = new String();
    private String telefone = new String();
    private String cnh = new String();
    private LocalDate dataNasc;
    private LocalDate validadeCNH;
    
    @FXML
    void initialize() {
    	
    	ObservableList<String> escolhas = FXCollections.observableArrayList("CPF", "Passaporte"); 
        cb_cpfPassaporte.setItems(escolhas);
        ReadOnlyIntegerProperty property = cb_cpfPassaporte.getSelectionModel().selectedIndexProperty();
        FormatadorTexto formatador = Contexto.getInstancia().getFormatador();
        property.addListener((value, oldValue, newValue)->tf_cpfPassaporte.setTextFormatter(formatador.CpfPassaporte((Integer)newValue,tf_cpfPassaporte)));
        tf_cnh.setTextFormatter(Contexto.getInstancia().getFormatador().CNH());
        
    	
    	if (!Contexto.getInstancia().getCliente().getCpf().equals("")) {
    		cb_cpfPassaporte.getSelectionModel().select(0);
    		cpf = Contexto.getInstancia().getCliente().getCpf();
    		tf_cpfPassaporte.setText(cpf);
    	}
    	
    	else {
    		cb_cpfPassaporte.getSelectionModel().select(1);
    		passaporte = Contexto.getInstancia().getCliente().getPassaporte();
    		tf_cpfPassaporte.setText(passaporte);
    	}
    	
    	nome = Contexto.getInstancia().getCliente().getNome();
        dataNasc = Contexto.getInstancia().getCliente().getDataNasc();
        nacionalidade = Contexto.getInstancia().getCliente().getNacionalidade();
        telefone = Contexto.getInstancia().getCliente().getTelefone();
        cnh = String.valueOf( Contexto.getInstancia().getCliente().getCnh() );
        validadeCNH = Contexto.getInstancia().getCliente().getDataCnh();
        
        
    	tf_nome.setText(nome);
    	tf_dataNasc.setValue(dataNasc);
    	tf_nacionalidade.setText(nacionalidade);
		tf_telefone.setText(telefone);
    	tf_cnh.setText(cnh);
    	tf_validadeCNH.setValue(validadeCNH);
    }
    
    @FXML
    void atualizarCliente(ActionEvent event) throws IOException {
    	
    	ConnectionSQL con = new ConnectionSQL();
    	
    	int    id = Contexto.getInstancia().getCliente().getId() ;
    	String nome = tf_nome.getText();
    	String cpf = Contexto.getInstancia().getCliente().getCpf();
    	String passaporte = Contexto.getInstancia().getCliente().getPassaporte();
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
        if (nome.equals("") || tf_cpfPassaporte.getText().equals("") || tf_dataNasc.getValue() == null || nacionalidade.equals("") || 
        		telefone.equals("") || cnh.equals("") || tf_validadeCNH.getValue() == null) {
        	
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
        	
        	// // Aqui os dados alterados do cliente deverao ser armazenados no BD
        	// (nome,cpf/passaporte,dataNasc,nacionalidade,telefone,cnh,validadeCNH)
        	
        	boolean atualizou = con.AtualizaCliente(id, nome, cpf, passaporte, dataNasc.toString(), nacionalidade,
					telefone, cnh, validadeCNH.toString());

        	if(atualizou) {
        		// Prints de teste
        		System.out.println("idCliente: " + id);
            	System.out.println("nome: " + nome);
                System.out.println("cpf: " + cpf);
                System.out.println("passaporte: " + passaporte);
                System.out.println("dataNasc: " + dataNasc.toString());
                System.out.println("nacionalidade: " + nacionalidade);
                System.out.println("telefone: " + telefone);
                System.out.println("cnh: " + cnh);
                System.out.println("validadeCNH: " + validadeCNH.toString());
                
                Cliente cliente = new Cliente();
                cliente.setId( id );
                cliente.setNome(nome);
                cliente.setCpf(cpf);
                cliente.setPassaporte(passaporte);
                cliente.setDataNasc(dataNasc);
                cliente.setNacionalidade(nacionalidade);
                cliente.setTelefone(telefone);
                cliente.setCnh( cnh );
                
                Contexto.getInstancia().setCliente( cliente );
            	
    	        Alert alert = new Alert(AlertType.INFORMATION);
    	        alert.setHeaderText("Cadastro alterado");
    	        alert.setContentText("Registro atualizado com sucesso.");
    	        alert.showAndWait();
    	        Stage stage = (Stage) botaoAtualizarCliente.getScene().getWindow();
    	        stage.close();
    	        main.showTelaCliente();
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