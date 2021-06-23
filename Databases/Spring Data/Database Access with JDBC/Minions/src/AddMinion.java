import java.sql.*;
import java.util.Scanner;

public class AddMinion {

    public static void main(String[] args) throws SQLException {
        /*      4. Add Minion       */

        Scanner scanner = new Scanner(System.in);

        String[] minionInfo = scanner.nextLine().split("\\s+");
        String[] villainInfo = scanner.nextLine().split("\\s+");

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", "root", "12345");

        Statement stmt = connection.createStatement();


        //Check if minion's town is present in the DB and if not add it
        if (!stmt.executeQuery(String.format("SELECT * from towns where name = '%s'", minionInfo[3])).next()) {
            stmt.execute(String.format("INSERT INTO towns(name) VALUES (%s)",
                    minionInfo[3]));

            System.out.printf("Town %s was added to the database.%n", minionInfo[3]);
        }

        //Check if villain is present in the DB and if not add him
        if (!stmt.executeQuery(String.format("SELECT * from villains where name = '%s'", villainInfo[1])).next()) {
            stmt.execute(String.format("INSERT INTO villains(name, evilness_factor) VALUES ('%s', 'evil')",
                    villainInfo[1]));

            System.out.printf("Villain %s was added to the database.%n", villainInfo[1]);
        }

        //Get minion town id
        ResultSet townIdResultSet = stmt.executeQuery(String.format("SELECT id FROM towns WHERE name = '%s'", minionInfo[3]));
        townIdResultSet.next();
        int townId = townIdResultSet.getInt(1);

        //Add minion to database
        stmt.execute(String.format("INSERT INTO minions(name, age, town_id) VALUES ('%s', %d, %d);",
                minionInfo[1], Integer.parseInt(minionInfo[2]), townId));

        System.out.printf("Successfully added %s to be minion of %s.%n",
                minionInfo[1], villainInfo[1]);

        //Get new minion ID
        ResultSet newMinionResSet =
                stmt.executeQuery("SELECT id FROM  minions ORDER BY id DESC LIMIT 1");
        newMinionResSet.next();
        int newMinionId = newMinionResSet.getInt("id");


        //Get new villain ID
        ResultSet newVillainResSet =
                stmt.executeQuery("SELECT id FROM  villains ORDER BY id DESC LIMIT 1");
        newVillainResSet.next();
        int newVillainId = newVillainResSet.getInt("id");

        //Create connection between new villain and new minion
        stmt.execute(String.format("INSERT INTO minions_villains VALUES (%d, %d)",
                newMinionId, newVillainId));
    }
}
