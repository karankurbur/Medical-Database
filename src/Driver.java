
import java.sql.*;

public class Driver {
	public static void main(String[] args) {
		try {
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/hospital?autoReconnect=true&useSSL=false", "root", "password");
			LoginGUI login = new LoginGUI(myConn);
			login.setVisible(true);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
