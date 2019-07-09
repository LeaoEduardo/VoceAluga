package application.controller;


import java.util.ArrayList;

import application.Contexto;
import application.dao.FilialDAO;
import application.dao.LocacaoDAO;
import application.entity.Filial;
import application.entity.Locacao;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AbaLocacoesController {

    @FXML
    private CheckBox 			cb_incluir_locacoes_passadas;
    @FXML
    private TableView<Locacao> 	tabela;
    @FXML
    private ChoiceBox<String> 	filial_escolhida;
    @FXML
    private TableColumn<Locacao, String> col_data_dev;
    @FXML
    private TableColumn<Locacao, String> col_data_loc;
    @FXML
    private TableColumn<Locacao, String> col_devolvido;
    @FXML
    private TableColumn<Locacao, String> col_placa;
    @FXML
    private TableColumn<Locacao, String> col_modelo;
    
    @FXML
    void initialize() {
    }

    @FXML
    void confirmarDevolucao(ActionEvent event) {
    	Locacao loc = tabela.getSelectionModel().getSelectedItem();
    	if( loc != null && loc.isDevolvido() == false ) {
    		if( loc.confirmaDevolucao( Contexto.getInstancia().getFuncionario().getFilial() ) ) {
    			System.out.println("Devolucao confirmada");
    			atualizaTabela(event);
    		}
    	}
    }

    @FXML
    void atualizaTabela(ActionEvent event) {
    	ObservableList<Locacao> lista_locacao = FXCollections.observableArrayList();
    	ArrayList<Locacao> todas_locacoes = (new LocacaoDAO()).findAll();
    	boolean incluir_passadas = cb_incluir_locacoes_passadas.isSelected();
    	Filial filial = null;
    	if( filial_escolhida.getSelectionModel().getSelectedIndex() > 0 ) {
    		String nome_filial = filial_escolhida.getSelectionModel().getSelectedItem();
        	ArrayList<Filial> result = (new FilialDAO()).find("nomeFilial", nome_filial);
        	if( result.size() > 0 ) filial = result.get(0);
    	}
    	for( Locacao loc : todas_locacoes ) {
    		if( incluir_passadas == false && loc.isDevolvido() ) {
    			continue;
    		}
    		if( filial != null && loc.getCarro().getFilial().getId() != filial.getId() ) {
    			continue;
    		}
    		lista_locacao.add(loc);
    	}
    	
    	tabela.setItems(lista_locacao);
    	col_devolvido.setCellValueFactory( ( cell_data ) -> {
    		return new SimpleStringProperty( cell_data.getValue().isDevolvido()? "Sim":"Não" );
    	});
    	col_placa.setCellValueFactory( ( cell_data ) -> {
    		return new SimpleStringProperty( cell_data.getValue().getCarro().getPlaca() );
    	});
    	col_modelo.setCellValueFactory( ( cell_data ) -> {
    		return new SimpleStringProperty( cell_data.getValue().getCarro().getModeloCarro().getModelo() );
    	});
    	col_data_loc.setCellValueFactory( ( cell_data ) -> {
    		return new SimpleStringProperty( cell_data.getValue().getDataInicial().toString() );
    	});
    	col_data_dev.setCellValueFactory( ( cell_data ) -> {
    		return new SimpleStringProperty( cell_data.getValue().getDataFinal().toString() );
    	});
    	
    }


}
