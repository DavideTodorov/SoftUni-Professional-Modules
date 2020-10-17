import java.util.*;
import java.util.function.Consumer;

public class Stack implements Iterable<Integer> {

    private ArrayList<Integer> stack;

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

    public boolean pop() {
        if (!stack.isEmpty()) {
            stack.remove(stack.size() - 1);
            return true;
        } else {
            System.out.println("No elements");
            return false;
        }
    }

    public int size(){
        return this.stack.size();
    }

    public void reverse(){
        Collections.reverse(this.stack);
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
