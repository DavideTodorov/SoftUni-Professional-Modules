package appenders;

import layouts.Layout;

public class ConsoleAppender implements Appender {
    private Layout layout;

    public ConsoleAppender(Layout layout) {
        this.layout = layout;
    }


    @Override
    public void appendToConsole() {
        System.out.println(this.layout.format());
    }
}
