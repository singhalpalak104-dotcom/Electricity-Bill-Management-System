import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/electricity";
        String user = "root";
        String password = "root";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully!");
            return con;

        } catch (Exception e) {
            System.out.println("DATABASE CONNECTION ERROR:");
            e.printStackTrace();
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        }
    }
}