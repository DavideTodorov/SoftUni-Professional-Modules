public enum ToppingsModifier {

    Meat(1.2),
    Veggies(0.8),
    Cheese(1.1),
    Sauce(0.9);

    private double modifierCalories;

    ToppingsModifier(double modifierCalories) {
        this.modifierCalories = modifierCalories;
    }

    public double getModifierCalories() {
        return this.modifierCalories;
    }


}
