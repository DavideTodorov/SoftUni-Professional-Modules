import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        /* Part 1: Accessing Database via Simple Java Application – Demo */

        // 1. Connection and Connection Properties
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/soft_uni", "root", "12345");


        // 2. Preparing and Executing Statements
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT first_name, last_name FROM employees WHERE salary > ?");

        preparedStatement.setString(1, scanner.nextLine());


        // 3. Iterating over the Result
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            System.out.println(resultSet.getString("first_name") +
                    " " +
                    resultSet.getString("last_name"));
        }
    }
}
