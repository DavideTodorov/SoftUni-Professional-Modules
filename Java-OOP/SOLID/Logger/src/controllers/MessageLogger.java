package controllers;

import enums.ReportLevel;
import interfaces.Appender;
import interfaces.Logger;

import java.util.ArrayList;
import java.util.List;

public class MessageLogger implements Logger {
    private static List<Appender> appenders = new ArrayList<>();

    public MessageLogger(Appender appender) {
        appenders.add(appender);
    }

    public void addAppender(Appender appender) {
        this.appenders.add(appender);
    }

    @Override
    public void logInfo(String date, String message) {
        this.logMessage(date, ReportLevel.INFO, message);
    }

    @Override
    public void logWarning(String date, String message) {

    }

    @Override
    public void logError(String date, String message) {
        this.logMessage(date, ReportLevel.ERROR, message);
    }

    @Override
    public void logCritical(String date, String message) {

    }

    @Override
    public void logFatal(String date, String message) {

    }

    private void logMessage(String date, ReportLevel reportLevel, String message) {
        for (Appender appender : appenders) {
            appender.append(date, reportLevel, message);
        }
    }
}
