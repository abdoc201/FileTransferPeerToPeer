package sendfile.client;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStudent {

   public static void main(String[] args) {

    String url = "jdbc:mysql://localhost:3306/ENSA_STUDENTS";
    String username = "root";
    String password = "mysql password";

    String email = "student's email";
    String plainPassword = "student's password";

    try {
        Connection connection = DriverManager.getConnection(url, username, password);

        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        String insertQuery = "INSERT INTO students (email, password) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.executeUpdate();
            System.out.println("Student inserted successfully.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}