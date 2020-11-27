package p04_BubbleSortTest;

import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleTest {

    @Test
    public void testSort() {
        int[] arr = {10, 13, -5, 0, 7, 2};
        Bubble.sort(arr);
        int[] sortedArrExpected = {-5, 0, 2, 7, 10, 13};
        assertArrayEquals(sortedArrExpected, arr);
    }
}