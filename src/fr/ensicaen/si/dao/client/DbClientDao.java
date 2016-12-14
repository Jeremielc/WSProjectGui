package fr.ensicaen.si.dao.client;

import java.sql.ResultSet;
import java.sql.SQLException;
import fr.ensicaen.si.db.DbManagement;
import fr.ensicaen.si.db.MySqlDbManagement;
import fr.ensicaen.si.model.Client;

public class DbClientDao extends AClientDao {

	public DbClientDao() {
		loadDb();
	}
	
	public void loadDb() {
		try {
			DbManagement dbMan = DbManagement.getInstance();
			dbMan.setDelegate(new MySqlDbManagement());
			dbMan.connection(MySqlDbManagement.NomBase);
			ResultSet res = dbMan.query("select * from tabclient");
			
			while (res.next()) {
				Client temp = new Client();
				temp.setId(res.getInt("idNumClient"));
				temp.setSurname(res.getString("txtNomCli"));
				temp.setName(res.getString("txtPrenomCli"));
				clients.add(temp);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.err);
		}
	}
}
