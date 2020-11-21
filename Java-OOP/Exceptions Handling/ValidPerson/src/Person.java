public class Person {

    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
    }

    private void setAge(int age) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age should be in the range [0...120]");
        }
        this.age = age;
    }

    private void setLastName(String lastName) {
        validateName(lastName, "last name");
        this.lastName = lastName;
    }

    private void setFirstName(String firstName) {
        validateName(firstName, "first name");
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("%s %s is %d years old.", firstName, lastName, age);
    }

    private void validateName(String firstName, String nameType) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("The %s cannot be null or empty", nameType));
        }
    }
}