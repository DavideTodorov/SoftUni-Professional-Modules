import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        /* Part 2: Writing your own data retrieval application */

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/diablo", "root", "12345");

        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT user_name, first_name, last_name, COUNT(game_id) AS games\n" +
                        "FROM users\n" +
                        "JOIN users_games\n" +
                        "ON users.id = users_games.user_id\n" +
                        "where user_name = ?\n" +
                        "GROUP BY (`user_id`);");

        System.out.println("Enter username: ");
        preparedStatement.setString(1, scanner.nextLine());

        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            System.out.println("No such user exists");
        } else {

            System.out.println("User: " + resultSet.getString("user_name"));
            System.out.println(resultSet.getString("first_name") +
                    " " +
                    resultSet.getString("last_name") +
                    " has played " +
                    resultSet.getString("games") +
                    " games");

        }
    }
}
