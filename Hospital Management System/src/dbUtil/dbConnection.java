package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
	private static final String url="jdbc:mysql://localhost:3306/hospital_system";
	private static final String uname= "root";
	private static final String pass="root";
	
	
	public static Connection getConnection()throws SQLException{
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url,uname,pass);
			
			
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
		
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
