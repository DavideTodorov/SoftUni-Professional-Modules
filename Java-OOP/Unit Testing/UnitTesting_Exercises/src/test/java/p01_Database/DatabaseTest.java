package p01_Database;

import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    @Test(expected = OperationNotSupportedException.class)
    public void testCreateDatabaseWithMoreThanSixteenElements() throws OperationNotSupportedException {
        Database database =
                new Database(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);

        int elementsCount = database.getElementsCount();

        assertEquals(0, elementsCount);
    }

    @Test
    public void testAddElementToDatabase() throws OperationNotSupportedException {
        Database database = new Database(1, 2, 3, 4);

        database.add(1);
        Integer[] elements = database.getElements();
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 1}, elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveFromInvalidIndex() throws OperationNotSupportedException {
        Database database = new Database();

        database.remove();
        assertArrayEquals(new Integer[]{}, database.getElements());
    }


}