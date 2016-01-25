package sampleCode;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectDatabase {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// jdbc://ip:port/da_name
		String dbUrl = "jdbc:mysql://localhost/example";
		// Database Username
		String username = "root";
		// Database Password
		String password = "12345";
		// Query to Execute
		String query = "select * from users;";
		// Load mysql jdbc driver
		Class.forName("com.mysql.jdbc.Driver");
		// Create Connection to DB
		Connection con = DriverManager.getConnection(dbUrl, username, password);
		// Create Statement Object
		Statement stmt = con.createStatement();
		// Execute the SQL Query. Store results in ResultSet
		ResultSet rs = stmt.executeQuery(query);
		// While Loop to iterate through all data and print results
		while (rs.next()) {
			String id = rs.getString(1);
			String name = rs.getString(2);
			System.out.println(id + "  " + name);
		}
		// closing DB Connection
		con.close();
	}
}
