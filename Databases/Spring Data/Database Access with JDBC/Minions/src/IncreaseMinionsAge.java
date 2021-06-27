import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class IncreaseMinionsAge {

    public static void main(String[] args) throws SQLException {
        /*      8. Increase Minions Age        */

        Scanner scanner = new Scanner(System.in);

        int[] input = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();


        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", "root", "12345");


        PreparedStatement minionsStatement = connection.prepareStatement("SELECT if(id IN (?,?,?), lower(name), name),\n" +
                "       if(id IN (?, ?, ?), age + 1, age)\n" +
                "FROM minions;");


        // TODO: Feature not supported in MySQL
        minionsStatement.setInt(1, input[0]);
        minionsStatement.setInt(2, input[1]);
        minionsStatement.setInt(3, input[2]);
        minionsStatement.setInt(4, input[0]);
        minionsStatement.setInt(5, input[1]);
        minionsStatement.setInt(6, input[2]);


        ResultSet alteredMinions = minionsStatement.executeQuery();


        while (alteredMinions.next()) {
            System.out.println(alteredMinions.getString(1) + " " + alteredMinions.getInt(2));

        }


    }
}
