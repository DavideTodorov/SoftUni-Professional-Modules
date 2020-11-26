package p05_CustomLinkedList;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLinkedListTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSetElementToIncorrectIndex() {
        CustomLinkedList<String> linkedList = new CustomLinkedList<>();
        linkedList.set(1, "Test");
    }
}