public class Person implements Comparable<Person> {

    private String name;
    private int age;
    private String city;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getCity() {
        return this.city;
    }

    @Override
    public int compareTo(Person otherPerson) {
        int result = this.name.compareTo(otherPerson.getName());

        if (result == 0) {
            result = Integer.compare(this.getAge(), otherPerson.getAge());
            if (result == 0) {
                result = this.city.compareTo(otherPerson.getCity());
            }
        }
        return result;
    }
}