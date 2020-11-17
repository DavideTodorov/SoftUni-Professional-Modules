package controllers;

import enums.ReportLevel;
import interfaces.Appender;
import interfaces.Layout;

public class ConsoleAppender extends Appender {

    public ConsoleAppender(Layout layout) {
        super(layout);
    }

    public ConsoleAppender(Layout layout, ReportLevel reportLevel) {
        super(layout, reportLevel);
    }

    @Override
    public void append(String date, ReportLevel reportLevel, String message) {
        super.incrementMessagesCount();
        System.out.println(this.getLayout().format(date, reportLevel, message));
    }
}
