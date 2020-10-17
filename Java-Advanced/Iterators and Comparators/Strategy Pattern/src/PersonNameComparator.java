import java.util.Comparator;

public class PersonNameComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        int result = Integer.compare(person1.getName().length(), person2.getName().length());

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