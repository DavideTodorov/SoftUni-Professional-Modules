import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Person> people = new LinkedHashMap<>();

        String input = scanner.nextLine();
        Person person;
        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");

            String name = tokens[0];
            String className = tokens[1];


            if (!people.containsKey(name)) {
                person = new Person(name);
            } else {
                person = people.get(name);
            }

            switch (className) {
                case "company":
                    String companyName = tokens[2];
                    String department = tokens[3];
                    double salary = Double.parseDouble(tokens[4]);

                    addCompany(person, companyName, department, salary);
                    break;

                case "pokemon":
                    String pokemonName = tokens[2];
                    String pokemonType = tokens[3];

                    addPokemon(person, pokemonName, pokemonType);
                    break;

                case "parents":
                    String parentName = tokens[2];
                    String parentBirthday = tokens[3];

                    addParent(person, parentName, parentBirthday);
                    break;

                case "children":
                    String childName = tokens[2];
                    String childBirthday = tokens[3];

                    addChild(person, childName, childBirthday);
                    break;

                case "car":
                    String carModel = tokens[2];
                    int carSpeed = Integer.parseInt(tokens[3]);
                    addCar(person, carModel, carSpeed);
                    break;
            }

            people.put(name, person);
            input = scanner.nextLine();
        }

        String personToPrint = scanner.nextLine();
        person = people.get(personToPrint);

        System.out.println(person.toString());
    }

    //only one
    private static void addCompany(Person person, String companyName, String department, double salary) {
        Company company = new Company(companyName, department, salary);
        person.setCompany(company);
    }

    private static void addCar(Person person, String carModel, int carSpeed) {
        Car car = new Car(carModel, carSpeed);
        person.setCar(car);
    }

    //one or more
    private static void addChild(Person person, String childName, String childBirthday) {
        Child child = new Child(childName, childBirthday);
        person.addChild(child);
    }

    private static void addParent(Person person, String parentName, String parentBirthday) {
        Parent parent = new Parent(parentName, parentBirthday);
        person.addParent(parent);
    }

    private static void addPokemon(Person person, String pokemonName, String pokemonType) {
        Pokemon pokemon = new Pokemon(pokemonName, pokemonType);
        person.addPokemon(pokemon);
    }
}