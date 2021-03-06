package fr.ensicaen.si.main;

import java.io.IOException;

import fr.ensicaen.si.controller.DialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fr/ensicaen/si/fxml/Dialog.fxml"));

			AnchorPane rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);
			
			DialogController dc = loader.getController();
			dc.setStage(primaryStage);
			
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/fr/ensicaen/si/resources/1481504894_Bank.png")));
			primaryStage.setTitle("Connection method");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
