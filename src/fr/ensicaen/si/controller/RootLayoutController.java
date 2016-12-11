package fr.ensicaen.si.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.ensicaen.si.dao.client.DbClientDao;
import fr.ensicaen.si.dao.operation.DbOperationDao;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class RootLayoutController implements Initializable {

	private Window owner;

	@FXML
	private TextField txtName, txtFirstname;
	@FXML
	private ComboBox<Client> cmbSelectCustomer;
	@FXML
	private TableView<Operation> table;
	@FXML
	private TableColumn<Operation, Integer> idCol,  operationTypeCol;
	@FXML
	private TableColumn<Operation, Float> amountCol;
	@FXML
	private TableColumn<Operation, String> cardNumCol, accountNumCol, dateCol;

	public RootLayoutController() {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtName.setText("");
		txtFirstname.setText("");
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
					cmbSelectCustomer.getItems().add(c);
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
		table.getItems().clear();
	}

	@FXML
	private void handleComboSelection() {
		int id = cmbSelectCustomer.getSelectionModel().getSelectedItem().getId();

		if (!OperationDao.getInstance().isDelegated()) {
			OperationDao.getInstance().setDelegate(new DbOperationDao());
		}

		List<Operation> operations = OperationDao.getInstance().getById(id);
		ObservableList<Operation> oList = FXCollections.observableArrayList();
		for (Operation o : operations) {
			oList.add(o);
		}
		
		idCol.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("id"));
		operationTypeCol.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("operationType"));
		amountCol.setCellValueFactory(new PropertyValueFactory<Operation, Float>("amount"));
		cardNumCol.setCellValueFactory(new PropertyValueFactory<Operation, String>("cardNum"));
		accountNumCol.setCellValueFactory(new PropertyValueFactory<Operation, String>("accountNum"));
		dateCol.setCellValueFactory(new PropertyValueFactory<Operation, String>("date"));
		idCol.setPrefWidth(100);
		operationTypeCol.setPrefWidth(100);
		amountCol.setPrefWidth(100);
		cardNumCol.setPrefWidth(150);
		accountNumCol.setPrefWidth(150);
		dateCol.setPrefWidth(150);
		
		table.getItems().clear();
		table.setItems(oList);
	}

	@FXML
	public void closeProgram(ActionEvent event) {
		Platform.exit();
	}

	public void setOwner(Window owner) {
		this.owner = owner;
	}
}
