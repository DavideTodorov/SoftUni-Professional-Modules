import java.util.*;
import java.util.function.Consumer;

public class Stack implements Iterable<Integer> {

    private ArrayDeque<Integer> stack;

    public Stack() {
        this.stack = new ArrayDeque<>();
    }

    public void push(int... integers) {
        if (integers.length >= 1) {
            for (int i = 0; i < integers.length; i++) {
                stack.offer(integers[i]);
            }
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pollLast();
        } else {
            System.out.println("No elements");
        }
    }

    public int size(){
        return this.stack.size();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int index = stack.size();

            @Override
            public boolean hasNext() {
                return index > 0;
            }

            @Override
            public Integer next() {
                index = index - 1;
                return stack.pollLast();
            }
        };
    }
}
