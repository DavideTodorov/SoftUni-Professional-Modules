package animals;

public class Animal {

    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getGender() {
        return this.gender;
    }

    protected String produceSound() {
        return "";
    }

    public String asString() {
        StringBuilder result = new StringBuilder();

        result.append(this.getClass().getSimpleName()).append(System.lineSeparator());

        result.append(String.format("%s %d %s", this.getName(), this.getAge(), this.getGender()))
                .append(System.lineSeparator());

        result.append(this.produceSound());

        return result.toString();
    }
}