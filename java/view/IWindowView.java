package view;

import controller.MainController;
import javafx.stage.Stage;

public interface IWindowView {

    public void createWindow(Stage stage, MainController mainController) throws Exception;

}
