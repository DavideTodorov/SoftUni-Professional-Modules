import java.util.Comparator;

public class PersonNameComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        int result = person1.getName().compareTo(person2.getName());

        if (result == 0) {
            result = Integer.compare(person1.getAge(), person2.getAge());
        }

        return result;
    }
}
