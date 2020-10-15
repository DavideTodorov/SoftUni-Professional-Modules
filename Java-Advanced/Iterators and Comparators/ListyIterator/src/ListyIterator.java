import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ListyIterator implements Iterable<String> {

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

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            int iteratorIndex = 0;

            @Override
            public boolean hasNext() {
                return iteratorIndex < list.size();
            }

            @Override
            public String next() {
                return list.get(iteratorIndex++);
            }
        };
    }

    public static void PrintAll(ListyIterator list) {
        for (String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
