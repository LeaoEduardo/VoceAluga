package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.Contexto;
import application.Main;
import application.dao.CarroDAO;
import application.dao.ClienteDAO;
import application.dao.EstadoCarroDAO;
import application.dao.FilialDAO;
import application.dao.ModeloCarroDAO;
import application.entity.Carro;
import application.entity.Cliente;
import application.entity.EstadoCarro;
import application.entity.Filial;
import application.entity.ModeloCarro;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
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

public class CadastroVeiculoController {

	private Main main = new Main();

	@FXML
	private Button botaoCancelar;

	@FXML
	private TextField tf_quilometragem;

	@FXML
	private ChoiceBox<String> cb_modelo;

	@FXML
	private ChoiceBox<String> cb_marca;

	@FXML
	private Button botaoCadastrarVeiculo;

	@FXML
	private DatePicker dp_dataManutencao;

	@FXML
	private DatePicker dp_dataCompra;

	@FXML
	private TextField tf_placa;

	@FXML
	private ChoiceBox<String> cb_filial;

	@FXML
	void initialize() {

		cb_filial.setItems(Filial.getAllFilialNomes());
		cb_marca.setItems(ModeloCarro.getAllMarcas());

		ReadOnlyObjectProperty<String> property = cb_marca.getSelectionModel().selectedItemProperty();
		property.addListener((ObservableValue<? extends String> observable, String oldValue,
				String newValue) -> atualizaModelos(newValue));

		tf_quilometragem.setTextFormatter(Contexto.getInstancia().getFormatador().Quilometragem());
	}

	void atualizaModelos(String marca) {

		ObservableList<String> nome_modelos = FXCollections.observableArrayList();
		ArrayList<ModeloCarro> todos_modelos = (new ModeloCarroDAO()).findAll();
		for (ModeloCarro modelo_carro : todos_modelos) {
			if (modelo_carro.getMarca().equals(marca)) {
				nome_modelos.add(modelo_carro.getModelo());
			}
		}
		cb_modelo.setItems(nome_modelos);
	}

	@FXML
	void cadastrarVeiculo(ActionEvent event) throws IOException {

		Carro carro = new Carro();
		String nome_filial = cb_filial.getSelectionModel().getSelectedItem();
		String nome_estado = "Disponível";
		String nome_modelo = cb_modelo.getSelectionModel().getSelectedItem();
		
		Filial filial = null;
		EstadoCarro estado_carro = (new EstadoCarroDAO()).find("tipo", nome_estado).get(0);
		ModeloCarro modelo_carro = null;
		if(!cb_filial.getSelectionModel().isEmpty()) {
			filial = (new FilialDAO()).find("nomeFilial", nome_filial).get(0);
			carro.setIdFilial(filial.getId());
		}
		else
			carro.setIdFilial(0);
		if(!cb_modelo.getSelectionModel().isEmpty()) {
			modelo_carro = (new ModeloCarroDAO()).find("modelo", nome_modelo).get(0);
			carro.setIdModelo(modelo_carro.getId());
		}
		else
			carro.setIdModelo(0);
		
		if(tf_quilometragem.getText().isEmpty())
			carro.setQuilometragem(-1);
		else
			carro.setQuilometragem(Integer.parseInt(tf_quilometragem.getText()));
		carro.setPlaca(tf_placa.getText());
		carro.setIdEstado(estado_carro.getId());
		carro.setDataCompra(dp_dataCompra.getValue());
		carro.setDataManutencao(dp_dataManutencao.getValue());
		
		String res = "";
		res = criarVeiculo(carro,res);

		if (!res.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Erro no cadastro.");
			alert.setContentText(res);
			alert.showAndWait();
		}

		else {			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Cadastro efetuado");
			alert.setContentText("Veículo cadastrado com sucesso.");
			alert.showAndWait();
			Stage stage = (Stage) botaoCadastrarVeiculo.getScene().getWindow();
			stage.close();
			Contexto.getInstancia().setVoltandoParaVeiculos(true);
			main.showTelaPrincipal();
		}
	}
	
	public String criarVeiculo(Carro carro,String res) {
		//"Todos os campos devem ser preenchidos!"
		if(carro.getPlaca() == null || carro.getPlaca().trim().isEmpty()) return res = "Falta a Placa";
		if(carro.getIdModelo() == 0) return res = "Falta o Modelo";
		if(carro.getDataCompra() == null) return res = "Falta a Data de Compra";
		if(carro.getDataManutencao() == null) return res = "Falta a Data de Manutenção";
		if(carro.getIdFilial() == 0) return res = "Falta a Filial";
		if(carro.getIdEstado() == 0) return res = "Falta o Estado";
		if(carro.getQuilometragem() == -1) return res = "Falta a Quilometragem";
		
		if((new CarroDAO()).find("placa", carro.getPlaca()).size() > 0) return res = "Carro ja existe";
				
		boolean cadastrou = (new CarroDAO()).insert(carro);
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