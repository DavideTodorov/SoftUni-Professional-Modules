public class Tires {

    private double firstTirePressure;
    private int firstTireAge;

    private double secondTirePressure;
    private int secondTireAge;

    private double thirdTirePressure;
    private int thirdTireAge;

    private double fourthTirePressure;
    private int fourthTireAge;

    public Tires(double firstTirePressure, int firstTireAge,
                 double secondTirePressure, int secondTireAge,
                 double thirdTirePressure, int thirdTireAge,
                 double fourthTirePressure, int fourthTireAge) {

        this.firstTirePressure = firstTirePressure;
        this.firstTireAge = firstTireAge;
        this.secondTirePressure = secondTirePressure;
        this.secondTireAge = secondTireAge;
        this.thirdTirePressure = thirdTirePressure;
        this.thirdTireAge = thirdTireAge;
        this.fourthTirePressure = fourthTirePressure;
        this.fourthTireAge = fourthTireAge;
    }

    public double getFirstTirePressure() {
        return this.firstTirePressure;
    }

    public int getFirstTireAge() {
        return this.firstTireAge;
    }

    public double getSecondTirePressure() {
        return this.secondTirePressure;
    }

    public int getSecondTireAge() {
        return this.secondTireAge;
    }

    public double getThirdTirePressure() {
        return this.thirdTirePressure;
    }

    public int getThirdTireAge() {
        return this.thirdTireAge;
    }

    public double getFourthTirePressure() {
        return this.fourthTirePressure;
    }

    public int getFourthTireAge() {
        return this.fourthTireAge;
    }
}
