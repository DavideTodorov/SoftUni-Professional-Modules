public abstract class Shape {
    private Double perimeter;
    private Double area;

    public abstract Double calculatePerimeter();

    public abstract Double calculateArea();

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    protected final Double getPerimeter() {
        return perimeter;
    }

    protected final Double getArea() {
        return area;
    }
}
