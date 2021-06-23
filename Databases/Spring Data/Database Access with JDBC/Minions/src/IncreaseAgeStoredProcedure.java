import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {

    public static void main(String[] args) throws SQLException {
        /*      9.	Increase Age Stored Procedure       */

        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", "root", "12345");

        CallableStatement callableStatement = connection.prepareCall("{CALL usp_get_older(?)}");

        System.out.println("Enter minion id:");
        callableStatement.setInt(1, Integer.parseInt(scanner.nextLine()));
        callableStatement.execute();

    }
}
