public class Pet implements Birthable {

    private String name;
    private String birthDate;

    public Pet(String name, String birthDay) {
        this.name = name;
        this.birthDate = birthDay;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getBirthDate() {
        return birthDate;
    }
}
