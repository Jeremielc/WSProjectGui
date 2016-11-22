package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import view.*;

public class MainController extends BaseController {
	
    private MainView mainView;

     

    public MainController() {
        mainView = new MainView();
    }

    public void openMainWindow(Stage primaryStage) {
        try {
            mainView.createWindow(primaryStage, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Fermeture du programme
     */
    @FXML
    public void closeProgram(ActionEvent event) {
        Platform.exit();
    }
}
