package loggers;

public interface Logger {

    void logError(String dateAndTime, String error);

    void logInfo(String dateAndTime, String info);
}
