package sendfile.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class createDB {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "ENSA_STUDENTS";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql password";

    private static final String CREATE_DB_SQL = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;

    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS students ("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "email VARCHAR(50) NOT NULL,"
            + "password VARCHAR(60) NOT NULL)";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
                 Statement statement = connection.createStatement()) {

                statement.executeUpdate(CREATE_DB_SQL);
                System.out.println("Database created successfully");

                statement.executeUpdate("USE " + DB_NAME);
                System.out.println("Using database: " + DB_NAME);

                statement.executeUpdate(CREATE_TABLE_SQL);
                System.out.println("Table 'Students' created successfully");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
