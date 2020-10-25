package classroom;

import java.util.ArrayList;
import java.util.List;

public class Classroom {

    private int capacity;
    private List<Student> data;

    public Classroom(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getStudents() {
        return this.data;
    }

    public int getStudentCount() {
        return this.data.size();
    }

    public String registerStudent(Student student) {
        if (this.data.size() >= this.capacity) {
            return "No seats in the classroom";

        } else if (this.data.contains(student)) {
            return "Student is already in the classroom";

        } else {
            this.data.add(student);
            return String.format("Added student %s %s",
                    student.getFirstName(), student.getLastName());
        }
    }

    public String dismissStudent(Student student) {
        if (this.data.contains(student)) {
            this.data.remove(student);

            return String.format("Removed student %s %s",
                    student.getFirstName(), student.getLastName());

        } else {
            return "Student not found";
        }
    }

    public String getSubjectInfo(String subject) {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Subject: %s", subject)).append(System.lineSeparator());

        boolean hasStudents = false;

        StringBuilder students = new StringBuilder();
        for (Student student : this.data) {
            if (student.getBestSubject().equals(subject)) {
                students.append(String.format("%s %s",
                        student.getFirstName(), student.getLastName()))
                        .append(System.lineSeparator());
                hasStudents = true;
            }
        }

        if (!hasStudents) {
            result.append("No students enrolled for the subject").append(System.lineSeparator());

        } else {
            result.append("Students:").append(System.lineSeparator());
            result.append(students);
        }

        return result.toString().trim();
    }

    public Student getStudent(String firstName, String secondName) {
        Student student = null;

        for (Student currStudent : this.data) {
            if (currStudent.getFirstName().equals(firstName) && currStudent.getLastName().equals(secondName)) {
                student = currStudent;
                break;
            }
        }

        return student;
    }

    public String getStatistics() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Classroom size: %d", this.data.size())).append(System.lineSeparator());

        for (Student student : this.data) {
            result.append(String.format("==Student: First Name= %s , Last Name= %s , Best Subject= %s",
                    student.getFirstName(), student.getLastName(), student.getBestSubject()))
                    .append(System.lineSeparator());
        }


        return result.toString().trim();
    }
}
