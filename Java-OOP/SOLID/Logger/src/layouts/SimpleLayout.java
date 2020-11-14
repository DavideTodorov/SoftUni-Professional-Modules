package layouts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SimpleLayout implements Layout {
    public SimpleLayout() {
        this.format();
    }

    @Override
    public String format() {
        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
        String dateString2 = dateFormat2.format(new Date());
        System.out.println("Current date and time in AM/PM: "+dateString2);

        return String.format("string");
    }
}
