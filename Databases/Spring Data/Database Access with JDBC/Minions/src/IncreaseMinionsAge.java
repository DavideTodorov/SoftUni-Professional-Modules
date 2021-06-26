import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class IncreaseMinionsAge {

    public static void main(String[] args) throws SQLException {
        /*      8. Increase Minions Age        */

        Scanner scanner = new Scanner(System.in);

        Object[] input = Arrays.stream(scanner.nextLine().split("\\s+")).toArray();


        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", "root", "12345");


        PreparedStatement minionsStatement = connection.prepareStatement("SELECT if(id IN (?), lower(name), name),\n" +
                "       if(id IN (?), age + 1, age)\n" +
                "FROM minions;");


        // TODO: Feature not supported in MySQL
        minionsStatement.setArray(1, connection.createArrayOf("INT", input));
        minionsStatement.setArray(2, connection.createArrayOf("INT", input));


        ResultSet alteredMinions = minionsStatement.executeQuery();


        while (alteredMinions.next()) {
            System.out.println(alteredMinions.getString(1) + " " + alteredMinions.getInt(2));

        }


    }
}
