package cs108;

import static java.lang.Math.floor;

public final class Chessboard implements Image<ColorRGB> {
    private final ColorRGB c1, c2;
    private final double w;

    public Chessboard(ColorRGB c1, ColorRGB c2, double w) {
        if (! (w > 0))
            throw new IllegalArgumentException("non-positive width: " + w);
        this.c1 = c1;
        this.c2 = c2;
        this.w = w;
    }

    @Override
    public ColorRGB apply(double x, double y) {
        int sqX = (int)floor(x / w), sqY = (int)floor(y / w);
        return (sqX + sqY) % 2 == 0 ? c1 : c2;
    }
}