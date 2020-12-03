public class Main {

    public static void main(String[] args) {

        Car car = new CarFacadeBuilder().withType("Ford")
                .withColor("Green")
                .withCity("Plovdiv")
                .withStreet("Trakia")
                .withDoors(4)
                .build();

        System.out.println(car.toString());
    }
}
