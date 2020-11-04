public class Box {

    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setHeight(double height) {
        validateSideSize(height, "Height");
        this.height = height;
    }

    private void setWidth(double width) {
        validateSideSize(width, "Width");
        this.width = width;
    }

    private void setLength(double length) {
        validateSideSize(length, "Length");
        this.length = length;
    }

    private void validateSideSize(double length, String exceptionPrefix) {
        if (length <= 0) {
            throw new IllegalArgumentException(String.format("%s cannot be zero or negative.",
                    exceptionPrefix));
        }
    }

    public double calculateSurfaceArea() {
        return (2 * this.length * this.width) +
                (2 * this.length * this.height) +
                (2 * this.width * this.height);
    }

    public double calculateLateralSurfaceArea() {
        return 2 * (this.length * this.height +
                this.width * this.height);
    }

    public double calculateVolume() {
        return this.length * this.width * this.height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Surface Area - ")
                .append(String.format("%.2f", this.calculateSurfaceArea()))
                .append(System.lineSeparator());

        sb.append("Lateral Surface Area - ")
                .append(String.format("%.2f", this.calculateLateralSurfaceArea()))
                .append(System.lineSeparator());

        sb.append("Volume - ")
                .append(String.format("%.2f",this.calculateVolume()))
                .append(System.lineSeparator());

        return sb.toString().trim();
    }
}
