import controllers.ConsoleAppender;
import controllers.MessageLogger;
import controllers.SimpleLayout;
import controllers.XmlLayout;
import interfaces.Appender;
import interfaces.Layout;
import interfaces.Logger;

public class Main {

    public static void main(String[] args) {
        Layout xmlLayout = new XmlLayout();
        Appender consoleAppender = new ConsoleAppender(xmlLayout);
        Logger logger = new MessageLogger(consoleAppender);

        logger.logError("3/26/2015 2:08:11 PM", "Error parsing JSON.");
        logger.logInfo("3/26/2015 2:08:11 PM", "User Pesho successfully registered.");

    }
}
