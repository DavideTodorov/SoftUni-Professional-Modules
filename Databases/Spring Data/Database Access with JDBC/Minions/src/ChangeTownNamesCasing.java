import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNamesCasing {

    public static void main(String[] args) throws SQLException {
        /*      5. Change Town Names Casing     */

        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", "root", "12345");

        //Statement to update towns names
        PreparedStatement preparedStatement =
                connection.prepareStatement("UPDATE towns SET name = UPPER(name) WHERE country = ?");

        //Read country name
        String countryName = scanner.nextLine();

        preparedStatement.setString(1, countryName);


        if (connection.createStatement().
                executeQuery(String.format("SELECT * FROM towns WHERE country = '%s'", countryName)).next()) {
            //If the country is present in DB

            //Update towns names
            preparedStatement.execute();

            //Get towns count
            PreparedStatement countStatement = connection.prepareStatement
                    ("SELECT COUNT(name) AS `count` FROM towns WHERE country = ? GROUP BY country;");

            countStatement.setString(1, countryName);
            ResultSet countResSet = countStatement.executeQuery();
            countResSet.next();

            System.out.println(countResSet.getInt(1) + " town names were affected. ");

            //Get affected towns
            PreparedStatement prstmtTownsAffected = connection.prepareStatement
                    ("SELECT name, COUNT(*) AS `count` FROM towns WHERE country = ? GROUP BY name");

            prstmtTownsAffected.setString(1, countryName);
            ResultSet townsAffected = prstmtTownsAffected.executeQuery();

            List<String> res = new ArrayList<>();
            while (townsAffected.next()) {
                res.add(townsAffected.getString("name"));
            }

            String sb = "[" +
                    String.join(", ", res) +
                    "]";
            System.out.println(sb);

        } else {
            //If the country is not present in DB
            System.out.println("No town names were affected.");
        }

    }
}
