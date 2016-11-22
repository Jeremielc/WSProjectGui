package main;
	
import javafx.application.Application;
import javafx.stage.Stage;

import controller.MainController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainController controller = new MainController();
        controller.openMainWindow(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}