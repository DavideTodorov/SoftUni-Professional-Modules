package loggers;


import appenders.Appender;

public class MessageLogger implements Logger {

    private Appender[] appender;

    public MessageLogger(Appender... appender) {
        this.appender = appender;
    }

    @Override
    public void logError(String dateAndTime, String error) {

    }

    @Override
    public void logInfo(String dateAndTime, String info) {

    }
}
