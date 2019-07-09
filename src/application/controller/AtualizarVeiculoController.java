package application.controller;

import java.io.IOException;
import java.time.LocalDate;

import application.Contexto;
import application.Main;
import application.dao.CarroDAO;
import application.dao.EstadoCarroDAO;
import application.dao.FilialDAO;
import application.dao.ModeloCarroDAO;
import application.entity.Carro;
import application.entity.Filial;
import application.entity.ModeloCarro;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
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

public class AtualizarVeiculoController {

	private Main main = new Main();

	@FXML
	private ChoiceBox<String> cb_marca;

	@FXML
	private Button botaoCancelar;

	@FXML
	private Button botaoAtualizarVeiculo;

	@FXML
	private TextField tf_quilometragem;

	@FXML
	private ChoiceBox<String> cb_modelo;

	@FXML
	private DatePicker dp_dataManutencao;

	@FXML
	private DatePicker dp_dataCompra;

	@FXML
	private ChoiceBox<String> cb_estado;

	@FXML
	private ChoiceBox<String> cb_filial;

	@FXML
	private TextField tf_placa;

	private String placa = new String();
	private String marca = new String();
	private String modelo = new String();
	private Integer quilometragem;
	private LocalDate dataCompra;
	private LocalDate dataManutencao;
	private String filial = new String();

	@FXML
	void initialize() {
		
		ObservableList<String> estados = FXCollections.observableArrayList("Disponivel", "Alugado", "Em manutenção",
				"Vendido");
		cb_estado.setItems(estados);
		cb_estado.getSelectionModel().select(Contexto.getInstancia().getVeiculo().getEstadoCarro().getTipo());
		tf_quilometragem.setTextFormatter(Contexto.getInstancia().getFormatador().Quilometragem());

		placa = Contexto.getInstancia().getVeiculo().getPlaca();
		marca = Contexto.getInstancia().getVeiculo().getModeloCarro().getMarca();
		modelo = Contexto.getInstancia().getVeiculo().getModeloCarro().getModelo();
		quilometragem = Contexto.getInstancia().getVeiculo().getQuilometragem();
		dataCompra = Contexto.getInstancia().getVeiculo().getDataCompra();
		dataManutencao = Contexto.getInstancia().getVeiculo().getDataManutencao();
		filial = Contexto.getInstancia().getVeiculo().getFilial().getNome();

		cb_filial.setItems(Filial.getAllFilialNomes());
		
		Contexto.getInstancia().setListaMarcas(ModeloCarro.getAllMarcas());
		ObservableList<String> listaMarcas = Contexto.getInstancia().getListaMarcas();
		cb_marca.setItems(listaMarcas);
		ReadOnlyObjectProperty<String> property = cb_marca.getSelectionModel().selectedItemProperty();
		property.addListener((ObservableValue<? extends String> observable, String oldValue,
				              String newValue) -> atualizaModelos(newValue));

		tf_quilometragem.setTextFormatter(Contexto.getInstancia().getFormatador().Quilometragem());
		tf_placa.setText(placa);
		cb_marca.getSelectionModel().select(marca);
		cb_modelo.getSelectionModel().select(modelo);
		tf_quilometragem.setText(quilometragem.toString());
		dp_dataCompra.setValue(dataCompra);
		dp_dataManutencao.setValue(dataManutencao);
		cb_filial.setValue(filial);
	}

	void atualizaModelos(String marca) {
		cb_modelo.setItems(ModeloCarro.getAllModelosDaMarca(marca));
	}

	@FXML
	void atualizarVeiculo(ActionEvent event) throws IOException {

		String placa = tf_placa.getText();
		Integer quilometragem = Integer.parseInt(tf_quilometragem.getText());
		String modelo = cb_modelo.getSelectionModel().getSelectedItem();
		String filial = cb_filial.getSelectionModel().getSelectedItem();
		String estado = cb_estado.getSelectionModel().getSelectedItem();

		if (placa.equals("") || quilometragem == null || dp_dataCompra.getValue() == null
				|| dp_dataManutencao.getValue() == null) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Erro no cadastro.");
			alert.setContentText("Todos os campos devem ser preenchidos!");
			alert.showAndWait();
		}

		else {

			LocalDate dataCompra = dp_dataCompra.getValue();
			LocalDate dataManutencao = dp_dataManutencao.getValue();

			Carro carro = Contexto.getInstancia().getVeiculo();
			carro.setPlaca(placa);
			carro.setIdModelo((new ModeloCarroDAO()).find("modelo", modelo).get(0).getId());
			carro.setIdFilial((new FilialDAO()).find("nomeFilial", filial).get(0).getId());
			carro.setIdEstado((new EstadoCarroDAO()).find("tipo", estado).get(0).getId());
			carro.setDataCompra(dataCompra);
			carro.setDataManutencao(dataManutencao);
			carro.setQuilometragem(quilometragem);

			boolean atualizou = (new CarroDAO()).update(carro);

			if (atualizou) {

				Contexto.getInstancia().setVeiculo(carro);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Cadastro alterado");
				alert.setContentText("Veiculo atualizado com sucesso.");
				alert.showAndWait();
				Stage stage = (Stage) botaoAtualizarVeiculo.getScene().getWindow();
				stage.close();
				main.showTelaVeiculo();
			}

			else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText("Erro no cadastro");
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