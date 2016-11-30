package fr.ensicaen.si.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RootLayoutController extends BaseController {
	
	@FXML private TextField txtName, txtFirstname;
	@FXML private ComboBox<String> cmbSelectCustomer;

	public RootLayoutController() {
		
	}

	@FXML
	public void closeProgram(ActionEvent event) {
		Platform.exit();
	}
}
