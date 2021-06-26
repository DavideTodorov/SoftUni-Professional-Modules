import java.sql.*;

public class GetVillainsNames {

    public static void main(String[] args) throws SQLException {
        /*      2. Get Villains’ Names      */

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", "root", "12345");


        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery
                ("""
                        SELECT v.name, COUNT(DISTINCT mv.minion_id) AS minions_count
                        FROM  villains AS v
                        JOIN minions_villains mv
                        ON v.id = mv.villain_id
                        GROUP BY mv.villain_id
                        HAVING  minions_count > 15
                        ORDER BY minions_count DESC;""");

        while (resultSet.next()) {
            System.out.println(resultSet.getString
                    (1) + " " + resultSet.getString(2));
        }
    }
}
