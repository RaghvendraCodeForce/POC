package expenseTracker;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {

    private Connection connection;

    public DBConnector() throws IOException, SQLException {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/resources/db.properties"));

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error connecting to database.");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}