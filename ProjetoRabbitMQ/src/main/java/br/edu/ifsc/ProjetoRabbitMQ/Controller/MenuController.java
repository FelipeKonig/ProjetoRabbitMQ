package br.edu.ifsc.ProjetoRabbitMQ.Controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import br.edu.ifsc.ProjetoRabbitMQ.RabbitMQ.Emitter;
import br.edu.ifsc.ProjetoRabbitMQ.RabbitMQ.ReceiverRow;
import br.edu.ifsc.ProjetoRabbitMQ.RabbitMQ.Receiverps;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MenuController implements Initializable {

	private Emitter emitter = new Emitter();
	private ReceiverRow receiver = new ReceiverRow();
	private Receiverps receiverPs = new Receiverps();

	private ObservableList<String> menuUser = FXCollections.observableArrayList("Perfil", "Configurações",
			"Sair da conta");
	private ObservableList<String> menuContact = FXCollections.observableArrayList("Dados do contato");
	private ObservableList<String> contacts = FXCollections.observableArrayList("111", "222", "333", "444");

	private String number = LoginController.user.getTxtNumber().getText();
	private String numberContact;
	private HashMap<String, List<String>> listMessages = new HashMap<String, List<String>>();

	public MenuController() {
		receiver.defineReceiver(number, this);
		receiver.run();
		receiverPs.saveMenu(this);
		receiverPs.run();
	}

	@FXML
	private JFXComboBox<String> cbMenuUser;

	@FXML
	private JFXComboBox<String> cbMenuContact;

	@FXML
	private JFXListView<String> listContacts;

	@FXML
	private ListView<String> tableChat;

	@FXML
	private Label lblContact;

	@FXML
	private Pane paneNoChat;

	@FXML
	private TextArea txtMessage;

	@FXML
	private JFXButton btSubmit;

	@FXML
	private void selectContact(MouseEvent event) {

		numberContact = listContacts.getSelectionModel().getSelectedItem();

		if (numberContact != null) {

			if (numberContact.length() > 3) {
				String justNumber[] = numberContact.split(" ");
				numberContact = justNumber[0];
			}

			updateListContact(numberContact, false);
			paneNoChat.setVisible(false);
			lblContact.setText(numberContact);
			getTableChat();
		}
	}

	@FXML
	private void sendMessage(MouseEvent event) {
		String msg = number + ": " + txtMessage.getText();

		if (!(numberContact.contentEquals("444"))) {
			addMessage(msg, numberContact);
		}
		emitter.defineReceiverName(numberContact);
		emitter.writeMessage(msg);
		emitter.run();

		txtMessage.clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addComboBox();
		addContacts();
	}

	private void getTableChat() {
		verifyTableChat(numberContact);
		Platform.runLater(() -> {
			try {
				tableChat.setItems((ObservableList<String>) listMessages.get(numberContact));
			} catch (Exception e) {
				System.out.println(e);
			}
		});
	}

	public void addMessage(String msg, String numberMsg) {
		Platform.runLater(() -> {
			try {
				verifyTableChat(numberMsg);
				listMessages.get(numberMsg).add(msg);

				if (numberContact != null && numberMsg.contains(numberContact)) {
					tableChat.setItems((ObservableList<String>) listMessages.get(numberMsg));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		});
	}

	public void updateListContact(String number, boolean receiver) {

		Platform.runLater(() -> {
			try {
				boolean contactOpen = false;

				if (numberContact != null && number.contentEquals(numberContact)) {
					contactOpen = true;
				}

				for (int i = 0; i < contacts.size(); i++) {
					if (contacts.get(i).contains(number)) {
						if (!(contacts.get(i).contains("Nova mensagem aqui!"))) {
							if (receiver && !(contactOpen)) {
								String updateContact = contacts.get(i)
										.concat("                        Nova mensagem aqui!");
								contacts.remove(i);
								contacts.add(updateContact);
								listContacts.setItems(contacts);
							}
						} else {
							if (!(receiver)) {
								contacts.remove(i);
								contacts.add(number);
								listContacts.setItems(contacts);
								listContacts.getSelectionModel().selectLast();
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		});

	}

	private void verifyTableChat(String newNumber) {
		if (!(listMessages.containsKey(newNumber))) {
			ObservableList<String> newList = FXCollections.observableArrayList();
			listMessages.put(newNumber, newList);
		}
	}

	private void addComboBox() {
		cbMenuUser.setItems(menuUser);
		cbMenuContact.setItems(menuContact);
	}

	private void addContacts() {
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).contentEquals(number)) {
				contacts.remove(i);
			}
		}
		listContacts.setItems(contacts);
	}
}
