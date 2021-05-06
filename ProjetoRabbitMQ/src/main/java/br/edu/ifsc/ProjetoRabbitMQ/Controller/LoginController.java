package br.edu.ifsc.ProjetoRabbitMQ.Controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import br.edu.ifsc.ProjetoRabbitMQ.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	static LoginController user;

	@FXML
	private TextField txtNumber;

	@FXML
	private JFXButton btGo;

	@FXML
	private JFXButton btRegister;

	@FXML
	private void click(ActionEvent e) throws IOException {
		Button btClicked = (Button) e.getSource();
		if (btClicked == btGo) {
			if (verifyUser(txtNumber.getText())) {
				Stage stage = new Stage();

				FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
				Parent root = (Parent) fxmlLoader.load();

				stage.setScene(new Scene(root));
				stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
					if (newValue)
						stage.setMaximized(false);
				});
				stage.show();

				Stage stage2 = (Stage) btGo.getScene().getWindow();
				stage2.close();
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Número incorreto");
				alert.setHeaderText("Erro");
				alert.show();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Essa função não está funcionando");
			alert.setHeaderText("Aviso");
			alert.show();
		}
	}

	private boolean verifyUser(String number) {
		if (number.contentEquals("111") || number.contentEquals("222") || number.contentEquals("333")) {
			user = this;
			System.out.println("LOGIN NUMERO: " + number);
			return true;
		} else {
			return false;
		}
	}

	public TextField getTxtNumber() {
		return txtNumber;
	}

}
