import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GenericCount<T extends Comparable<T>> {

    private List<T> listOfElements;

    public GenericCount(List<T> listOfElements) {
        this.listOfElements = new ArrayList<>();
        this.listOfElements = listOfElements;
    }

    public int compare(T element) {
        return (int) listOfElements.stream()
                .filter(x -> x.compareTo(element) > 0)
                .count();
    }
}