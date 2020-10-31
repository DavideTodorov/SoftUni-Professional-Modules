import java.util.ArrayList;

public class StackOfStrings {

    private ArrayList<String> data;

    public void push(String string) {
        this.data.add(0, string);
    }

    public String pop() {
        return this.data.remove(0);
    }

    public String peek() {
        String peeked = this.data.remove(0);
        this.data.add(0, peeked);
        return peeked;
    }
}
