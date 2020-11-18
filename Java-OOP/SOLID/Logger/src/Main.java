import controllers.*;
import enums.ReportLevel;
import interfaces.Appender;
import interfaces.Layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Appender> appenders = createAllAppenders(scanner);
        

    }





    private static List<Appender> createAllAppenders(Scanner scanner) {
        List<Appender> appenders = new ArrayList<>();

        int appendersCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < appendersCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            String appenderType = tokens[0];
            Layout layout = tokens[1].equals("SimpleLayout")
                    ? new SimpleLayout()
                    : new XmlLayout();


            Appender appender;
            if (appenderType.equals("ConsoleAppender")) {
                appender = new ConsoleAppender(layout);
                if (tokens.length == 3) {
                    ReportLevel reportLevel = ReportLevel.valueOf(tokens[2].toUpperCase());
                    appender.setReportLevel(reportLevel);
                }
            } else {
                appender = new FileAppender(layout, new LogCustomFile());
                if (tokens.length == 3) {
                    ReportLevel reportLevel = ReportLevel.valueOf(tokens[2].toUpperCase());
                    appender.setReportLevel(reportLevel);
                }
            }

            appenders.add(appender);
        }

        return appenders;
    }
}
