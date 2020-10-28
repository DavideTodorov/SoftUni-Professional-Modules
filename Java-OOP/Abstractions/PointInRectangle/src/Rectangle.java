import java.awt.*;

public class Rectangle {

    private Point bottomLeft;
    private Point topRight;

    public Rectangle(int bottomLeftX, int bottomLeftY, int topRightX, int topRightY) {
        this.bottomLeft = new Point(bottomLeftX, bottomLeftY);
        this.topRight = new Point(topRightX, topRightY);
    }

    public boolean contains(Point point) {
        return point.x >= this.bottomLeft.x && point.x <= this.topRight.x &&
                point.y >= this.bottomLeft.y && point.y <= this.topRight.y;
    }
}