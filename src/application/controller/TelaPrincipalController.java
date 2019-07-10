package application.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import application.Contexto;
import application.FormatadorTexto;
import application.Main;
import application.dao.CarroDAO;
import application.dao.ClienteDAO;
import application.dao.FilialDAO;
import application.dao.LocacaoDAO;
import application.dao.ReservaDAO;
import application.dao.TipoFuncionarioDAO;
import application.entity.Filial;
import application.entity.Funcionario;
import application.entity.Locacao;
import application.entity.Reserva;
import application.entity.TipoFuncionario;
import application.entity.Carro;
import application.entity.Cliente;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
	private TextField tf_cpfPassaporteL;

	@FXML
	private ChoiceBox<String> cb_cpfPassaporteL;

	@FXML
	private TabPane telaPrincipalTabPane;
	
	@FXML
    private Tab abaReserva;

    @FXML
    private Tab abaLocacao;
	
	@FXML
    private Tab abaDevolucao;

	@FXML
	private Tab abaVeiculos;

	@FXML
	private Tab abaFuncionarios;

	@FXML
	private AnchorPane clientePane;
	boolean clientePaneBool = false;
	
	@FXML
	private AnchorPane locacaoPane;
	boolean locacaoPaneBool = false;

	@FXML
	private Text tf_nome;

	@FXML
	private Text tf_cnh;
	
	@FXML
	private Text tf_nomeL;

	@FXML
	private Text tf_cnhL;

	@FXML
	private TextField tf_placa;

	@FXML
	private Button	btn_cancelar_reserva;
	
	@FXML
	private TableView<Reserva> tabelaReservas;

	@FXML
	private TableView<Carro> tabelaVeiculos;

	@FXML
	private TableColumn<Carro, String> col_grupo;

	@FXML
	private TableColumn<Carro, String> col_marca;

	@FXML
	private TableColumn<Carro, String> col_modelo;

	@FXML
	private TableColumn<Carro, String> col_placa;

	@FXML
	private TableColumn<Carro, Integer> col_quilometragem;

	@FXML
	private TableColumn<Carro, LocalDate> col_dataM;

	@FXML
	private TableColumn<Carro, LocalDate> col_dataC;
	
	@FXML
	private TableColumn<Carro, String> col_filial;

	@FXML
	private TableColumn<Carro, String> col_estado;
	
	@FXML
	private TableView<Carro> tabelaVeiculosL;

	@FXML
	private TableColumn<Carro, String> col_grupoL;

	@FXML
	private TableColumn<Carro, String> col_marcaL;

	@FXML
	private TableColumn<Carro, String> col_modeloL;

	@FXML
	private TableColumn<Carro, String> col_placaL;

	@FXML
	private TableColumn<Carro, Integer> col_quilometragemL;

	@FXML
	private TableColumn<Carro, LocalDate> col_dataML;

	@FXML
	private TableColumn<Carro, LocalDate> col_dataCL;
	

	private ObservableList<Carro> listaCarro = FXCollections.observableArrayList();
	private ObservableList<Carro> listaCarrosDisponiveis = FXCollections.observableArrayList();

	@FXML
	void initialize() throws IOException {

		ObservableList<String> escolhas = FXCollections.observableArrayList("Por CPF", "Por passaporte");
		cb_cpfPassaporte.setItems(escolhas);
		ReadOnlyIntegerProperty property = cb_cpfPassaporte.getSelectionModel().selectedIndexProperty();
		FormatadorTexto formatador = Contexto.getInstancia().getFormatador();
		property.addListener((value, oldValue, newValue) -> tf_cpfPassaporte
				.setTextFormatter(formatador.CpfPassaporte((Integer) newValue, tf_cpfPassaporte)));
		cb_cpfPassaporte.getSelectionModel().selectFirst();
		
		cb_cpfPassaporteL.setItems(escolhas);
		ReadOnlyIntegerProperty propertyL = cb_cpfPassaporteL.getSelectionModel().selectedIndexProperty();
		FormatadorTexto formatadorL = Contexto.getInstancia().getFormatador();
		propertyL.addListener((value, oldValue, newValue) -> tf_cpfPassaporteL
				.setTextFormatter(formatadorL.CpfPassaporte((Integer) newValue, tf_cpfPassaporteL)));
		cb_cpfPassaporteL.getSelectionModel().selectFirst();

		Funcionario funcionario = Contexto.getInstancia().getFuncionario();
		TipoFuncionario tipo_funcionario = (new TipoFuncionarioDAO()).find(funcionario.getIdTipo());
		Filial filial = (new FilialDAO()).find(funcionario.getIdFilial());

		tf_username.setText(funcionario.getUsuario());
		tf_nivel.setText(tipo_funcionario.getNome());
		tf_filial.setText(filial.getNome());

		clientePane.setVisible(clientePaneBool);
		locacaoPane.setVisible(locacaoPaneBool);

		//Fazendo o botão de cancelar reserva ficar desativado quando nenhuma reserva estiver selecionada
		btn_cancelar_reserva.setDisable( true );
		tabelaReservas.addEventHandler( MouseEvent.ANY , event -> {
			btn_cancelar_reserva.setDisable( tabelaReservas.getSelectionModel().getSelectedItem() == null );
		});
		
		if (Contexto.getInstancia().getVoltandoParaCliente()) {
			
			telaPrincipalTabPane.getSelectionModel().select(abaReserva);
			String cpf = Contexto.getInstancia().getCliente().getCpf();
			if (cpf != null) {
				cb_cpfPassaporte.getSelectionModel().select(0);
				tf_cpfPassaporte.setText(Contexto.getInstancia().getCliente().getCpf());
				pesquisaPorCpf(cpf);
			}
			else {
				String passaporte = Contexto.getInstancia().getCliente().getPassaporte();
				cb_cpfPassaporte.getSelectionModel().select(1);
				tf_cpfPassaporte.setText(passaporte);
				pesquisaPorPassaporte(passaporte);
			}
			Contexto.getInstancia().setVoltandoParaCliente(false);
		}
		
		if (Contexto.getInstancia().getVoltandoParaLocacao()) {
			
			telaPrincipalTabPane.getSelectionModel().select(abaLocacao);
			String cpf = Contexto.getInstancia().getCliente().getCpf();
			if (cpf != null) {
				cb_cpfPassaporteL.getSelectionModel().select(0);
				tf_cpfPassaporteL.setText(Contexto.getInstancia().getCliente().getCpf());
				pesquisaPorCpf(cpf);
			}
			else {
				String passaporte = Contexto.getInstancia().getCliente().getPassaporte();
				cb_cpfPassaporteL.getSelectionModel().select(1);
				tf_cpfPassaporteL.setText(passaporte);
				pesquisaPorPassaporte(passaporte);
			}
			Contexto.getInstancia().setVoltandoParaLocacao(false);
		}

		telaPrincipalTabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			if (newTab == abaVeiculos)
				carregaVeiculos();
		});

		ReadOnlyIntegerProperty veiculoProperty = tabelaVeiculos.getSelectionModel().selectedIndexProperty();
		veiculoProperty.addListener((value, oldValue, newValue) -> tf_placa
				.setText(tabelaVeiculos.getSelectionModel().getSelectedItem().getPlaca()));

		if (Contexto.getInstancia().getVoltandoParaVeiculos()) {

			telaPrincipalTabPane.getSelectionModel().select(abaVeiculos);
			Contexto.getInstancia().setVoltandoParaVeiculos(false);
		}
		
		if (Contexto.getInstancia().getVoltandoParaDevolucao()) {
			
			telaPrincipalTabPane.getSelectionModel().select(abaDevolucao);
			Contexto.getInstancia().setVoltandoParaDevolucao(false);
		}
	}

	void carregaVeiculos() {

		// Preenche a tabela de veiculos com todos os veiculos no BD

		listaCarro = FXCollections.observableArrayList();
		ArrayList<Carro> all_carros = (new CarroDAO()).findAll();
		for (int i = 0; i < all_carros.size(); i++) {
			listaCarro.add(all_carros.get(i));
		}
		Contexto.getInstancia().setListaVeiculos(listaCarro);

		col_grupo.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getModeloCarro().getGrupoCarro().getGrupo()));
		col_marca.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getModeloCarro().getMarca()));
		col_modelo.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getModeloCarro().getModelo()));
		col_placa.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getPlaca()));
		col_quilometragem.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getQuilometragem()).asObject());
		col_dataM.setCellValueFactory(
				cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getDataManutencao()));
		col_dataC.setCellValueFactory(
				cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getDataCompra()));
		col_filial.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getFilial().getNome()));
		col_estado.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getEstadoCarro().getTipo()));

		tabelaVeiculos.setItems(listaCarro);
		col_grupo.setSortType(TableColumn.SortType.ASCENDING);
		col_estado.setSortType(TableColumn.SortType.ASCENDING);
		tabelaVeiculos.getSortOrder().add(col_grupo);
	}
	
	void carregaVeiculosLocacao() {
		
		listaCarrosDisponiveis = FXCollections.observableArrayList();
		ArrayList<Carro> carrosDisponiveis = Contexto.getInstancia().getFuncionario().getFilial().getCarrosDisponiveis(null);
		for (int i = 0; i < carrosDisponiveis.size(); i++) {
			listaCarrosDisponiveis.add(carrosDisponiveis.get(i));
		}
		
		col_grupoL.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getModeloCarro().getGrupoCarro().getGrupo()));
		col_marcaL.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getModeloCarro().getMarca()));
		col_modeloL.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getModeloCarro().getModelo()));
		col_placaL.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getPlaca()));
		col_quilometragemL.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getQuilometragem()).asObject());
		col_dataML.setCellValueFactory(
				cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getDataManutencao()));
		col_dataCL.setCellValueFactory(
				cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getDataCompra()));
		
		tabelaVeiculosL.setItems(listaCarrosDisponiveis);
		col_grupoL.setSortType(TableColumn.SortType.ASCENDING);
		tabelaVeiculosL.getSortOrder().add(col_grupoL);
		
	}

	@FXML
	void reservarVeiculo(ActionEvent event) throws IOException {
		// Abre a janela de reservar veículos
		Stage stage = new Stage();
		Parent reservaVeiculo = FXMLLoader.load(getClass().getResource("ReservarVeiculo.fxml"));
		Scene scene = new Scene(reservaVeiculo);

		stage.setTitle("Reserva de veículo");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void cancelarReserva(ActionEvent event) throws IOException {
		Reserva reserva = this.tabelaReservas.getSelectionModel().getSelectedItem();
		if( reserva != null ) {
			ReservaDAO reserva_dao = new ReservaDAO();
			reserva_dao.delete(reserva);
			carregaCliente( reserva.getCliente() );
		}
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

	@SuppressWarnings("unchecked")
	void carregaCliente(Cliente cliente) throws IOException {
		tf_nome.setText(cliente.getNome());
		tf_cnh.setText(cliente.getCnh());
		clientePane.setVisible(clientePaneBool = true);

		// Atualizar a lista de reservas
		ObservableList<Reserva> listaReservas =  FXCollections.observableArrayList();
		ArrayList<Reserva> arrayReservas = (new ReservaDAO()).findAll();
		for( Reserva r : arrayReservas ) {
			if( r.getIdCliente() == cliente.getId() ) {
				listaReservas.add(r);
			}
		}
		this.tabelaReservas.getSelectionModel().getSelectedItem();
		TableColumn<Reserva,String> coluna_reservas 	= (TableColumn<Reserva, String>) tabelaReservas.getColumns().get(0);
		TableColumn<Reserva,String> coluna_data_loc 	= (TableColumn<Reserva, String>) tabelaReservas.getColumns().get(1);
		TableColumn<Reserva,String> coluna_data_dev 	= (TableColumn<Reserva, String>) tabelaReservas.getColumns().get(2);
		TableColumn<Reserva,String> coluna_grupo 		= (TableColumn<Reserva, String>) tabelaReservas.getColumns().get(3);
		TableColumn<Reserva,String> coluna_modelo 		= (TableColumn<Reserva, String>) tabelaReservas.getColumns().get(4);
		
		coluna_reservas.setCellValueFactory(
				cellData -> new SimpleStringProperty( cellData.getValue().getEstadoReserva() ) ); // Falta dizer exatamente o estado da reserva
		coluna_data_loc.setCellValueFactory(
				cellData -> new SimpleStringProperty( cellData.getValue().getDataLocacao().toString() ) );
		coluna_data_dev.setCellValueFactory(
				cellData -> new SimpleStringProperty( cellData.getValue().getDataDevolucao().toString() ) );
		coluna_grupo.setCellValueFactory(
				cellData -> new SimpleStringProperty( cellData.getValue().getModeloReserva().getGrupoCarro().getGrupo() ) );
		coluna_modelo.setCellValueFactory(
				cellData -> new SimpleStringProperty( cellData.getValue().getModeloReserva().getModelo() ) );
		
		tabelaReservas.setItems( listaReservas );
		coluna_data_loc.setSortType( TableColumn.SortType.ASCENDING );
		tabelaReservas.getSortOrder().add( coluna_data_loc );
		
	}
	
	void carregaClienteLocacao(Cliente cliente) throws IOException {
		
		tf_nomeL.setText(cliente.getNome());
		tf_cnhL.setText(cliente.getCnh());
		locacaoPane.setVisible(locacaoPaneBool = true);
		carregaVeiculosLocacao();
	}

	void pesquisarCliente() throws IOException {
		Cliente cliente = null;
		
		if (telaPrincipalTabPane.getSelectionModel().getSelectedItem() == abaReserva) {
			
			if (cb_cpfPassaporte.getSelectionModel().getSelectedIndex() == 0) {
				String cpf = tf_cpfPassaporte.getText();
				cliente = pesquisaPorCpf(cpf);
			} else {
				String passaporte = tf_cpfPassaporte.getText();
				cliente = pesquisaPorPassaporte(passaporte);
			}
		}
		else if (cb_cpfPassaporteL.getSelectionModel().getSelectedIndex() == 0) {
			String cpf = tf_cpfPassaporteL.getText();
			cliente = pesquisaPorCpf(cpf);
		} else {
			String passaporte = tf_cpfPassaporteL.getText();
			cliente = pesquisaPorPassaporte(passaporte);
		}	
		if (cliente != null) {
			Contexto.getInstancia().setCliente(cliente);
		}
	}

	Cliente pesquisaPorCpf(String cpf) throws IOException {
		if (cpf == null || cpf == "")
			return null;

		ArrayList<Cliente> todos_clientes = (new ClienteDAO()).findAll();
		Cliente cliente = null;
		for (int i = 0; i < todos_clientes.size(); i++) {
			String cpf2 = todos_clientes.get(i).getCpf();
			if (cpf2 != null && cpf2.equals(cpf)) {
				cliente = todos_clientes.get(i);
				break;
			}
		}

		if (cliente != null) {
			
			if (telaPrincipalTabPane.getSelectionModel().getSelectedItem() == abaReserva)
				carregaCliente(cliente);
			else
				carregaClienteLocacao(cliente);

			System.out.println("cpf: " + cliente.getCpf());
			System.out.println("passaporte: " + cliente.getPassaporte());
		}

		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Cliente nao encontrado.");
			alert.setContentText("Para cadastra-lo, clique em \"Novo cliente\".");
			alert.showAndWait();
			tf_cpfPassaporte.setText("");
		}
		return cliente;
	}

	Cliente pesquisaPorPassaporte(String passaporte) throws IOException {

		ArrayList<Cliente> todos_clientes = (new ClienteDAO()).findAll();
		Cliente cliente = null;
		for (int i = 0; i < todos_clientes.size(); i++) {
			String passaporte2 = todos_clientes.get(i).getPassaporte();
			if (passaporte2 != null && passaporte2.equals(passaporte)) {
				cliente = todos_clientes.get(i);
				break;
			}
		}

		if (cliente != null) {

			// main.showTelaCliente();
			if (telaPrincipalTabPane.getSelectionModel().getSelectedItem() == abaReserva)
				carregaCliente(cliente);
			else
				carregaClienteLocacao(cliente);

			System.out.println("cpf: " + cliente.getCpf());
			System.out.println("passaporte: " + cliente.getPassaporte());
		}

		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Cliente nao encontrado.");
			alert.setContentText("Para cadastra-lo, clique em \"Novo cliente\".");
			alert.showAndWait();
			tf_cpfPassaporte.setText("");
		}
		return cliente;
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
    void alugarVeiculo(ActionEvent event) throws IOException {
		
		Carro escolhido = tabelaVeiculosL.getSelectionModel().getSelectedItem();
		
		ArrayList<Locacao> locacao_dao = (new LocacaoDAO()).find("id_cliente", 
				Integer.toString(Contexto.getInstancia().getCliente().getId()));
		boolean ReservaExist = false;  
		if(locacao_dao.size() > 0) {
			for (int i = 0; i < locacao_dao.size(); i++) {
				if(!locacao_dao.get(i).isDevolvido()) {
					ReservaExist = true;
					break;
				}
					
			}
		}
		if(!ReservaExist) {
			if ( escolhido == null ) {				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText("Nenhum veiculo selecionado.");
				alert.setContentText("Selecione um veiculo.");
				alert.showAndWait();
			}			
			else {			
				Contexto.getInstancia().setVeiculo(escolhido);
				
				// Prints de teste
				System.out.println(escolhido.getModeloCarro().getGrupoCarro().getGrupo());
				System.out.println(escolhido.getPlaca());
				System.out.println(escolhido.getModeloCarro().getMarca());
				System.out.println(escolhido.getModeloCarro().getModelo());
				System.out.println(escolhido.getQuilometragem());
				
				Stage stage = new Stage();
				Parent alugarVeiculo = FXMLLoader.load(getClass().getResource("AlugarVeiculo.fxml"));
				Scene scene = new Scene(alugarVeiculo);
		
				stage.setTitle("Aluguel de veículo");
				stage.setScene(scene);
				stage.show();
			}
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Cliente.");
			alert.setContentText("Cliente ja tem um carro alugado");
			alert.showAndWait();
		}
    }

	@FXML
	void cadastrarFuncionario(ActionEvent event) throws IOException {

		// Abre a janela de cadastro de novo funcionário
		Stage stage = new Stage();
		Parent novoFuncionario = FXMLLoader.load(getClass().getResource("CadastroFuncionario.fxml"));
		Scene scene = new Scene(novoFuncionario);

		stage.setTitle("Cadastro de funcionário");
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
		
		String placa;
		
		if (tabelaVeiculos.getSelectionModel().getSelectedItem() == null)
			placa = tf_placa.getText();
		else
			placa = tabelaVeiculos.getSelectionModel().getSelectedItem().getPlaca();
		
		ArrayList<Carro> todos_carros = (new CarroDAO()).findAll();
		Carro carro = null;
		for (int i = 0; i < todos_carros.size(); i++) {
			if (todos_carros.get(i).getPlaca().equals(placa)) {
				carro = todos_carros.get(i);
				break;
			}
		}

		if (carro != null) {
			
			Contexto.getInstancia().setVeiculo(carro);
			main.showTelaVeiculo();

			// Print de teste
			System.out.println("placa: " + Contexto.getInstancia().getVeiculo().getPlaca());
		}

		// se nao estiver
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
    void enviarParaManutencao(ActionEvent event) throws IOException {
		
		Carro carro = tabelaVeiculos.getSelectionModel().getSelectedItem();
		
		if (carro != null) {
		
			if (carro.getEstadoCarro().getId() == 3 ) {
				
				Contexto.getInstancia().setVeiculo(carro);
				
				Stage stage = new Stage();
				Parent telaManutencao = FXMLLoader.load(getClass().getResource("TelaManutencao.fxml"));
				Scene scene = new Scene(telaManutencao);
	
				stage.setTitle("Enviar veículo para oficina");
				stage.setScene(scene);
				stage.show();
			}
			
			else {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText("Veiculo nao esta disponivel");
				alert.setContentText("O estado atual do veiculo é: " + carro.getEstadoCarro().getTipo() + ".");
				alert.showAndWait();
			}
		}
		
		else {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Nenhum veiculo selecionado");
			alert.setContentText("Por favor selecione um veiculo da lista.");
			alert.showAndWait();
		}
    }
	
	@FXML
    void maisInfoVeiculo(ActionEvent event) throws IOException {
		
		Carro carro = tabelaVeiculos.getSelectionModel().getSelectedItem();
		
		if (carro != null) {
			
			pesquisarVeiculo();
		}
		
		else {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Nenhum veiculo selecionado");
			alert.setContentText("Por favor selecione um veiculo da lista.");
			alert.showAndWait();
		}
    }

	@FXML
	void sair(ActionEvent event) throws IOException {
		main.showLoginFuncionario();
	}
}
