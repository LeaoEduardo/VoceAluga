package application.controller;


import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AbaLocacoesController {

    @FXML
    private CheckBox cb_incluir_locacoes_passadas;
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
    private TableColumn<Locacao, String> col_idCliente;
    
    private String nome_filial_selecionado;
    
    @FXML
    void initialize() {
    	
    	ObservableList<String> nome_filiais = FXCollections.observableArrayList();
    	Filial filial_contexto = Contexto.getInstancia().getFuncionario().getFilial();
    	nome_filiais.add("Todas filiais");
    	nome_filiais.add("Minha filial(" + filial_contexto.getNome() + ")");
    	ArrayList<Filial> todas_filiais = (new FilialDAO()).findAll();
    	for( Filial filial : todas_filiais ) {
    		if( filial.getId() != filial_contexto.getId() ) {
    			nome_filiais.add( filial.getNome() );
    		}
    	}
    	filial_escolhida.setItems(nome_filiais);
    	filial_escolhida.getSelectionModel().selectedItemProperty().addListener( 
    			(value,old_value,new_value) -> {
    				nome_filial_selecionado = new_value;
    				atualizaTabela(null); // aqui dentro ele não percebeu a alteração no choicebox ainda...
    			}
    	);
    	filial_escolhida.getSelectionModel().clearAndSelect(0);
    }

    @FXML
    void confirmarDevolucao(ActionEvent event) throws IOException {
    	
    	Locacao loc = tabela.getSelectionModel().getSelectedItem();
    	
    	if( loc != null && loc.isDevolvido() == false ) {
    		
        	Contexto.getInstancia().setLocacao(loc);
    		Stage stage = new Stage();
    		Parent confirmarPagamento = FXMLLoader.load(getClass().getResource("ConfirmarPagamento.fxml"));
    		Scene scene = new Scene(confirmarPagamento);
    		stage.setTitle("Confirmacao de pagamento");
    		stage.setScene(scene);
    		stage.show();
    	}
    }

    @FXML
    void atualizaTabela(ActionEvent event) {
    	ObservableList<Locacao> lista_locacao = FXCollections.observableArrayList();
    	ArrayList<Locacao> todas_locacoes = (new LocacaoDAO()).findAll();
    	boolean incluir_passadas = cb_incluir_locacoes_passadas.isSelected();
    	Filial filial = null;
    	
    	System.out.println(todas_locacoes);
    	System.out.println("INDEX SELECIONADO:");
    	System.out.println( filial_escolhida.getSelectionModel().getSelectedIndex() );
    	
    	if( filial_escolhida.getSelectionModel().getSelectedIndex() > 0 ) {
    		// Opção "Minha filial"
        	if( filial_escolhida.getSelectionModel().getSelectedIndex() == 1 ) {
        		filial = Contexto.getInstancia().getFuncionario().getFilial();
        	}else {
            	ArrayList<Filial> result = (new FilialDAO()).find("nomeFilial", nome_filial_selecionado);
            	if( result.size() > 0 ) filial = result.get(0);
        	}
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
    	col_idCliente.setCellValueFactory( ( cell_data ) -> {
    		return new SimpleStringProperty( cell_data.getValue().getCliente().getCpf() == null?
    				cell_data.getValue().getCliente().getPassaporte():cell_data.getValue().getCliente().getCpf() );
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
