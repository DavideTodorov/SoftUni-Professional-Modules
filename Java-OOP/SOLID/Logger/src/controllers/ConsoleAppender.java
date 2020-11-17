package controllers;

import interfaces.Appender;
import interfaces.Layout;

public class ConsoleAppender extends Appender {

    public ConsoleAppender(Layout layout) {
        super(layout);
    }

    @Override
    protected void append(String date, String reportLevel, String message) {
        super.incrementMessagesCount();
        System.out.println(this.getLayout().format(date, reportLevel, message));
    }
}
