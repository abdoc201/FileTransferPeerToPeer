package sendfile.client;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addUser {

   public static void main(String[] args) {

    String url = "jdbc:mysql://localhost:3306/test";
    String username = "test";
    String password = "test";

    // Sample user data
    String email = "test";
    String plainPassword = "test";

    try {
        // Establish database connection
        Connection connection = DriverManager.getConnection(url, username, password);

        // Hash the plain password using BCrypt
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        // Insert user into the 'users' table
        String insertQuery = "INSERT INTO users (email, password) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.executeUpdate();
            System.out.println("User inserted successfully.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}