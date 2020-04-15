import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static Connection connection = null;
    public DbConnection() {
    }

    public Connection getConnection()  {

        if (connection != null)
            return connection;
        else {


            String driver= "org.postgresql.Driver";
            String url = "jdbc:postgresql://database-1-instance-1.ceuwom91dpva.us-east-1.rds.amazonaws.com:5432/suroradb";
            String user= "postgres";
//            String url = "jdbc:postgresql://localhost:5432/postgres";
            String password="7c056266";
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(e);
            }
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }

            return connection;
        }

    }
}