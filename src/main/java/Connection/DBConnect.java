package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private static String DB_URL = "jdbc:mysql://localhost:3306/airplane_ticket_db";
    private static String userName = "root";
    private static String password = "";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,userName,password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
