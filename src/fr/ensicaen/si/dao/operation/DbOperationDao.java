package fr.ensicaen.si.dao.operation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.ensicaen.si.db.DbManagement;
import fr.ensicaen.si.db.MySqlDbManagement;
import fr.ensicaen.si.model.Operation;
import fr.ensicaen.si.model.Resultat;

public class DbOperationDao implements IOperationDao {

	public DbOperationDao() {
		
	}

	@Override
	public List<Operation> getById(int id) {
		List<Operation> operations = new ArrayList<>();
		try {
			DbManagement dbMan = DbManagement.getInstance();
			dbMan.setDelegate(new MySqlDbManagement());
			dbMan.connection(MySqlDbManagement.NomBase);
			ResultSet res = dbMan.query("SELECT ope.* " + "FROM tabOperation ope "
					+ "INNER JOIN relClientCompte cc ON cc.idNumCompte = ope.idNumCompte "
					+ "INNER JOIN tabClient cl ON cl.idNumClient = cc.idNumClient " + "WHERE cl.idNumClient = '" + id
					+ "' UNION " + "SELECT ope.* " + "FROM tabOperation ope "
					+ "INNER JOIN tabCarte ca ON ca.idNumCarte = ope.idNumCarte "
					+ "INNER JOIN tabClient cl ON cl.idNumClient = ca.idNumClient " + "WHERE cl.idNumClient = '" + id
					+ "'");

			while (res.next()) {
				Operation temp = new Operation();
				temp.setId(res.getInt("idNumOperation"));
				temp.setCardNum(res.getString("idNumCarte"));
				temp.setAccountNum(res.getString("idNumCompte"));
				temp.setAmount(res.getFloat("numMontantOpe"));
				temp.setDate(res.getString("datOpe"));
				operations.add(temp);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.err);
		}
		
		return operations;
	}

	@Override
	public Resultat getByName(String name) {
		Resultat result = new Resultat();
		List<Operation> operations = new ArrayList<>();
		
		try {
			DbManagement dbMan = DbManagement.getInstance();
			dbMan.setDelegate(new MySqlDbManagement());
			dbMan.connection(MySqlDbManagement.NomBase);
			ResultSet res = dbMan.query("SELECT ope.* " + "FROM tabOperation ope "
					+ "INNER JOIN relClientCompte cc ON cc.idNumCompte = ope.idNumCompte "
					+ "INNER JOIN tabClient cl ON cl.idNumClient = cc.idNumClient " + "WHERE cl.txtNomCli = '" + name
					+ "' UNION " + "SELECT ope.* " + "FROM tabOperation ope "
					+ "INNER JOIN tabCarte ca ON ca.idNumCarte = ope.idNumCarte "
					+ "INNER JOIN tabClient cl ON cl.idNumClient = ca.idNumClient " + "WHERE cl.txtNomCli = '" + name
					+ "'");
			
			while (res.next()) {
				Operation temp = new Operation();
				temp.setId(res.getInt("idNumOperation"));
				temp.setCardNum(res.getString("idNumCarte"));
				temp.setAccountNum(res.getString("idNumCompte"));
				temp.setAmount(res.getFloat("numMontantOpe"));
				temp.setDate(res.getString("datOpe"));
				operations.add(temp);
			}
			
			result.setOperationList(operations);
		} catch (SQLException ex) {
			ex.printStackTrace(System.err);
		}
		
		return result;
	}

	@Override
	public Resultat getByFullname(String surname, String name) {
		Resultat result = new Resultat();
		List<Operation> operations = new ArrayList<>();
		
		try {
			DbManagement dbMan = DbManagement.getInstance();
			dbMan.setDelegate(new MySqlDbManagement());
			dbMan.connection(MySqlDbManagement.NomBase);
			ResultSet res = dbMan.query("SELECT ope.* " + "FROM tabOperation ope "
					+ "INNER JOIN relClientCompte cc ON cc.idNumCompte = ope.idNumCompte "
					+ "INNER JOIN tabClient cl ON cl.idNumClient = cc.idNumClient " + "WHERE cl.txtNomCli = '" + surname + "' AND cl.txtPrenomCli = '" + name
					+ "' UNION " + "SELECT ope.* " + "FROM tabOperation ope "
					+ "INNER JOIN tabCarte ca ON ca.idNumCarte = ope.idNumCarte "
					+ "INNER JOIN tabClient cl ON cl.idNumClient = ca.idNumClient " + "WHERE cl.txtNomCli = '" + surname + "' AND cl.txtPrenomCli = '" + name
					+ "'");
			
			while (res.next()) {
				Operation temp = new Operation();
				temp.setId(res.getInt("idNumOperation"));
				temp.setCardNum(res.getString("idNumCarte"));
				temp.setAccountNum(res.getString("idNumCompte"));
				temp.setAmount(res.getFloat("numMontantOpe"));
				temp.setDate(res.getString("datOpe"));
				operations.add(temp);
			}
			
			result.setOperationList(operations);
		} catch (SQLException ex) {
			ex.printStackTrace(System.err);
		}
		
		return result;
	}
}
