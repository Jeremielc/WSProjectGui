package fr.ensicaen.si.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(System.getProperty("user.dir"));
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fr/ensicaen/si/fxml/searchCustomer.fxml"));

			AnchorPane rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);

			primaryStage.setTitle("Search a customer");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
