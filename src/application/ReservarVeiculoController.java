package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import application.dao.GrupoCarroDAO;
import application.dao.ModeloCarroDAO;
import application.entity.GrupoCarro;
import application.entity.ModeloCarro;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReservarVeiculoController {

    @FXML
    private Button cancel_button;

    @FXML
    private TextField devolution_hour_text;

    @FXML
    private TextField devolution_minute_text;

    @FXML
    private Button confirm_button;

    @FXML
    private ChoiceBox<String> car_group_choice_box;

    @FXML
    private ChoiceBox<String> car_model_choice_box;

    @FXML
    private DatePicker devolution_date_picker;

    @FXML
    private CheckBox choose_model_checkbox;

    @FXML
    private TextField rent_minute_text;

    @FXML
    private DatePicker rent_date_picker;

    @FXML
    private TextField rent_hour_text;


    @FXML
    public void initialize() {
    	ArrayList<GrupoCarro> array_grupo_carro = (new GrupoCarroDAO()).findAll();
    	ObservableList<String> grupos_carro = FXCollections.observableArrayList();
    	for( GrupoCarro g : array_grupo_carro ) {
    		grupos_carro.add( g.getGrupo() );
    	}
    	car_group_choice_box.setItems( grupos_carro );
    	atualizaModelos( car_group_choice_box.getValue() );
    	
    	ReadOnlyObjectProperty<String> property = car_group_choice_box.getSelectionModel().selectedItemProperty();
    	property.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> atualizaModelos(newValue));
    }
    
    void atualizaModelos(  String grupo_carro_selecionado ) {
    	if( grupo_carro_selecionado != null && grupo_carro_selecionado.length() > 0  ) {
	    	int id_grupo_carro_selecionado = (new GrupoCarroDAO()).find("grupo", grupo_carro_selecionado).get(0).getId();
	    	ArrayList<ModeloCarro> array_modelos_carro = (new ModeloCarroDAO()).find("idGrupo",String.valueOf(id_grupo_carro_selecionado));
	    	ObservableList<String> modelo_list = FXCollections.observableArrayList();
	    	for( ModeloCarro m : array_modelos_carro ) {
	    		modelo_list.add( m.getModelo() );
	    	}
	    	car_model_choice_box.setItems(modelo_list);
	    	
    	}
    }
    
    @FXML
    void choose_model_checkbox_changed(ActionEvent event) {
    	if( choose_model_checkbox.isSelected() ) {
    		car_model_choice_box.setDisable(false);
    	} else {
    		car_model_choice_box.setDisable(true);
    	}
    }

    @FXML
    void cancel_button_clicked(ActionEvent event) {
    	Stage stage = (Stage)rent_date_picker.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void confirm_button_clicked(ActionEvent event) {
    	String grupo_carro_selecionado = car_group_choice_box.getValue();
    	String modelo_carro_selecionado = car_model_choice_box.getValue();
    	LocalDate data_locacao = rent_date_picker.getValue();
    	LocalDate data_devolucao = devolution_date_picker.getValue();
    	String rent_hour = rent_hour_text.getText();
    	String rent_minute = rent_hour_text.getText();
    	String devolution_hour = rent_hour_text.getText();
    	String devolution_minute = rent_hour_text.getText();
    	LocalDateTime data_hora_locacao = data_locacao.atTime( Integer.valueOf(rent_hour) , Integer.valueOf(rent_minute) );
    	LocalDateTime data_hora_devolucao = data_devolucao.atTime( Integer.valueOf(devolution_hour) , Integer.valueOf(devolution_minute) );
    	
    	int id_grupo_carro_selecionado = (new GrupoCarroDAO()).find("grupo", grupo_carro_selecionado).get(0).getId();
    	// id_modelo_carro ou é -1 (tanto faz) ou selecionado (>=1)
    	int id_modelo_carro_selecionado = -1;
    	if( car_model_choice_box.isDisabled() == false && modelo_carro_selecionado.length() > 0 ) {
    		ArrayList<ModeloCarro> modelos = (new ModeloCarroDAO()).find("modelo", modelo_carro_selecionado);
    		if( modelos.size() > 0 ) {
        		id_modelo_carro_selecionado = modelos.get(0).getId();	
    		}
    	}
    
    	// Persiste a locação no database:
    	
    	
    }

}
