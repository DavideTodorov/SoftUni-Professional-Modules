import java.util.*;
import java.util.function.Consumer;

public class Stack implements Iterable<Integer> {

    private List<Integer> stack;

    public Stack() {
        this.stack = new ArrayList<>();
    }

    public void push(int... integers) {
        if (integers.length >= 1) {
            for (int i = 0; i < integers.length; i++) {
                stack.add(integers[i]);
            }
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.remove(stack.size() - 1);
        } else {
            System.out.println("No elements");
        }
    }


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < stack.size();
            }

            @Override
            public Integer next() {
                return stack.get(index++);
            }
        };
    }

}
