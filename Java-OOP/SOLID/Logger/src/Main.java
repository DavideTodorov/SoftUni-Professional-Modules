import controllers.*;
import enums.ReportLevel;
import interfaces.Appender;
import interfaces.Layout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Appender> appenders = createAllAppenders(scanner);
        MessageLogger logger = new MessageLogger(appenders);


        String input = scanner.nextLine();
        while (!"END".equals(input)) {
            String[] tokens = input.split("\\|");

            ReportLevel reportLevel = ReportLevel.valueOf(tokens[0].toUpperCase());
            String date = tokens[1];
            String message = tokens[2];

            String reportLevelAsString = Character.toUpperCase(reportLevel.name().charAt(0)) +
                    reportLevel.name().substring(1).toLowerCase();

            Class<MessageLogger> clazz = MessageLogger.class;

            try {
                Method method = clazz.getMethod("log" + reportLevelAsString,
                        String.class, String.class);

                method.invoke(logger, date, message);
            } catch (NoSuchMethodException
                    | IllegalAccessException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }

            input = scanner.nextLine();
        }
        System.out.println("Logger info");

        System.out.println(logger.toString());
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
