package cs108;

public record Point(double x, double y) {
    public Point translatedBy(double dX, double dY) {
        return new Point(x + dX, y + dY);
    }
}
