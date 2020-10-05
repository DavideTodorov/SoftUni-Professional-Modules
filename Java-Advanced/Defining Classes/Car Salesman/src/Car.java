public class Car {

    private String carName;
    private String weight;
    private String color;
    private String engineModel;
    private String power;
    private String displacement;
    private String efficiency;

    public Car(String carName, String weight, String color, Engine engine) {
        this.carName = carName;
        this.weight = weight;
        this.color = color;
        this.engineModel = engine.getModel();
        this.power = engine.getPower();
        this.displacement = engine.getDisplacement();
        this.efficiency = engine.getEfficiency();
    }

    @Override
    public String toString() {
        //FordFocus:
        //V4-33:
        //Power: 140
        //Displacement: 28
        //Efficiency: B
        //Weight: 1300
        //Color: Silver
        return String.format("%s:%n" +
                        "%s:%n" +
                        "Power: %s%n" +
                        "Displacement: %s%n" +
                        "Efficiency: %s%n" +
                        "Weight: %s%n" +
                        "Color: %s", this.carName, this.engineModel, this.power,
                this.displacement, this.efficiency, this.weight, this.color);
    }
}
