import java.util.ArrayList;
import java.util.List;

public class Box<T> {

    private List<T> text;

    public Box() {
        this.text = new ArrayList<>();
    }

    public void add(T element) {
        this.text.add(element);
    }

    public void swap(int indexOne, int indexTwo) {
        if (indexOne >= 0 && indexOne < text.size() &&
                indexTwo >= 0 && indexTwo < text.size()) {

            T firstElement = text.get(indexOne);
            text.set(indexOne, (T) text.get(indexTwo));
            text.set(indexTwo, firstElement);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T t : text) {
            sb.append("java.lang.String: ").append(t).append("\n");
        }
        return sb.toString();
    }
}
