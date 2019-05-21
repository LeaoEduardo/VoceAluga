package application;

import java.io.IOException;
import java.time.LocalDate;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
    
    @FXML
    private AnchorPane clientePane;
    boolean clientePaneBool = false;
    
    @FXML
    private Text tf_nome;
    
    @FXML
    private Text tf_cnh;
    
    @FXML
    private TextField tf_placa;
    
    @FXML
    private TableView<Veiculo> tabelaVeiculos;
    
    @FXML
    private TableColumn<Veiculo, String> col_grupo;
    
    @FXML
    private TableColumn<Veiculo, String> col_marca;
    
    @FXML
    private TableColumn<Veiculo, String> col_modelo;
    
    @FXML
    private TableColumn<Veiculo, String> col_placa;
    
    @FXML
    private TableColumn<Veiculo, Integer> col_quilometragem;
    
    @FXML
    private TableColumn<Veiculo, LocalDate> col_dataM;
    
    @FXML
    private TableColumn<Veiculo, LocalDate> col_dataC;
    
    @FXML
    private TableColumn<Veiculo, String> col_filial;

    @FXML
    private TableColumn<Veiculo, String> col_estado;

	private ObservableList<Veiculo> listaVeiculos = FXCollections.observableArrayList();
    
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
		
		clientePane.setVisible(clientePaneBool);
		
    	if (usuario.getNivel() != 1) {
    		
    		TabPane tabPane = abaVeiculos.getTabPane();
        	tabPane.getTabs().remove(abaVeiculos);
        	tabPane.getTabs().remove(abaFuncionarios);
        }
    	
    	else {
    		
    		telaPrincipalTabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
    	        if (newTab == abaVeiculos)
    	        	carregaVeiculos();
    	    });
    		
    		if (Contexto.getInstancia().getVoltandoParaVeiculos()) {
    			
    			telaPrincipalTabPane.getSelectionModel().select(abaVeiculos);
    			Contexto.getInstancia().setVoltandoParaVeiculos(false);
    		}
    	}
    }
    
    void carregaVeiculos() {
    	
    	// Preenche a tabela de veiculos com todos os veiculos no BD
    	
    	con.ConsultaTodosVeiculos();
		listaVeiculos = Contexto.getInstancia().getListaVeiculos();
		
		col_grupo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGrupo()));
		col_marca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
		col_modelo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModelo()));
		col_placa.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlaca()));
		col_quilometragem.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuilometragem()).asObject());
		col_dataM.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getDataManutencao()));
		col_dataC.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getDataCompra()));
		col_filial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFilial()));
		col_estado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado()));
		
		tabelaVeiculos.setItems(listaVeiculos);
		col_grupo.setSortType(TableColumn.SortType.ASCENDING);
		tabelaVeiculos.getSortOrder().add(col_grupo);
    }
    
    @FXML
    void maisInfoBotao(ActionEvent event) throws IOException {    	
    	main.showTelaCliente();
    }
    
    @FXML
    void processaPesquisarBotao(ActionEvent event) throws IOException {    	
    	pesquisarCliente();
    }
    
    @FXML
    void processaPesquisarEnter(ActionEvent event) throws IOException {    	
    	pesquisarCliente();
    }
    
    void showCliente() throws IOException{
    	tf_nome.setText(Contexto.getInstancia().getCliente().getNomeCliente());
		tf_cnh.setText(Contexto.getInstancia().getCliente().getCnh());
		clientePane.setVisible(clientePaneBool=true);
    }
    
    void pesquisarCliente() throws IOException {    	
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
    	if (!cpf.equals("") && con.ConsultaCliente("cpf",cpf)) {

    		showCliente();
    		
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
    	if (!passaporte.equals("") && con.ConsultaCliente("passaporte",passaporte)) {
    		//main.showTelaCliente();
    		showCliente();
    		
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
    void pesquisarVeiculoEnter(ActionEvent event) throws IOException {
    	pesquisarVeiculo();
    }

    @FXML
    void pesquisarVeiculoBotao(ActionEvent event) throws IOException {
    	pesquisarVeiculo();

    }
    
    void pesquisarVeiculo() throws IOException {
    	
    	String placa = tf_placa.getText();
    	
    	if (!placa.equals("") && con.ConsultaVeiculo(placa)) {
    		
    		main.showTelaVeiculo();

    		// Print de teste
    		System.out.println("placa: " + Contexto.getInstancia().getVeiculo().getPlaca());
    	}
    	
    	//se nao estiver
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erro");
    		alert.setHeaderText("Veiculo nao encontrado.");
    		alert.setContentText("Para cadastra-lo, clique em \"Cadastrar veiculo\".");
    		alert.showAndWait();
    		tf_cpfPassaporte.setText("");
    	}
    }
    
    @FXML
    void cadastrarVeiculo(ActionEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	Parent cadastroVeiculo = FXMLLoader.load(getClass().getResource("CadastroVeiculo.fxml"));
    	Scene scene = new Scene(cadastroVeiculo);
    	
    	stage.setTitle("Cadastro de veículo");
    	stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void cadastrarModelo(ActionEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	Parent cadastroModelo = FXMLLoader.load(getClass().getResource("CadastroModelo.fxml"));
    	Scene scene = new Scene(cadastroModelo);
    	
    	stage.setTitle("Cadastro de modelo de veículo");
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
