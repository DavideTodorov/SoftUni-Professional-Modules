public class Rectangle extends Shape {

    private final Double height;
    private final Double width;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
    }

    public final Double getHeight() {
        return height;
    }

    public final Double getWidth() {
        return width;
    }

    @Override
    public Double calculatePerimeter() {
        if (super.getPerimeter() != null) {
            return super.getPerimeter();
        }

        super.setPerimeter(2 * height + 2 * width);
        return super.getPerimeter();
    }

    @Override
    public Double calculateArea() {
        if (super.getArea() != null) {
            return super.getArea();
        }
        super.setArea( width * height);
        return super.getArea();
    }
}
