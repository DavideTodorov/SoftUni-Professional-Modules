package classroom;

public class Student {

    private String firstName;
    private String lastName;
    private String bestSubject;

    public Student(String firstName, String lastName, String bestSubject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bestSubject = bestSubject;
    }

    @Override
    public String toString() {
        return String.format("Student: First Name= %s, Last Name= %s, Best Subject= %s",
                this.firstName, this.lastName, this.bestSubject);
    }
}