package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Person;

public class ViewController implements Initializable {

	@FXML
	private ComboBox<Person> comboboxPerson;

	private ObservableList<Person> obsList;

	@FXML
	private TextField txtNumber1;

	@FXML
	private TextField txtNumber2;

	@FXML
	private Label labelResult;

	@FXML
	private Button btSum;
	
	@FXML
	private Button btAll;
	
	@FXML
	public void onBtAllAction() {
		for(Person p : comboboxPerson.getItems()) {
			System.out.println(p);
		}
	}

	@FXML
	public void onComboBoxPersonAction() {
		Person person = comboboxPerson.getSelectionModel().getSelectedItem();
		System.out.println(person);
	}
	
	@FXML
	public void onBtSumAction() {
		try {
			Locale.setDefault(Locale.US);
			double number1 = Double.parseDouble(txtNumber1.getText());
			double number2 = Double.parseDouble(txtNumber2.getText());
			double sum = number1 + number2;
			labelResult.setText(String.format("%.2f", sum));
		} catch (NumberFormatException e) {
			Alerts.showAlert("Error", "Parse error", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldDouble(txtNumber1);
		Constraints.setTextFieldDouble(txtNumber2);
		Constraints.setTextFieldMAxLength(txtNumber1, 12);
		Constraints.setTextFieldMAxLength(txtNumber2, 12);

		List<Person> list = new ArrayList<>();
		list.add(new Person(1,"Maria","maria@gmail.com"));
		list.add(new Person(2,"Alex","alex@gmail.com"));
		list.add(new Person(3,"Bob","bob@gmail.com"));
		
		obsList = FXCollections.observableList(list);
		comboboxPerson.setItems(obsList);
		
		Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>(){
			@Override
			protected void updateItem(Person item, boolean empty) {
				super.updateItem(item,empty);
				setText(empty? "" : item.getName());
			}
		};
		
		comboboxPerson.setCellFactory(factory);
		comboboxPerson.setButtonCell(factory.call(null));
	}
}
