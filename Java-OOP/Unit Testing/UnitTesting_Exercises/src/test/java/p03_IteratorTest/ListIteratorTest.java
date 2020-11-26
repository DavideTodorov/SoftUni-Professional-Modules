package p03_IteratorTest;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {
    private ListIterator listIterator;

    @Before
    public void setUp() throws OperationNotSupportedException {
        listIterator = new ListIterator("1", "2", "3");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testCreatingListIteratorWithNullParam() throws OperationNotSupportedException {
        ListIterator listIteratorTest = new ListIterator(null);
    }

    @Test
    public void testIfMoveWorksCorrectly() throws OperationNotSupportedException {
        boolean move = listIterator.move();

        assertTrue(move);
    }

    @Test
    public void testHasNextWithFirstValidIndex() {
        boolean hasNext = listIterator.hasNext();
        assertTrue(hasNext);
    }

    @Test
    public void testHasNextWithLastIndex() throws OperationNotSupportedException {
        listIterator = new ListIterator( "3");

        boolean hasNext = listIterator.hasNext();
        assertFalse(hasNext);
    }

    @Test(expected = IllegalStateException.class)
    public void testCallingPrintOnEmptyListIterator() throws OperationNotSupportedException {
        listIterator = new ListIterator();

        String print = listIterator.print();
        assertEquals("Invalid Operation!",print);
    }

    @Test
    public void testCallingPrintOnCurrInternalIndex(){
        String print = listIterator.print();

        assertEquals("1", print);
    }

}