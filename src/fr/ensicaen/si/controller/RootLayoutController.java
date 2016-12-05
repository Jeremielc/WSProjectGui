package fr.ensicaen.si.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.ensicaen.si.dao.client.DbClientDao;
import fr.ensicaen.si.dao.operation.OperationDao;
import fr.ensicaen.si.db.DbManagement;
import fr.ensicaen.si.db.MySqlDbManagement;
import fr.ensicaen.si.model.Client;
import fr.ensicaen.si.model.Operation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class RootLayoutController implements Initializable {

	private Window owner;

	@FXML
	private TextField txtName, txtFirstname;
	@FXML
	private ComboBox<String> cmbSelectCustomer;
	@FXML
	private TableView<Operation> table;

	public RootLayoutController() {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtName.setText("");
		txtFirstname.setText("");
		table = new TableView<>();
	}

	@FXML
	private void handleSearch() {
		String name = txtName.getText();
		String firstname = txtFirstname.getText();
		cmbSelectCustomer.getItems().clear();

		if (name == null || name.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(owner);
			alert.setTitle("Error");
			alert.setHeaderText("Empty parameter");
			alert.setContentText("You must at least specify a name for the search engine.");
			alert.show();
		} else {
			DbManagement dbMan = DbManagement.getInstance();
			dbMan.setDelegate(new MySqlDbManagement());
			List<Client> clients = new ArrayList<>();

			try {
				dbMan.connection(MySqlDbManagement.NomBase);
				DbClientDao dbCli = new DbClientDao();
				if (firstname == null || firstname.isEmpty()) {
					clients = dbCli.getByName(name);
				} else {
					clients = dbCli.getByFullname(name, firstname);
				}
			} catch (SQLException ex) {
				ex.printStackTrace(System.err);
			}

			if (!clients.isEmpty()) {
				for (Client c : clients) {
					cmbSelectCustomer.getItems().add(c.toString());
				}
				cmbSelectCustomer.getSelectionModel().select(0);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initOwner(owner);
				alert.setTitle("No match");
				alert.setHeaderText("Client not found");
				alert.setContentText("It seems that no client match your request.");
				alert.show();
			}
		}
	}

	@FXML
	private void handleReset() {
		txtName.setText("");
		txtFirstname.setText("");
		cmbSelectCustomer.getItems().clear();
	}
	
	@FXML
	private void handleComboSelection() {
		String s = cmbSelectCustomer.getSelectionModel().getSelectedItem();
		String idString = "";
		
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
				case 0 :
					idString += "0";
					break;
				case 1 :
					idString += "1";
					break;
				case 2 : 
					idString += "2";
					break;
				case 3 : 
					idString += "3";
					break;
				case 4 : 
					idString += "4";
					break;
				case 5 : 
					idString += "5";
					break;
				case 6 : 
					idString += "6";
					break;
				case 7 : 
					idString += "7";
					break;
				case 8 : 
					idString += "8";
					break;
				case 9 : 
					idString += "9";
					break;
				default :
					break;
			}
		}
		
		int id = Integer.parseInt(idString);
		List<Operation> operations = OperationDao.getInstance().getById(id);
		ObservableList<Operation> operationList = FXCollections.observableList(operations);
	}

	@FXML
	public void closeProgram(ActionEvent event) {
		Platform.exit();
	}

	public void setOwner(Window owner) {
		this.owner = owner;
	}
}
