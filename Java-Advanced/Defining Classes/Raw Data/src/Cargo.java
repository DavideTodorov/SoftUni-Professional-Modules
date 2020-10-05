public class Cargo {

    private int weight;
    private String cargoType;

    public Cargo(int weight, String cargoType) {
        this.weight = weight;
        this.cargoType = cargoType;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getCargoType() {
        return this.cargoType;
    }
}
