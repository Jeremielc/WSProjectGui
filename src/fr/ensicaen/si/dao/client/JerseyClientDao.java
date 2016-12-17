package fr.ensicaen.si.dao.client;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import fr.ensicaen.si.model.Client;

public class JerseyClientDao extends AClientDao {
	
	public JerseyClientDao() {
		javax.ws.rs.client.Client rsclient = javax.ws.rs.client.ClientBuilder.newClient();
		javax.ws.rs.client.WebTarget target = rsclient.target("http://localhost:8080/WSProjectRest_Hue_Leclerc/si");
		clients = target.path("clients").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Client>> () {});
	}
}
