package view;

import controller.MainController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainView implements IWindowView {

    /**
     * Création de la fenêtre principale
     *
     * @param primaryStage fenêtre principale
     * @param mainController controlleur principal
     * @throws Exception
     */
    @Override
    public void createWindow(Stage primaryStage, MainController mainController) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/searchCustomer.fxml"));
        primaryStage.setTitle("Search a customer");
        primaryStage.setScene(new Scene(fxmlLoader.load(), 960, 610));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public void closeProgram(){
    	Platform.exit();
    }
}
