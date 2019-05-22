package application;

import java.io.IOException;
import java.time.LocalDate;

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
    	
    	ObservableList<String> estados = FXCollections.observableArrayList("Disponivel", "Alugado", "Em manutenção", "Vendido");
        cb_estado.setItems(estados);
        cb_estado.getSelectionModel().select(Contexto.getInstancia().getVeiculo().getEstado());
        tf_quilometragem.setTextFormatter(Contexto.getInstancia().getFormatador().Quilometragem());
        
        placa = Contexto.getInstancia().getVeiculo().getPlaca();
        marca = Contexto.getInstancia().getVeiculo().getMarca();
        modelo = Contexto.getInstancia().getVeiculo().getModelo();
        quilometragem = Contexto.getInstancia().getVeiculo().getQuilometragem();
        dataCompra = Contexto.getInstancia().getVeiculo().getDataCompra();
        dataManutencao = Contexto.getInstancia().getVeiculo().getDataManutencao();
        filial = Contexto.getInstancia().getVeiculo().getFilial();
        
    	ConnectionSQL con = new ConnectionSQL();
    	
    	boolean consultou = con.ConsultaFiliais();
    	
    	if (consultou) {
    		
    		ObservableList<String> listaFiliais = Contexto.getInstancia().getListaFiliais();
    		cb_filial.setItems(listaFiliais);
    	}
    	
    	else {
    		System.out.println("Erro na consulta de filiais!");
    	}
    	
    	consultou = con.ConsultaMarcas();
    	
    	if (consultou) {
    		
    		ObservableList<String> listaMarcas = Contexto.getInstancia().getListaMarcas();
    		cb_marca.setItems(listaMarcas);
    		ReadOnlyObjectProperty<String> property = cb_marca.getSelectionModel().selectedItemProperty();
    		property.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> atualizaModelos(newValue));
    	}
    	
    	else {
    		System.out.println("Erro na consulta de marca!");
    	}
    	
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
    	
    	ConnectionSQL con = new ConnectionSQL();
    	
    	boolean consultou = con.ConsultaModelosDaMarca(marca);
    	
    	if (consultou) {
    		
    		ObservableList<String> listaModelosDaMarca = Contexto.getInstancia().getListaModelosDaMarca();
    		cb_modelo.setItems(listaModelosDaMarca);
    	}
    	
    	else {
    		System.out.println("Erro na consulta de modelos!");
    	}
    }
    
    @FXML
    void atualizarVeiculo(ActionEvent event) throws IOException {
    	
    	ConnectionSQL con = new ConnectionSQL();
    	
    	int id = Contexto.getInstancia().getVeiculo().getId();
    	String placa = tf_placa.getText();
    	String marca = cb_marca.getSelectionModel().getSelectedItem();
    	String modelo = cb_modelo.getSelectionModel().getSelectedItem();
    	String grupo = con.ConsultaGrupo(modelo);
    	Integer quilometragem = Integer.parseInt(tf_quilometragem.getText());
    	String filial = cb_filial.getSelectionModel().getSelectedItem();
    	String estado = cb_estado.getSelectionModel().getSelectedItem();
    	
    	if (placa.equals("") || quilometragem == null || dp_dataCompra.getValue() == null || dp_dataManutencao.getValue() == null) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Erro");
        	alert.setHeaderText("Erro no cadastro.");
        	alert.setContentText("Todos os campos devem ser preenchidos!");
        	alert.showAndWait();
    	}
    	
    	else {
    		
    		LocalDate dataCompra = dp_dataCompra.getValue();
        	LocalDate dataManutencao = dp_dataManutencao.getValue();
        	
        	boolean atualizou = con.UpdateVeiculo(placa, quilometragem, marca, modelo, filial, estado, dataCompra, dataManutencao);
        	
        	if (atualizou) {
                
                Contexto.getInstancia().setVeiculo(id, placa, dataManutencao, dataCompra, quilometragem, marca, modelo, grupo, filial, estado);
            	
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
            	alert.setHeaderText("Erro no cadastro.");
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