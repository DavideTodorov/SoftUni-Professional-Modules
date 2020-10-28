import java.util.HashMap;
import java.util.Map;

public class StudentSystem {

    private Map<String, Student> students;

    public StudentSystem() {
        this.students = new HashMap<>();
    }

    public Map<String, Student> getStudents() {
        return this.students;
    }

    public void create(String[] arrayToParse) {
        String name = arrayToParse[1];
        int age = Integer.parseInt(arrayToParse[2]);
        double grade = Double.parseDouble(arrayToParse[3]);

        if (!students.containsKey(name)) {
            Student student = new Student(name, age, grade);
            students.put(name, student);
        }
    }

    public void show(String[] tokens) {
        String name = tokens[1];
        if (students.containsKey(name)) {
            Student student = students.get(name);

            StringBuilder result = new StringBuilder();

            result.append(String.format("%s is %s years old.", student.getName(), student.getAge()));

            appendTextForGrade(student, result);

            System.out.println(result.toString());
        }
    }

    private void appendTextForGrade(Student student, StringBuilder result) {
        if (student.getGrade() >= 5.00) {
            result.append(" Excellent student.");
        } else if (student.getGrade() < 5.00 && student.getGrade() >= 3.50) {
            result.append(" Average student.");
        } else {
            result.append(" Very nice person.");
        }
    }
}