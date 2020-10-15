import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListyIterator  {

    private int index = 0;
    private List<String> list;

    public ListyIterator(String... elements) {
        this.list = Arrays.asList(elements);
    }

    public boolean hasNext() {

        return index < list.size() - 1;
    }

    public boolean move() {
        boolean isMoved = false;

        if (index < list.size() - 1) {
            index++;
            isMoved = true;
        }

        return isMoved;
    }

    public String print() {
        if (list.isEmpty() || index >= list.size()) {
            return "Invalid Operation!";
        }
        return list.get(index);
    }
}
