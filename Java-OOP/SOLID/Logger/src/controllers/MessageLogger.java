package controllers;

import enums.ReportLevel;
import interfaces.Appender;
import interfaces.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageLogger implements Logger {
    private List<Appender> appenders;

    public MessageLogger(Appender... appenders) {
        this.appenders = new ArrayList<>(Arrays.asList(appenders));
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
        this.logMessage(date, ReportLevel.WARNING, message);
    }

    @Override
    public void logError(String date, String message) {
        this.logMessage(date, ReportLevel.ERROR, message);
    }

    @Override
    public void logCritical(String date, String message) {
        this.logMessage(date, ReportLevel.CRITICAL, message);
    }

    @Override
    public void logFatal(String date, String message) {
        this.logMessage(date, ReportLevel.FATAL, message);
    }


    private void logMessage(String date, ReportLevel reportLevel, String message) {
        for (Appender appender : appenders) {
            appender.append(date, reportLevel, message);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Appender appender : appenders) {
            builder.append(appender.toString()).append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
