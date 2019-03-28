package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaClienteController {
	
	Main main = new Main();
	
	@FXML
    private Text t_cpf;
	@FXML
    private Text t_passaporte;
    @FXML
    private Text tf_nome;
    @FXML
    private Text tf_cpfPassaporte;
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
    private String passaporte = new String();
    private String nacionalidade = new String();
    private String telefone = new String();
    private String cnh = new String();
    private LocalDate dataNasc;
    private LocalDate validadeCNH;
    
    @FXML
    void initialize() {
    	
    	// CPF/Passaporte inserido na pesquisa efetuada anteriormente na tela gerar locacao
        cpf = Contexto.getInstancia().getCpfCliente();
        passaporte = Contexto.getInstancia().getPassaporteCliente();
    	
    	// Abaixo os dados do cliente devem ser carregados do BD, a partir do CPF/Passaporte acima
        
        // Se CPF do cliente estiver preenchido
        if (!Contexto.getInstancia().getCpfCliente().equals("")) {
        	
        	// Use o CPF do cliente para carregar os dados do BD
        	carregarPorCPF(cpf);
        }
        
        // Se nao, o passaporte obrigatoriamente estara preenchido
        else {
        	
        	// Use o passaporte do cliente para carregar os dados do BD
        	carregarPorPassaporte(passaporte);
        }
        
        tf_nome.setText(nome);
        tf_dataNasc.setText(dataNasc.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        tf_nacionalidade.setText(nacionalidade);
        tf_telefone.setText(telefone);
        tf_cnh.setText(cnh);
        tf_validadeCNH.setText(validadeCNH.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
    
    void carregarPorCPF(String cpf) {
    	
    	t_passaporte.setVisible(false);
    	tf_cpfPassaporte.setText(cpf);
    	
    	nome = "Fulano da Silva";
        dataNasc = LocalDate.parse("12/12/2012", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        nacionalidade = "Brasileiro(a)";
        telefone = "(XX)XXXXX-XXXX";
        cnh = "XXXXXXXXXXX";
        validadeCNH = LocalDate.parse("12/12/2012", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    
    void carregarPorPassaporte(String passaporte) {
    	
    	t_cpf.setVisible(false);
    	tf_cpfPassaporte.setText(passaporte);
    	
    	nome = "Fulano da Silva";
    	dataNasc = LocalDate.parse("12/12/2012", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        nacionalidade = "Brasileiro(a)";
        telefone = "(XX)XXXXX-XXXX";
        cnh = "XXXXXXXXXXX";
        validadeCNH = LocalDate.parse("12/12/2012", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    
    @FXML
    void reservarCarro(ActionEvent event) {
    	
    	// Sera feito no Sprint de aluguel
    }
    
    @FXML 
    void atualizarRegistro(ActionEvent event) throws IOException {
    	
    	// Abre a janela de cadastro de novo cliente 
    	Stage stage = new Stage();
    	Parent atualizarCliente = FXMLLoader.load(getClass().getResource("AtualizarCliente.fxml"));
    	Scene scene = new Scene(atualizarCliente);
    	
    	stage.setTitle("Atualizar registro de cliente");
    	stage.setScene(scene);
        stage.show();
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
    		// apaga registro do cliente
    		main.showTelaPrincipal();
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
