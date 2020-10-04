public class Car {

    private String brand;
    private String model;
    private int horsePower;

    public Car(String made, String model, int horsePower) {
        this.brand = made;
        this.model = model;
        this.horsePower = horsePower;
    }

    public Car() {
        this("n/a", "n/a", 0);
    }

    public void setBrand(String made) {
        this.brand = made;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getModel() {
        return this.model;
    }

    public int getHorsePower() {
        return this.horsePower;
    }
}
