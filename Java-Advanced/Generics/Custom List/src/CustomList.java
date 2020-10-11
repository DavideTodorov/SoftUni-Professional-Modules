import java.util.ArrayList;
import java.util.List;

public class CustomList<T extends Comparable<T>> {

    private List<T> listOfElements;

    public CustomList() {
        this.listOfElements = new ArrayList<>();
    }

    public void add(T element) {
        this.listOfElements.add(element);
    }

    public T remove(int index) {
        if (index >= 0 && index < this.listOfElements.size()) {
            return this.listOfElements.remove(index);
        }
        return null;
    }

    public boolean contains(T element) {
        return this.listOfElements.contains(element);
    }

    public void swap(int indexOne, int indexTwo) {
        if (indexOne >= 0 && indexOne < this.listOfElements.size() &&
                indexTwo >= 0 && indexTwo < this.listOfElements.size()) {

            T firstElement = this.listOfElements.get(indexOne);
            this.listOfElements.set(indexOne, this.listOfElements.get(indexTwo));
            this.listOfElements.set(indexTwo, firstElement);
        }
    }

    public int countGreaterThan(T element) {
        int count = 0;
        for (T currElement : listOfElements) {
            if (currElement.compareTo(element) > 0) {
                count++;
            }
        }
        return count;
    }

    public T getMax() {
        return this.listOfElements.stream()
                .max(Comparable::compareTo)
                .orElseThrow(IllegalArgumentException::new);
    }

    public T getMin() {
        return this.listOfElements.stream()
                .min(Comparable::compareTo)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T currElement : listOfElements) {
            sb.append(currElement).append("\n");
        }
        return sb.toString();
    }
}