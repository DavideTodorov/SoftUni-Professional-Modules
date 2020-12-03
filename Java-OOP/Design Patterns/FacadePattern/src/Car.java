public class Car {
    private String type;
    private String color;
    private String city;
    private String street;
    private int countOfDoors;

    public Car() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountOfDoors() {
        return countOfDoors;
    }

    public void setCountOfDoors(int countOfDoors) {
        this.countOfDoors = countOfDoors;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    @Override
    public String toString() {
        return String.format("Car type: %s%n" +
                "Car color: %s%n" +
                "Car city: %s%n" +
                "Car street: %s%n" +
                "Car doors: %s", type, color, city, street, countOfDoors);
    }
}
