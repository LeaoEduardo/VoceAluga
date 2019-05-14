package application;

import java.time.LocalDate;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroVeiculoController {

    @FXML
    private Button botaoCancelar;

    @FXML
    private TextField tf_quilometragem;
    
    @FXML
    private TextField tf_marca;

    @FXML
    private TextField tf_modelo;

    @FXML
    private Button botaoCadastrarCliente;

    @FXML
    private DatePicker dp_dataManutencao;

    @FXML
    private DatePicker dp_dataCompra;

    @FXML
    private TextField tf_placa;

    @FXML
    private ChoiceBox<String> cb_grupo;

    @FXML
    private TextField tf_filial;

    
    @FXML
    public void initialize() {
    	
    	ObservableList<String> escolhas = FXCollections.observableArrayList("A", "B", "C"); 
        cb_grupo.setItems(escolhas);
    }
    
    @FXML
    void cadastrarVeiculo(ActionEvent event) {
    	
    	ConnectionSQL con = new ConnectionSQL();
    	
    	String placa = tf_placa.getText();
    	String marca = tf_marca.getText();
    	String modelo = tf_placa.getText();
    	String grupo = cb_grupo.getSelectionModel().getSelectedItem();
    	String quilometragem = tf_quilometragem.getText();
    	LocalDate dataCompra = dp_dataCompra.getValue();
    	LocalDate dataManutencao = dp_dataManutencao.getValue();
    	String filial = tf_filial.getText();
    	String estado = "Disponível";
    	
    	con.CadastraVeiculo(placa, quilometragem, marca, modelo, filial, estado, dataCompra, dataManutencao);
    }

    @FXML
    void cancelar(ActionEvent event) {
    	
    	Stage stage = (Stage) botaoCancelar.getScene().getWindow();
        stage.close();
    }
}
