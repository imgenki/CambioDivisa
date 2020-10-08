package dad.javafx.cambiodivisa;

import javax.swing.JComboBox;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Aplicacion extends Application {
	private Divisa euro = new Divisa("Euro", 1.0);
	private Divisa libra = new Divisa ("Libra", 0.9);
	private Divisa dolar = new Divisa ("Dolar", 1.2);
	private Divisa yen = new Divisa ("Yen", 133.6);
	private TextField originalText, converText;
	
	private Divisa [] divisas = { euro, libra, dolar, yen} ;
	
	private ComboBox<Divisa> origenCombo;
	private ComboBox<Divisa> destinoCombo;
	private Button cambiarButton;

	public void start(Stage primaryStage) throws Exception {
		//Campo de texto origen
		originalText = new TextField("0");
		originalText.setPrefColumnCount(4);
		//Desplegable moneda
		origenCombo = new ComboBox<>();
		origenCombo.getItems().addAll(divisas);
		origenCombo.getSelectionModel().select(euro);
		
		HBox originalBox = new HBox();
		originalBox.setAlignment(Pos.BASELINE_CENTER);
		originalBox.setSpacing(5);
		originalBox.getChildren().addAll(originalText, origenCombo);
		//Campo de texto destino
		converText = new TextField("0");
		converText.setPrefColumnCount(4);
		converText.setEditable(false);
		//Desplegable moneda
		destinoCombo = new ComboBox<>();
		destinoCombo.getItems().addAll(divisas);
		destinoCombo.getSelectionModel().select(libra);		
		HBox converBox = new HBox();
		converBox.setAlignment(Pos.BASELINE_CENTER);
		converBox.setSpacing(5);
		converBox.getChildren().addAll(converText, destinoCombo);
		
		cambiarButton = new Button();
		cambiarButton.setText("Cambiar");
		cambiarButton.setOnAction(e -> onCambiarButtonAction(e));

		
		
		// Caja principal
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(originalBox, converBox, cambiarButton);

		Scene escena = new Scene(root, 320, 200);

		primaryStage.setScene(escena);
		primaryStage.setTitle("CambioDivisa");
		primaryStage.show();

	}
	private void onCambiarButtonAction(ActionEvent e) {
		Double cantidadOrigen = Double.parseDouble(originalText.getText());
		Divisa divisaOrigen = origenCombo.getSelectionModel().getSelectedItem();
		Divisa divisaDestino = destinoCombo.getSelectionModel().getSelectedItem();
		
		Double enEuros = divisaOrigen.toEuro(cantidadOrigen);
		Double cantidadDestino = divisaDestino.fromEuro(enEuros);
		
		converText.setText("" + cantidadDestino);
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}