package fr.ensicaen.si.db;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDbManagement extends ADbManagement {
	public static final String NomBase = "jdbc:mysql://localhost/si?autoReconnect=true";
	
	public MySqlDbManagement() {
		
	}
	
	@Override
	public void connection(String bdd_path) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception ex) {
			System.err.println("Driver loading failed " + ex.getMessage());
			throw new RuntimeException("Driver loading failed.");
		}
		
		try {
			conn = DriverManager.getConnection(bdd_path, User, Password); 
			if (conn == null) {
				System.out.println("Could not connect !!");
			} else {
				System.out.println("Connection successfull !");
			}
		} catch (SQLException e) {
			System.err.println("Connection error " + e.getMessage());
		}
	}
}
