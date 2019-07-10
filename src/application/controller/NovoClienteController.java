package application.controller;

import application.Contexto;
import application.FormatadorTexto;
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
		property.addListener((value, oldValue, newValue) -> tf_cpfPassaporte
				.setTextFormatter(formatador.CpfPassaporte((Integer) newValue, tf_cpfPassaporte)));
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
		if (cb_cpfPassaporte.getSelectionModel().getSelectedIndex() == 0) {
			cliente.setCpf(tf_cpfPassaporte.getText());
		}
		// se nao, selecionou passaporte
		else {
			cliente.setPassaporte(tf_cpfPassaporte.getText());
		}
		cliente.setDataNasc(tf_dataNasc.getValue());
		cliente.setDataCnh(tf_validadeCNH.getValue());

		String res = "";
		res = criarCliente(cliente,res);
		// Se estiver tudo certo
		if (res.equals("")) {
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
		else {
			// Se algum campo estiver vazio
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Erro no cadastro.");
			alert.setContentText(res);
			alert.showAndWait();
		}
	}
	
	public String criarCliente(Cliente cliente,String res) {
		//"Todos os campos devem ser preenchidos!"
		if(cliente.getNome() == null || cliente.getNome().trim().isEmpty()) return res = "Falta o Nome";
		if((cliente.getPassaporte() == null || cliente.getPassaporte().trim().isEmpty()) && 
				(cliente.getCpf() == null || cliente.getCpf().trim().isEmpty())) return res = "Falta o Passaporte ou o Cpf";
		if(cliente.getDataNasc() == null) return res = "Falta a Data de Nascimento";
		if(cliente.getNacionalidade() == null || cliente.getNacionalidade().trim().isEmpty()) return res = "Falta o Nacionalidade";
		if(cliente.getTelefone() == null || cliente.getTelefone().trim().isEmpty()) return res = "Falta o Telefone";
		if(cliente.getCnh() == null || cliente.getCnh().trim().isEmpty()) return res = "Falta o CNH";
		if(cliente.getDataCnh() == null) return res = "Falta a Data do CNH";
		
		boolean cadastrou = (new ClienteDAO()).insert(cliente);
		if(cadastrou)
			return "";
		else
			return "Erro Inesperado";
	}

	@FXML
	void cancelar(ActionEvent event) {

		Stage stage = (Stage) botaoCancelar.getScene().getWindow();
		stage.close();
	}
}