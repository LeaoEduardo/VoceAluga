package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaVeiculoController {
	
	Main main = new Main();

    @FXML
    private Text tf_dataManutencao;

    @FXML
    private Text tf_marca;

    @FXML
    private Text tf_quilometragem;

    @FXML
    private Text tf_modelo;

    @FXML
    private Text tf_dataCompra;

    @FXML
    private Text tf_filial;

    @FXML
    private Text tf_placa;

    @FXML
    private Text tf_grupo;

    @FXML
    private Text tf_estado;

    @FXML
    void initialize() {
    	
    	String placa = Contexto.getInstancia().getVeiculo().getPlaca();
    	String marca = Contexto.getInstancia().getVeiculo().getModeloCarro().getMarca();
    	String modelo = Contexto.getInstancia().getVeiculo().getModeloCarro().getModelo();
    	String grupo = Contexto.getInstancia().getVeiculo().getModeloCarro().getGrupoCarro().getGrupo();
    	Integer quilometragem = Contexto.getInstancia().getVeiculo().getQuilometragem();
        LocalDate dataCompra = Contexto.getInstancia().getVeiculo().getDataCompra();
        LocalDate dataManutencao = Contexto.getInstancia().getVeiculo().getDataManutencao();
        String filial = Contexto.getInstancia().getVeiculo().getFilial().getNome();
        String estado = Contexto.getInstancia().getVeiculo().getEstadoCarro().getTipo();
        
        tf_placa.setText(placa);
        tf_marca.setText(marca);
        tf_modelo.setText(modelo);
        tf_grupo.setText(grupo);
        tf_quilometragem.setText(quilometragem.toString());
        tf_dataCompra.setText(dataCompra.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        tf_dataManutencao.setText(dataManutencao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        tf_filial.setText(filial);
        tf_estado.setText(estado);
    }
    
    @FXML
    void atualizarVeiculo(ActionEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	Parent atualizarVeiculo = FXMLLoader.load(getClass().getResource("AtualizarVeiculo.fxml"));
    	Scene scene = new Scene(atualizarVeiculo);
    	
    	stage.setTitle("Atualizar registro de veiculo");
    	stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void venderVeiculo(ActionEvent event) throws IOException {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmar acao");
    	alert.setHeaderText("Este veiculo sera vendido");
    	alert.setContentText("Tem certeza de que deseja vender este veiculo?");
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if (result.get() == ButtonType.OK) {
    		// vende veiculo (seta estado = vendido)
    		ConnectionSQL con = new ConnectionSQL();
    		if(con.VendeVeiculo(Contexto.getInstancia().getVeiculo().getPlaca())) {
    			alert = new Alert(AlertType.CONFIRMATION);
    		    alert.setTitle("Confirmacao");
    		    alert.setHeaderText("Veiculo vendido.");
    		    alert.showAndWait();
    	    	main.showTelaPrincipal();
    		}
    		else {
    			alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Erro");
    		    alert.setHeaderText("Impossivel vender esse veiculo.");
    		    alert.setContentText("Tente novamente.");
    		    alert.showAndWait();
    		}
    	}
    }
    
    @FXML
    void voltar(ActionEvent event) throws IOException {
    	
    	Contexto.getInstancia().setVoltandoParaVeiculos(true);
    	main.showTelaPrincipal();
    }
}
