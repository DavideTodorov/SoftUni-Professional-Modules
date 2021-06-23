import java.sql.*;
import java.util.Scanner;

public class GetMinionNames {

    public static void main(String[] args) throws SQLException {
        /*      3. Get Minion Names     */

        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", "root", "12345");


        PreparedStatement preparedStatement = connection.prepareStatement
                ("""
                        SELECT v.name, m.name, m.age
                        FROM  villains AS v
                        JOIN minions_villains AS mv
                        ON v.id = mv.villain_id
                        JOIN minions AS m
                        ON m.id = mv.minion_id
                        WHERE  v.id = ?;""");

        int villainId = Integer.parseInt(scanner.nextLine());

        preparedStatement.setInt(1, villainId);

        ResultSet villainWithMinions = preparedStatement.executeQuery();

        if (villainWithMinions.next()) {
            System.out.println("Villain: " + villainWithMinions.getString(1));
            int minionIndex = 1;

            System.out.printf("%d. %s %d%n", minionIndex,
                    villainWithMinions.getString(2),
                    villainWithMinions.getInt(3));

            minionIndex++;

            while (villainWithMinions.next()) {
                System.out.printf("%d. %s %d%n", minionIndex,
                        villainWithMinions.getString(2),
                        villainWithMinions.getInt(3));

                minionIndex++;
            }

        } else {
            System.out.printf("No villain with ID %d exists in the database.%n", villainId);
        }
    }
}
