public class Circle extends Shape {

    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return radius;
    }

    @Override
    public Double calculatePerimeter() {
        if (super.getPerimeter() != null) {
            return super.getPerimeter();
        }

        super.setPerimeter(2 * Math.PI * this.radius);
        return super.getPerimeter();
    }

    @Override
    public Double calculateArea() {
        if (super.getArea() != null) {
            return super.getArea();
        }
        super.setArea(Math.PI * (this.radius * this.radius));
        return super.getArea();
    }
}
