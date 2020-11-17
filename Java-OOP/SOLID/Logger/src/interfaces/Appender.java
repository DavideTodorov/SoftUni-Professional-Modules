package interfaces;

public abstract class Appender {
    private Layout layout;
    private int messagesCount;


    public Appender(Layout layout) {
        this.layout = layout;
        this.messagesCount = 0;
    }

    protected Layout getLayout() {
        return layout;
    }

    protected void incrementMessagesCount() {
        this.messagesCount++;
    }

    protected abstract void append(String date, String reportLevel, String message);
}
