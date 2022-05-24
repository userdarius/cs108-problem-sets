package cs108;

public record Rectangle(Point bottomLeft, double width, double height) {
    public static Rectangle ofCenterAndSize(Point center, double width, double height) {
        return new Rectangle(center.translatedBy(-width / 2d, -height / 2d), width, height);
    }

    public static Rectangle ofBottomLeftAndSize(Point bottomLeft, double width, double height) {
        return new Rectangle(bottomLeft, width, height);
    }

    public Point center() {
        return bottomLeft.translatedBy(width / 2d, height / 2d);
    }

    public double minX() {
        return bottomLeft.x();
    }

    public double maxX() {
        return bottomLeft.x() + width;
    }

    public double minY() {
        return bottomLeft.y();
    }

    public double maxY() {
        return bottomLeft.y() + height;
    }

    public Rectangle translatedBy(double dX, double dY) {
        return new Rectangle(bottomLeft.translatedBy(dX, dY), width, height);
    }

    public Rectangle translatedBy(double d) {
        return translatedBy(d, d);
    }

    public Rectangle scaledBy(double sX, double sY) {
        return new Rectangle(bottomLeft, width * sX, height * sY);
    }

    public Rectangle scaledBy(double s) {
        return scaledBy(s, s);
    }
}
