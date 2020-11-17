package interfaces;

import enums.ReportLevel;

public abstract class Appender {
    private Layout layout;
    private int messagesCount;
    private ReportLevel reportLevel;


    public Appender(Layout layout, ReportLevel reportLevel) {
        this.layout = layout;
        this.messagesCount = 0;
    }

    public Appender(Layout layout) {
        this(layout, ReportLevel.INFO);
    }

    protected Layout getLayout() {
        return layout;
    }

    protected void incrementMessagesCount() {
        this.messagesCount++;
    }

    public void setReportLevel(ReportLevel reportLevel) {
        this.reportLevel = reportLevel;
    }

    public abstract void append(String date, ReportLevel reportLevel, String message);
}