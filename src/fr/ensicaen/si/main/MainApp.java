package fr.ensicaen.si.main;

import java.io.IOException;

import fr.ensicaen.si.controller.RootLayoutController;
/*import java.util.List;

import fr.ensicaen.si.dao.client.ClientDao;
import fr.ensicaen.si.dao.client.DbClientDao;
import fr.ensicaen.si.dao.operation.DbOperationDao;
import fr.ensicaen.si.dao.operation.OperationDao;
import fr.ensicaen.si.model.Client;
import fr.ensicaen.si.model.Operation;
import fr.ensicaen.si.model.Resultat;*/
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
			
			RootLayoutController rlc = loader.getController();
			rlc.setOwner(primaryStage);

			primaryStage.setTitle("Search a customer");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
		
		/*//Comme on dit chez nous, ça marche ! ------------ Zone de test -------------
		ClientDao cd = ClientDao.getInstance();
		System.out.println(cd.isDelegated() == false ? "Pas de delegué." : "Correctement délègué.");
		cd.setDelegate(new DbClientDao());
		System.out.println(cd.isDelegated() == false ? "Pas de delegué." : "Correctement délègué.");
		System.out.println(cd.countClient());
		System.out.println(cd.getById(55));
		System.out.println(cd.getByFullname("Béatrice", "Innocent").get(0));
		
		List<Client> bea = cd.getByName("Innocent");
		for (Client c : bea) {
			System.out.println(c);
		}
		
		OperationDao od = OperationDao.getInstance();
		System.out.println(od.isDelegated() == false ? "Pas de delegué." : "Correctement délègué.");
		od.setDelegate(new DbOperationDao());
		System.out.println(od.isDelegated() == false ? "Pas de delegué." : "Correctement délègué.");
		System.out.println(od.getById(26));
		System.out.println(od.getByFullname("Baudoin", "Claude"));

		Resultat claudeOps = od.getByName("Claude");
		for (Operation ope : claudeOps.getOperationList()) {
			System.out.println(ope);
		}//*/
	}

	public static void main(String[] args) {
		launch(args);
	}
}
