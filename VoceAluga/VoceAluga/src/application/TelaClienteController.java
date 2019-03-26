package application;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

public class TelaClienteController {
	
	Main main = new Main();
	
    @FXML
    private Text tf_nome;
    @FXML
    private Text tf_cpf;
    @FXML
    private Text tf_dataNasc;
    @FXML
    private Text tf_nacionalidade;
    @FXML
    private Text tf_telefone;
    @FXML
    private Text tf_cnh;
    @FXML
    private Text tf_validadeCNH;
    
    private String nome = new String();
    private String cpf = new String();
    private String dataNasc = new String();
    private String nacionalidade = new String();
    private String telefone = new String();
    private String cnh = new String();
    private String validadeCNH = new String();
    
    @FXML
    void initialize() {
    	
    	// CPF inserido na pesquisa efetuada anteriormente na tela gerar locacao
        cpf = Contexto.getInstancia().getCpfCliente();
    	
    	// Aqui os dados do cliente devem ser carregados do BD, a partir do CPF acima
    	
    	// Apenas valores de teste
    	nome = "Fulano da Silva";
        dataNasc = "XX/XX/XXXX";
        nacionalidade = "Brasileiro(a)";
        telefone = "(XX)XXXXX-XXXX";
        cnh = "XXXXXXXXXXX";
        validadeCNH = "XX/XX/XXXX";
        
        tf_nome.setText(nome);
        tf_cpf.setText(cpf);
        tf_dataNasc.setText(dataNasc);
        tf_nacionalidade.setText(nacionalidade);
        tf_telefone.setText(telefone);
        tf_cnh.setText(cnh);
        tf_validadeCNH.setText(validadeCNH);
    }
    
    @FXML
    void reservarCarro(ActionEvent event) {
    	
    	// Sera feito no Sprint de aluguel
    }

    @FXML
    void apagarRegistro(ActionEvent event) throws IOException {
    	
    	// Aqui sera feita a remoção do cadastro do usuário do BD
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmar acao");
    	alert.setHeaderText("Este registro sera apagado");
    	alert.setContentText("Tem certeza de que deseja apagar este registro?");
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if (result.get() == ButtonType.OK) {
    	    // ... user chose OK
    		// apaga registro do cliente
    		main.showTelaPrincipal();
    	}
    		
    	else {
    	    // ... user chose CANCEL or closed the dialog
    	}
    }
    
    @FXML
    void cancelarReserva(ActionEvent event) {
    	
    	// Sera feito no Sprint de aluguel
    }
    
    @FXML
    void historico(ActionEvent event) {
    	
    	// Sera feito no Sprint de aluguel
    }

    @FXML
    void contratempos(ActionEvent event) {
    	
    	// Sera feito no Sprint de aluguel
    }

    @FXML
    void apoliceSeguros(ActionEvent event) {
    	
    	// Sera feito no Sprint de aluguel
    }
    
    @FXML
    void voltar(ActionEvent event) throws IOException {
    	
    	main.showTelaPrincipal();
    }
}
