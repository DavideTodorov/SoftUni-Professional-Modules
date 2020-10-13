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

    //Sorter
    public static <T extends Comparable<T>> CustomList<T> sort(CustomList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            int minPosition = -1;
            T minValue = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                T secondPosition = list.get(j);
                if (minValue.compareTo(secondPosition) > 0) {
                    minPosition = j;
                    minValue = list.get(j);
                }
            }
            if (minPosition >= 0) {
                swap(list, i, minPosition);
            }
        }

        return list;
    }

    private static <T extends Comparable<T>> void swap(CustomList<T> sequence,
                                                       int positionOne, int positionTwo) {
        T temp = sequence.get(positionOne);
        sequence.set(positionOne, sequence.get(positionTwo));
        sequence.set(positionTwo, temp);
    }

    private int size() {
        return this.listOfElements.size();
    }

    private void set(int index, T element) {
        this.listOfElements.set(index, element);
    }

    private T get(int index) {
        return this.listOfElements.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.listOfElements.size(); i++) {
            T currElement = this.listOfElements.get(i);
            if (i == this.listOfElements.size() - 1){
                sb.append(currElement);
                break;
            }
            sb.append(currElement).append("\n");
        }

        return sb.toString();
    }
}