import appenders.Appender;
import appenders.ConsoleAppender;
import layouts.Layout;
import layouts.SimpleLayout;
import loggers.Logger;
import loggers.MessageLogger;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        Layout simpleLayout = new SimpleLayout();

        Appender consoleAppender = new ConsoleAppender(simpleLayout);


//        Appender fileAppender = new FileAppender(simpleLayout);
//        File file = new LogFile();
//        ((FileAppender) fileAppender).setFile(file);


        Logger logger = new MessageLogger(consoleAppender);


        logger.logError("3/26/2015 2:08:11 PM", "Error parsing JSON.");

        logger.logInfo("3/26/2015 2:08:11 PM", "User Pesho successfully registered.");
    }
}
