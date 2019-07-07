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
import application.dao.ReservaDAO;
import application.dao.TipoFuncionarioDAO;
import application.entity.Filial;
import application.entity.Funcionario;
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


	private ObservableList<Carro> listaCarro = FXCollections.observableArrayList();

	@FXML
	void initialize() {

		ObservableList<String> escolhas = FXCollections.observableArrayList("Por CPF", "Por passaporte");
		cb_cpfPassaporte.setItems(escolhas);
		ReadOnlyIntegerProperty property = cb_cpfPassaporte.getSelectionModel().selectedIndexProperty();
		FormatadorTexto formatador = Contexto.getInstancia().getFormatador();
		property.addListener((value, oldValue, newValue) -> tf_cpfPassaporte
				.setTextFormatter(formatador.CpfPassaporte((Integer) newValue, tf_cpfPassaporte)));
		cb_cpfPassaporte.getSelectionModel().selectFirst();

		Funcionario funcionario = Contexto.getInstancia().getFuncionario();
		TipoFuncionario tipo_funcionario = (new TipoFuncionarioDAO()).find(funcionario.getIdTipo());
		Filial filial = (new FilialDAO()).find(funcionario.getIdFilial());

		tf_username.setText(funcionario.getUsuario());
		tf_nivel.setText(tipo_funcionario.getNome());
		tf_filial.setText(filial.getNome());

		clientePane.setVisible(clientePaneBool);

		if (funcionario.getIdTipo() != 1) {

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
		col_placa.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlaca()));
		col_quilometragem.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getQuilometragem()).asObject());
		col_dataM.setCellValueFactory(
				cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getDataManutencao()));
		col_dataC.setCellValueFactory(
				cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getDataCompra()));
		col_filial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFilial().getNome()));
		col_estado.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getEstadoCarro().getTipo()));

		tabelaVeiculos.setItems(listaCarro);
		col_grupo.setSortType(TableColumn.SortType.ASCENDING);
		tabelaVeiculos.getSortOrder().add(col_grupo);
	}

	@FXML
	void reservarVeiculo(ActionEvent event) throws IOException {
		// Abre a janela de reservar veículos
		Stage stage = new Stage();
		Parent reservaVeiculo = FXMLLoader.load(getClass().getResource("../ReservarVeiculo.fxml"));
		Scene scene = new Scene(reservaVeiculo);

		stage.setTitle("Reserva de veículo");
		stage.setScene(scene);
		stage.show();
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

		TableColumn<Reserva,String> coluna_reservas 	= (TableColumn<Reserva, String>) tabelaReservas.getColumns().get(0);
		TableColumn<Reserva,String> coluna_data_loc 	= (TableColumn<Reserva, String>) tabelaReservas.getColumns().get(1);
		TableColumn<Reserva,String> coluna_data_dev 	= (TableColumn<Reserva, String>) tabelaReservas.getColumns().get(2);
		TableColumn<Reserva,String> coluna_grupo 		= (TableColumn<Reserva, String>) tabelaReservas.getColumns().get(3);
		TableColumn<Reserva,String> coluna_modelo 		= (TableColumn<Reserva, String>) tabelaReservas.getColumns().get(4);
		
		coluna_reservas.setCellValueFactory(
				cellData -> new SimpleStringProperty( "Reservado" ) ); // Falta dizer exatamente o estado da reserva
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
		
		/**
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
		col_placa.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlaca()));
		col_quilometragem.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getQuilometragem()).asObject());
		col_dataM.setCellValueFactory(
				cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getDataManutencao()));
		col_dataC.setCellValueFactory(
				cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getDataCompra()));
		col_filial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFilial().getNome()));
		col_estado.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getEstadoCarro().getTipo()));

		tabelaVeiculos.setItems(listaCarro);
		col_grupo.setSortType(TableColumn.SortType.ASCENDING);
		tabelaVeiculos.getSortOrder().add(col_grupo);
		**/
	}

	void pesquisarCliente() throws IOException {
		Cliente cliente = null;
		if (cb_cpfPassaporte.getSelectionModel().getSelectedIndex() == 0) {
			String cpf = tf_cpfPassaporte.getText();
			cliente = pesquisaPorCpf(cpf);
		} else {
			String passaporte = tf_cpfPassaporte.getText();
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

			carregaCliente(cliente);

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
			if (todos_clientes.get(i).getPassaporte() == passaporte) {
				cliente = todos_clientes.get(i);
				break;
			}
		}

		if (cliente != null) {

			// main.showTelaCliente();
			carregaCliente(cliente);

			System.out.println("cpf: " + Contexto.getInstancia().getCliente().getCpf());
			System.out.println("passaporte: " + Contexto.getInstancia().getCliente().getPassaporte());
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
		Parent novoCliente = FXMLLoader.load(getClass().getResource("../NovoCliente.fxml"));
		Scene scene = new Scene(novoCliente);

		stage.setTitle("Cadastro de cliente");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void cadastrarFuncionario(ActionEvent event) throws IOException {

		// Abre a janela de cadastro de novo funcionário
		Stage stage = new Stage();
		Parent novoFuncionario = FXMLLoader.load(getClass().getResource("../CadastroFuncionario.fxml"));
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

		String placa = tf_placa.getText();

		ArrayList<Carro> todos_carros = (new CarroDAO()).findAll();
		Carro carro = null;
		for (int i = 0; i < todos_carros.size(); i++) {
			if (todos_carros.get(i).getPlaca() == placa) {
				carro = todos_carros.get(i);
				break;
			}
		}

		if (carro != null) {

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
		Parent cadastroVeiculo = FXMLLoader.load(getClass().getResource("../CadastroVeiculo.fxml"));
		Scene scene = new Scene(cadastroVeiculo);

		stage.setTitle("Cadastro de veículo");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void cadastrarModelo(ActionEvent event) throws IOException {

		Stage stage = new Stage();
		Parent cadastroModelo = FXMLLoader.load(getClass().getResource("../CadastroModelo.fxml"));
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

		// Aqui serao feitas as consultas gerais, e talvez o acesso ao financeiro da
		// empresa
	}

	@FXML
	void sair(ActionEvent event) throws IOException {
		main.showLoginFuncionario();
	}
}
