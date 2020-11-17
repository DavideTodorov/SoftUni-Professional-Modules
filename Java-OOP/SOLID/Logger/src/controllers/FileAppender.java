package controllers;

import enums.ReportLevel;
import interfaces.Appender;
import interfaces.CustomFile;
import interfaces.Layout;

public class FileAppender extends Appender {
    private CustomFile file;

    public FileAppender(Layout layout, CustomFile file) {
        super(layout);
        this.file = file;
    }

    @Override
    public void append(String date, ReportLevel reportLevel, String message) {
        this.incrementMessagesCount();
        this.file.write(
                super.getLayout().format(date, reportLevel, message)
        );
    }
}
