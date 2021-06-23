import com.mysql.cj.x.protobuf.MysqlxExpr;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class IncreaseMinionsAge {

    public static void main(String[] args) throws SQLException {
        /*      8. Increase Minions Age        */ // --- TODO

        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");


        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", "root", "12345");


        PreparedStatement minionsStatement = connection.prepareStatement("SELECT if(id IN (?), lower(name), name),\n" +
                "       if(id IN (?), age + 1, age)\n" +
                "FROM minions;");


        // --- TODO
        /*
        minionsStatement.setArray(1, anInt);
        minionsStatement.setArray(2, anInt);

        ResultSet alteredMinions = minionsStatement.executeQuery();

        System.out.println(anInt);
        while (alteredMinions.next()) {
            System.out.println(alteredMinions.getString(1) + " " + alteredMinions.getInt(2));

        }
            */

    }
}
