package p05_CustomLinkedList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLinkedListTest {

    private CustomLinkedList<String> linkedList;

    @Before
    public void setUp(){
        linkedList = new CustomLinkedList<>();
    }


    //TESTS FOR GET METHOD
    @Test(expected = IllegalArgumentException.class)
    public void testGettingFromInvalidIndex() {
        String s = linkedList.get(1);
        assertNull(s);
    }

    @Test
    public void testGettingFromValidIndex() {
        String item1 = "Item1";
        String item2 = "Item2";
        String item3 = "Item3";

        linkedList.add(item1);
        linkedList.add(item2);
        linkedList.add(item3);

        String get = linkedList.get(1);
        assertEquals(item2, get);
    }


    //TESTS FOR SET METHOD
    @Test(expected = IllegalArgumentException.class)
    public void testSetElementToIncorrectIndex() {
        linkedList.set(1, "Test");
    }

    @Test
    public void testSetElementToValidIndex() {
        String item1 = "Item1";
        String item2 = "Item2";
        String item3 = "Item3";

        linkedList.add(item1);
        linkedList.add(item2);
        linkedList.add(item3);
        linkedList.set(2, "Replaced");

        assertEquals("Replaced", linkedList.get(2));
    }


    //TEST FOR ADD METHOD
    @Test
    public void testAddingNullElement(){

    }


}