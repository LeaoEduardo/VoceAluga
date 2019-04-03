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
    
    @FXML
    void initialize() {
    	
    	// CPF/Passaporte inserido na pesquisa efetuada anteriormente na tela gerar locacao
    	String cpf = Contexto.getInstancia().getCliente().getCPF();
    	String passaporte = Contexto.getInstancia().getCliente().getPassaporte();
    	
    	// Abaixo os dados do cliente devem ser carregados do BD, a partir do CPF/Passaporte acima
        
        // Se CPF do cliente estiver preenchido
        if (Contexto.getInstancia().getCliente().getCPF() != null) {
        	
        	// Use o CPF do cliente para carregar os dados do BD
        	carregarPorCPF(cpf);
        }
        
        // Se nao, o passaporte obrigatoriamente estara preenchido
        else {
        	
        	// Use o passaporte do cliente para carregar os dados do BD
        	carregarPorPassaporte(passaporte);
        }
        
        String nome = Contexto.getInstancia().getCliente().getNomeCliente();
        String nacionalidade = Contexto.getInstancia().getCliente().getNacionalidade();
        String telefone = Contexto.getInstancia().getCliente().getTelefone();
        String cnh = Contexto.getInstancia().getCliente().getCnh();
        LocalDate dataNasc = Contexto.getInstancia().getCliente().getDataNascimento();
        LocalDate validadeCNH = Contexto.getInstancia().getCliente().getDatacnh();
        
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
    }

    void carregarPorPassaporte(String passaporte) {
    	t_cpf.setVisible(false);
    	tf_cpfPassaporte.setText(passaporte);
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
    		ConnectionSQL con = new ConnectionSQL();
    		if(con.RemoverCliente(Contexto.getInstancia().getCliente().getIdCliente())) {
    			alert = new Alert(AlertType.CONFIRMATION);
    		    alert.setTitle("Erro");
    		    alert.setHeaderText("Cliente Apagado.");
    		    alert.showAndWait();
    	    	main.showTelaPrincipal();
    		}
    		else {
    			alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Erro");
    		    alert.setHeaderText("Impossivel apagar esse cliente.");
    		    alert.setContentText("Tente novamente.");
    		    alert.showAndWait();
    		}
    		
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
