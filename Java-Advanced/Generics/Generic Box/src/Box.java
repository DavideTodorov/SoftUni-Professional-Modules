import java.util.ArrayList;
import java.util.List;

public class Box<String> {

    private List<String> text;

    public Box() {
        text = new ArrayList<>();
    }

    public void add(String text) {
        this.text.add(text);
    }

    @Override
    public java.lang.String toString() {
        StringBuilder sb = new StringBuilder();
        for (String string : text) {
            sb.append("java.lang.String: ").append(string).append("\n");
        }
        return sb.toString();
    }
}
