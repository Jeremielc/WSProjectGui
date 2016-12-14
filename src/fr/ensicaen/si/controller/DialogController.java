package fr.ensicaen.si.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DialogController implements Initializable {
	private Stage stage;
	
	@FXML
	private RadioButton jdbc, restful;

	public DialogController() {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		jdbc.setSelected(true);
		restful.setSelected(false);
	}
	
	@FXML
	public void handleSelectionJDBC() {
		jdbc.setSelected(true);
		restful.setSelected(false);
	}
	
	@FXML
	public void handleSelectionREST() {
		jdbc.setSelected(false);
		restful.setSelected(true);
	}

	@FXML
	public void handleConnect() {
		boolean directAccess = false;
		if (jdbc.isSelected()) {
			directAccess = true;
		} else {
			directAccess = false;
		}

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fr/ensicaen/si/fxml/searchCustomer.fxml"));

			Stage stage = new Stage();

			AnchorPane rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);

			RootLayoutController rlc = loader.getController();
			rlc.setOwner(stage);
			rlc.setDirectAccess(directAccess);

			stage.getIcons().add(new Image(getClass().getResourceAsStream("/fr/ensicaen/si/resources/1481504894_Bank.png")));
			stage.setTitle("Connection method");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
			
			this.stage.close();
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
	}

	@FXML
	public void handleCancel() {
		Platform.exit();
	}

	public void setStage(Stage primaryStage) {
		this.stage = primaryStage;
	}
}
