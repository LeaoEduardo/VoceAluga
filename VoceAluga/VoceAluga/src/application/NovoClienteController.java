package application;

import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovoClienteController {

	@FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_cpf;
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
    void cadastrarCliente(ActionEvent event) {
    	
    	String nome = tf_nome.getText();
        String cpf = tf_cpf.getText();
        String nacionalidade = tf_nacionalidade.getText();
        String telefone = tf_telefone.getText();
        String cnh = tf_cnh.getText();
        
        // Se algum campo estiver vazio
        // Aqui deve ser feita checagem adicional para garantir que todos os campos foram preenchidos corretamente!!!
        if (nome.equals("") || cpf.equals("") || tf_dataNasc.getValue() == null || nacionalidade.equals("") || telefone.equals("") || cnh.equals("") || tf_validadeCNH.getValue() == null) {
        	
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Erro");
        	alert.setHeaderText("Erro no cadastro.");
        	alert.setContentText("Todos os campos devem ser preenchidos!");
        	alert.showAndWait();
        }
        	
        // Se estiver tudo certo
        else {
        	
        	// As strings abaixo saem em formato "dd/mm/aaaa", para serem inseridas no banco de dados de forma padronizada
        	String dataNasc = tf_dataNasc.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        	String validadeCNH = tf_validadeCNH.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        	
        	// Aqui os dados do cliente devem ser registrados no BD
        	// (nome,cpf,dataNasc,nacionalidade,telefone,cnh,validadeCNH)
        	
        	// Prints de teste
        	System.out.println(nome);
            System.out.println(cpf);
            System.out.println(dataNasc);
            System.out.println(nacionalidade);
            System.out.println(telefone);
            System.out.println(cnh);
            System.out.println(validadeCNH);
        	
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setHeaderText("Cadastro efetuado");
	        alert.setContentText("Cliente cadastrado com sucesso.");
	        alert.showAndWait();
	        Stage stage = (Stage) botaoCadastrarCliente.getScene().getWindow();
	        stage.close();
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
    	
    	Stage stage = (Stage) botaoCancelar.getScene().getWindow();
        stage.close();
    }
}

