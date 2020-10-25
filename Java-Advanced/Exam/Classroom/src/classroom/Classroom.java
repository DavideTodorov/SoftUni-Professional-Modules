package classroom;

import java.util.ArrayList;
import java.util.List;

public class Classroom {

    public int capacity;
    public List<Student> students;

    public Classroom(int capacity) {
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public int getStudentCount() {
        return this.students.size();
    }

    public String registerStudent(Student student) {
        if (this.students.size() >= this.capacity) {
            return "No seats in the classroom";

        } else if (this.students.contains(student)) {
            return "Student is already in the classroom";

        } else {
            this.students.add(student);
            return String.format("Added student %s %s",
                    student.getFirstName(), student.getLastName());
        }
    }

    public String dismissStudent(Student student) {
        if (this.students.contains(student)) {
            this.students.remove(student);

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
        for (Student student : this.students) {
            if (student.getBestSubject().equals(subject)) {
                students.append(String.format("%s %s",
                        student.getFirstName(), student.getLastName()))
                        .append(System.lineSeparator());
                hasStudents = true;
            }
        }

        if (!hasStudents) {

            return "No students enrolled for the subject";
        } else {
            result.append("Students: ").append(System.lineSeparator());
            result.append(students);
            return result.toString().trim();
        }
    }

    public Student getStudent(String firstName, String secondName) {
        Student student = null;

        for (Student currStudent : this.students) {
            if (currStudent.getFirstName().equals(firstName) && currStudent.getLastName().equals(secondName)) {
                student = currStudent;
                break;
            }
        }

        return student;
    }

    public String getStatistics() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Classroom size: %d", this.students.size())).append(System.lineSeparator());

        for (Student student : this.students) {
            result.append(String.format("==Student: First Name= %s, Last Name= %s, Best Subject= %s",
                    student.getFirstName(), student.getLastName(), student.getBestSubject()))
                    .append(System.lineSeparator());
        }


        return result.toString().trim();
    }
}
