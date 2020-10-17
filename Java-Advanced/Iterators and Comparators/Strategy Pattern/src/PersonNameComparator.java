import java.util.Comparator;

public class PersonNameComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        int result = person1.getName().compareTo(person2.getName());

        if (result == 0) {
            result = person1.getName().toLowerCase().charAt(0) - person2.getName().toLowerCase().charAt(0);

            if (result > 0) {
                result = 1;
            } else if (result < 0) {
                result = -1;
            }
        }

        return result;
    }
}
