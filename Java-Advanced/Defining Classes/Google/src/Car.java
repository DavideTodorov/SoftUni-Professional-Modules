public class Car {

    private String carModel;
    private int carSpeed;

    public Car(String carModel, int carSpeed) {
        this.carModel = carModel;
        this.carSpeed = carSpeed;
    }

    @Override
    public String toString() {
        return String.format("%s",
                this.carSpeed == 0 ? "" : String.format("%s %d\n", this.carModel, this.carSpeed));
    }
}