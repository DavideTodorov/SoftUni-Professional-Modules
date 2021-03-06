package p02_ExtendedDatabase;

import org.junit.Test;
import org.mockito.Mockito;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    @Test(expected = OperationNotSupportedException.class)
    public void testFindingPeopleWithTheSameId() throws OperationNotSupportedException {
        Person person = Mockito.mock(Person.class);
        Person person2 = Mockito.mock(Person.class);

        Mockito.when(person.getId()).thenReturn(13);
        Mockito.when(person2.getId()).thenReturn(13);

        Database database = new Database(person, person2);

        Person byId = database.findById(13);

        assertNull(byId);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testTryRemoveFromEmptyDatabase() throws OperationNotSupportedException {
        Database database = new Database();
        database.remove();
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testFindingNotExistingUsername() throws OperationNotSupportedException {
        Person person1 = Mockito.mock(Person.class);
        Person person2 = Mockito.mock(Person.class);
        Person person3 = Mockito.mock(Person.class);

        Mockito.when(person1.getUsername()).thenReturn("Name1");
        Mockito.when(person2.getUsername()).thenReturn("Name2");
        Mockito.when(person3.getUsername()).thenReturn("Name3");

        Database database = new Database(person1, person2, person3);

        Person personFound = database.findByUsername("Name4");

        assertNull(personFound);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testFindingByNullUsername() throws OperationNotSupportedException {
        Database database = new Database(new Person(10, "Name"));
        database.findByUsername(null);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testFindingByNotExistingId() throws OperationNotSupportedException {
        Person person1 = Mockito.mock(Person.class);
        Person person2 = Mockito.mock(Person.class);

        Mockito.when(person1.getId()).thenReturn(1);
        Mockito.when(person2.getId()).thenReturn(2);

        Database database = new Database(person1, person2);

        database.findById(3);
    }


    @Test
    public void testFindingBYExistingId() throws OperationNotSupportedException {
        Person person1 = Mockito.mock(Person.class);
        Person person2 = Mockito.mock(Person.class);

        Mockito.when(person1.getId()).thenReturn(1);
        Mockito.when(person2.getId()).thenReturn(2);

        Database database = new Database(person1, person2);

        Person byId = database.findById(2);

        assertEquals(person2, byId);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testAddingNullToDatabase() throws OperationNotSupportedException {
        Database database = new Database(new Person(13, "Name"));
        database.add(null);
    }


    @Test
    public void testAddingValidPersonToDatabase() throws OperationNotSupportedException {
        Database database = new Database(new Person(13, "Name"));
        Person person = new Person(72, "Name2");
        database.add(person);
        Person[] elements = database.getElements();
        assertEquals(person, elements[1]);
    }


    @Test
    public void testRemovingPeopleFromDatabase() throws OperationNotSupportedException {
        Database database =
                new Database(new Person(1, "Name1"), new Person(2, "Name2"));

        database.remove();
        int length = database.getElements().length;
        assertEquals(1, length);

        database.remove();
        length = database.getElements().length;
        assertEquals(0, length);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testRemovingWhenThereAreNoPeopleLeftInDatabase() throws OperationNotSupportedException {
        Database database =
                new Database(new Person(1, "Name1"), new Person(2, "Name2"));

        database.remove();
        int length = database.getElements().length;
        assertEquals(1, length);

        database.remove();
        length = database.getElements().length;
        assertEquals(0, length);

        database.remove();
    }


    @Test
    public void testFindExistingPersonByUsername() throws OperationNotSupportedException {
        Person person = new Person(13, "Name");
        Person person2 = new Person(42, "Name2");
        Person person3 = new Person(72, "Name3");

        Database database = new Database(person, person2, person3);

        Person foundPerson1 = database.findByUsername("Name");
        Person foundPerson2 = database.findByUsername("Name3");

        assertEquals(person, foundPerson1);
        assertEquals(person3, foundPerson2);
    }
}