import java.sql.*;

public class PrintAllMinionNames {

    public static void main(String[] args) throws SQLException {
        /*      7. Print All Minion Names      */

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", "root", "12345");

        PreparedStatement minionStatement = connection.prepareStatement("SELECT name FROM minions WHERE id = ?");

        int firstIndex = 1;
        int lastIndex = 50;

        while (firstIndex <= lastIndex) {
            //Print the minion from the beginning
            minionStatement.setInt(1, firstIndex);
            ResultSet firstMinion = minionStatement.executeQuery();
            firstMinion.next();
            System.out.println(firstMinion.getString(1));

            if (firstIndex < lastIndex) {
                //Print the minion from the end
                minionStatement.setInt(1, lastIndex);
                ResultSet lastMinion = minionStatement.executeQuery();
                lastMinion.next();
                System.out.println(lastMinion.getString(1));
            }

            firstIndex++;
            lastIndex--;
        }
    }
}
