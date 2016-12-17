package fr.ensicaen.si.dao.operation;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import fr.ensicaen.si.model.Operation;
import fr.ensicaen.si.model.Resultat;

public class JerseyOperationDao implements IOperationDao {
	
	javax.ws.rs.client.Client rsclient;
	javax.ws.rs.client.WebTarget target;
	
	public JerseyOperationDao() {
		rsclient = javax.ws.rs.client.ClientBuilder.newClient();
		target = rsclient.target("http://localhost:8080/WSProjectRest_Hue_Leclerc/si");
	}

	@Override
	public List<Operation> getById(int id) {
		List<Operation> operations = target.path("operations").path("id").path("" + id).request(MediaType.APPLICATION_JSON).get(new GenericType<List<Operation>>() {});
		return operations;
	}

	@Override
	public Resultat getByName(String name) {
		List<Operation> operations = target.path("operations").path(name).request(MediaType.APPLICATION_JSON).get(new GenericType<List<Operation>>() {});
		Resultat res = new Resultat();
		res.setOperationList(operations);
		return res;
	}

	@Override
	public Resultat getByFullname(String surname, String name) {
		List<Operation> operations = target.path("operations").path(surname).path(name).request(MediaType.APPLICATION_JSON).get(new GenericType<List<Operation>>() {});
		Resultat res = new Resultat();
		res.setOperationList(operations);
		return res;
	}

}
