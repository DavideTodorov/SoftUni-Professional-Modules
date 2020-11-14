import appenders.Appender;
import appenders.ConsoleAppender;
import layouts.SimpleLayout;

public class Main {

    public static void main(String[] args) {

        SimpleLayout layout = new SimpleLayout();
        Appender appender = new ConsoleAppender(layout);

        appender.appendToConsole();
    }
}
