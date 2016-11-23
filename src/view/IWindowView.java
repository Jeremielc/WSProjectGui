package view;

import controller.RootLayoutController;
import javafx.stage.Stage;

public interface IWindowView {

	public void createWindow(Stage stage, RootLayoutController mainController) throws Exception;

}
