package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        
    	
    	if (!Contexto.getInstancia().getCpfCliente().equals("")) {
    		cb_cpfPassaporte.getSelectionModel().select(0);
    		cpf = Contexto.getInstancia().getCpfCliente();
    		tf_cpfPassaporte.setText(cpf);
    	}
    	
    	else {
    		cb_cpfPassaporte.getSelectionModel().select(1);
    		passaporte = Contexto.getInstancia().getPassaporteCliente();
    		tf_cpfPassaporte.setText(passaporte);
    	}
    	// Aqui os dados do cliente devem ser carregados do BD, a partir do CPF acima
    	// Nao é necessario, os dados do cliente ja estao em contexto
    	
    	// Apenas valores de teste
    	nome = Contexto.getInstancia().getCliente().getNomeCliente();
        dataNasc = Contexto.getInstancia().getCliente().getDataNascimento();
        nacionalidade = Contexto.getInstancia().getCliente().getNacionalidade();
        telefone = Contexto.getInstancia().getCliente().getTelefone();
        cnh = Contexto.getInstancia().getCliente().getCnh();
        validadeCNH = Contexto.getInstancia().getCliente().getDatacnh();
       
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
    	
    	String id = Contexto.getInstancia().getCliente().getIdCliente();
    	String nome = tf_nome.getText();
    	String cpf = Contexto.getInstancia().getCpfCliente();
    	String passaporte = Contexto.getInstancia().getPassaporteCliente();
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
        // Aqui deve ser feita checagem adicional para garantir que todos os campos foram preenchidos corretamente!!!
        if (nome.equals("") || tf_cpfPassaporte.getText().equals("") || tf_dataNasc.getValue() == null || nacionalidade.equals("") || telefone.equals("") || cnh.equals("") || tf_validadeCNH.getValue() == null) {
        	
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Erro");
        	alert.setHeaderText("Erro no cadastro.");
        	alert.setContentText("Todos os campos devem ser preenchidos!");
        	alert.showAndWait();
        }
        	
        // Se estiver tudo certo
        else {
        	
        	// As strings abaixo saem em formato "dd/mm/aaaa", para serem inseridas no banco de dados de forma padronizada
        	LocalDate dataNasc = tf_dataNasc.getValue();
        	LocalDate validadeCNH = tf_validadeCNH.getValue();
        	
        	// // Aqui os dados alterados do cliente deverao ser armazenados no BD
        	// (nome,cpf/passaporte,dataNasc,nacionalidade,telefone,cnh,validadeCNH)
        	Contexto.getInstancia().setCpfCliente(cpf);
        	Contexto.getInstancia().setPassaporteCliente(passaporte);
        	
        	boolean atualizou = con.AtualizaCliente(id, nome, cpf, passaporte, dataNasc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), nacionalidade,
					telefone, cnh, validadeCNH.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        	if(atualizou) {
        		// Prints de teste
        		System.out.println("idCliente: " + id);
            	System.out.println("nome: " + nome);
                System.out.println("cpf: " + cpf);
                System.out.println("passaporte: " + passaporte);
                System.out.println("dataNasc: " + dataNasc.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("nacionalidade: " + nacionalidade);
                System.out.println("telefone: " + telefone);
                System.out.println("cnh: " + cnh);
                System.out.println("validadeCNH: " + validadeCNH.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                
                Contexto.getInstancia().setCliente(id, nome, cpf, passaporte, dataNasc, nacionalidade, telefone, cnh, validadeCNH);
            	
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