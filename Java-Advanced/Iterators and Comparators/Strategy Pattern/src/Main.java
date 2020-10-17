import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeSet<Person> peopleSortedByName = new TreeSet<>(new PersonNameComparator());
        TreeSet<Person> peopleSortedByAge = new TreeSet<>(new PersonAgeComparator());

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] personInfo = scanner.nextLine().split("\\s+");

            Person person = new Person(personInfo[0], Integer.parseInt(personInfo[1]));

            peopleSortedByName.add(person);
            peopleSortedByAge.add(person);
        }

        for (Person person : peopleSortedByName) {
            System.out.println(person);
        }

        for (Person person : peopleSortedByAge) {
            System.out.println(person);
        }
    }
}