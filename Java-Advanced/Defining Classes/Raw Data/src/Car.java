public class Car {

    private String model;
    private Engine engine;
    private Cargo cargo;
    private Tires tires;

    public Car(String model, String[] engine, String[] cargo, String[] tires) {
        this.model = model;

        this.engine = new Engine(Integer.parseInt(engine[0]), Integer.parseInt(engine[1]));

        this.cargo = new Cargo(Integer.parseInt(cargo[0]), cargo[1]);

        this.tires = new Tires(Double.parseDouble(tires[0]), Integer.parseInt(tires[1]),
                Double.parseDouble(tires[2]), Integer.parseInt(tires[3]),
                Double.parseDouble(tires[4]), Integer.parseInt(tires[5]),
                Double.parseDouble(tires[6]), Integer.parseInt(tires[7]));
    }

    public String getModel() {
        return this.model;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public Cargo getCargo() {
        return this.cargo;
    }

    public Tires getTires() {
        return this.tires;
    }

    @Override
    public String toString() {
        return this.model;
    }
}
