public enum DiscountType {

    VIP(20),
    SECONDVISIT(10),
    NONE(0);

    private final int percents;

    DiscountType(int percents) {
        this.percents = percents;
    }

    public int getPercents() {
        return percents;
    }
}
