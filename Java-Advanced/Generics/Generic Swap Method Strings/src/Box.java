import java.util.ArrayList;
import java.util.List;

public class Box<T> {

    private List<T> collection;

    public Box() {
        this.collection = new ArrayList<>();
    }

    public void add(T element) {
        this.collection.add(element);
    }

    public void swap(int indexOne, int indexTwo) {
        if (indexOne >= 0 && indexOne < collection.size() &&
                indexTwo >= 0 && indexTwo < collection.size()) {

            T firstElement = collection.get(indexOne);
            collection.set(indexOne, (T) collection.get(indexTwo));
            collection.set(indexTwo, firstElement);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T t : collection) {
            String clazz = t.getClass().toString().substring(6);
            sb.append(clazz).append(": ").append(t).append("\n");
        }
        return sb.toString();
    }
}
