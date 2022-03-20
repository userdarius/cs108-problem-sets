package cs108;

import static java.lang.Math.max;
import static java.lang.Math.min;

public final class HorizontalGradientMask implements Image<Double> {
    public static final Image<Double> IMAGE = new HorizontalGradientMask();
    
    @Override
    public Double apply(double x, double y) {
        return max(0, min((x + 1d) / 2d, 1d));
    }
}