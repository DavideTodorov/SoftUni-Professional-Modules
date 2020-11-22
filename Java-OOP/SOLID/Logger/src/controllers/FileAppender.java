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
        if (reportLevel.ordinal() >= super.getReportLevel().ordinal()) {
            this.file.write(
                    super.getLayout().format(date, reportLevel, message)
            );

            this.incrementMessagesCount();
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", File size: %d", this.file.getSize());
    }
}
