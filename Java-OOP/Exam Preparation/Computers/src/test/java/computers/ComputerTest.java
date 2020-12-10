package computers;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class ComputerTest {
    private Computer computer;
    private Part part1;
    private Part part2;
    private Part part3;

    @Before
    public void setUp() {
        this.computer = new Computer("computer_name");
        this.part1 = new Part("part1", 10);
        this.part2 = new Part("part2", 20);
        this.part3 = new Part("part3", 30);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithInvalidName() {
        new Computer(null);
    }

    @Test
    public void testGetterForName() {
        assertEquals("computer_name", computer.getName());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetPartsAsUnmodifiableList() {
        computer.addPart(part1);
        computer.getParts().remove(0);
    }

    @Test
    public void testGetPartsAsCorrectUnmodifiableList() {
        computer.addPart(part1);
        computer.addPart(part2);
        computer.addPart(part3);
        List<Part> parts = computer.getParts();
        List<Part> expected = Arrays.asList(part1, part2, part3);
        assertEquals(expected, parts);
    }

    @Test
    public void testGetTotalPrice() {
        computer.addPart(part1);
        computer.addPart(part2);
        computer.addPart(part3);
        double totalPrice = computer.getTotalPrice();
        assertEquals(60, totalPrice, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullPart() {
        computer.addPart(null);
    }

    @Test
    public void testRemovePart() {
        computer.addPart(part1);
        boolean removePart = computer.removePart(part1);
        assertTrue(removePart);
    }

    @Test
    public void testGetPartThatIsNotPresent() {
        Part part = computer.getPart("part");
        assertNull(part);
    }

    @Test
    public void testGetPartThatIsPresent() {
        computer.addPart(part1);
        Part part = computer.getPart("part1");
        assertEquals(part1, part);
    }
}