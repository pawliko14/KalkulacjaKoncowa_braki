package DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
public class RCPdatabaseConnection {
	Connection conn=null;
	public static Connection dbConnector()
	{
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mariadb://192.168.90.123/fatdb","listy","listy1234");
			return conn;
		}catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}



