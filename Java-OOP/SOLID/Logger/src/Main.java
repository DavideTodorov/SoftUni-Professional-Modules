import controllers.*;
import interfaces.Appender;
import interfaces.CustomFile;
import interfaces.Layout;
import interfaces.Logger;

import java.io.File;

public class Main {

    public static void main(String[] args) {
//        Layout xmlLayout = new XmlLayout();
//        Appender consoleAppender = new ConsoleAppender(xmlLayout);
//        Logger logger = new MessageLogger(consoleAppender);
//
//        logger.logError("3/26/2015 2:08:11 PM", "Error parsing JSON.");
//        logger.logInfo("3/26/2015 2:08:11 PM", "User Pesho successfully registered.");
//

        CustomFile file = new LogCustomFile();

        Layout layout = new XmlLayout();
        Appender fileAppender = new FileAppender(layout, file);

        Logger logger = new MessageLogger(fileAppender);

        logger.logInfo("date", "message");
    }
}
