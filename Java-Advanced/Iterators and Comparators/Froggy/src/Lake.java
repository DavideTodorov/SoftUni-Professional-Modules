import java.util.Iterator;

public class Lake implements Iterator<Integer> {
    private int[] numbers;
    private int index;


    public Lake(int[] numbers) {
        this.numbers = numbers;
    }


    @Override
    public boolean hasNext() {
        return this.index < this.numbers.length;
    }

    @Override
    public Integer next() {
        final int lastEvenIndex = numbers.length % 2 == 0 ? numbers.length - 2 : numbers.length - 1;

        if (index == lastEvenIndex) {
            int currNum = numbers[index];
            index = 1;
            return currNum;
        }


        int currNum = numbers[index];
        index += 2;
        return currNum;
    }
}