package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Base d'un controlleur
 */
public abstract class BaseController {

    @FXML
    private AnchorPane paneWindow;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public MainController getMainController() {
        return mainController;
    }

    public AnchorPane getPaneWindow() {
        return paneWindow;
    }

    /**
     * Ferme la fenêtre
     */
    public void closeWindow() {
        Stage stage = (Stage) getPaneWindow().getScene().getWindow();
        stage.close();
    }
}
