package cs108;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public final class Rotated<T> implements Image<T> {
    private final Image<T> image;
    private final double cosA, sinA;

    public Rotated(Image<T> image, double angle) {
        this.image = image;
        this.cosA = cos(-angle);
        this.sinA = sin(-angle);
    }

    @Override
    public T apply(double x, double y) {
        double x1 = x * cosA - y * sinA;
        double y1 = x * sinA + y * cosA;
        return image.apply(x1, y1);
    }
}