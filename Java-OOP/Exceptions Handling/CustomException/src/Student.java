public class Student {

    private String name;
    private String email;

    public Student(String name, String email) {
        setName(name);
        setEmail(email);
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setName(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isAlphabetic(name.charAt(i))){
                throw new InvalidPersonNameException("Person's name can contain only alphabetical characters");
            }
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", name, email);
    }
}
