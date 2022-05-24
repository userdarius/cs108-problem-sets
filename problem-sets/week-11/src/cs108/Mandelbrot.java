package cs108;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public final class Mandelbrot {
    private static final Parameters INITIAL_PARAMETERS =
            new Parameters(500, new Point(-0.5, 0), 3.4, 300, 250);

    private final IntegerProperty maxIterationsProperty;

    private final ObjectProperty<Point> frameCenterProperty;
    private final DoubleProperty frameWidthProperty;

    private final IntegerProperty widthProperty;
    private final IntegerProperty heightProperty;

    private final ObjectProperty<Parameters> parametersProperty;
    private final ObjectProperty<Image> imageProperty;

    public Mandelbrot() {
        this.maxIterationsProperty = new SimpleIntegerProperty(INITIAL_PARAMETERS.maxIterations);
        this.frameCenterProperty = new SimpleObjectProperty<>(INITIAL_PARAMETERS.frameCenter);
        this.frameWidthProperty = new SimpleDoubleProperty(INITIAL_PARAMETERS.frameWidth);
        this.widthProperty = new SimpleIntegerProperty(INITIAL_PARAMETERS.width);
        this.heightProperty = new SimpleIntegerProperty(INITIAL_PARAMETERS.height);
        this.parametersProperty = new SimpleObjectProperty<>(INITIAL_PARAMETERS);
        this.imageProperty = new SimpleObjectProperty<>(computeImage(INITIAL_PARAMETERS));


        ChangeListener<Object> parameterChangeListener = (p, o, n) ->
                Platform.runLater(() -> parametersProperty.set(parameters()));
        maxIterationsProperty.addListener(parameterChangeListener);
        frameCenterProperty.addListener(parameterChangeListener);
        frameWidthProperty.addListener(parameterChangeListener);
        widthProperty.addListener(parameterChangeListener);
        heightProperty.addListener(parameterChangeListener);

        parametersProperty.addListener((p, o, n) ->
                imageProperty.set(computeImage(parameters())));
    }

    public IntegerProperty maxIterationsProperty() {
        return maxIterationsProperty;
    }

    public int maxIterations() {
        return maxIterationsProperty.get();
    }

    public void setMaxIterations(int newMaxIterations) {
        maxIterationsProperty.set(newMaxIterations);
    }

    public ObjectProperty<Point> frameCenterProperty() {
        return frameCenterProperty;
    }

    public Point frameCenter() {
        return frameCenterProperty.get();
    }

    public void setFrameCenter(Point newFrameCenter) {
        frameCenterProperty.set(newFrameCenter);
    }

    public DoubleProperty frameWidthProperty() {
        return frameWidthProperty;
    }

    public double frameWidth() {
        return frameWidthProperty.get();
    }

    public void setFrameWidth(double newFrameWidth) {
        frameWidthProperty.set(newFrameWidth);
    }

    public Rectangle getFrame() {
        return Parameters.frameFor(width(), height(), frameCenter(), frameWidth());
    }

    public IntegerProperty widthProperty() {
        return widthProperty;
    }

    public int width() {
        return widthProperty.get();
    }

    public void setWidth(int newWidth) {
        widthProperty.set(newWidth);
    }

    public IntegerProperty heightProperty() {
        return heightProperty;
    }

    public int height() {
        return heightProperty.get();
    }

    public void setHeight(int newHeight) {
        heightProperty.set(newHeight);
    }

    public ReadOnlyObjectProperty<Image> imageProperty() {
        return imageProperty;
    }

    public Image image() {
        return imageProperty.get();
    }

    private Parameters parameters() {
        return new Parameters(maxIterations(), frameCenter(), frameWidth(), width(), height());
    }

    private record Parameters(int maxIterations,
                              Point frameCenter,
                              double frameWidth,
                              int width,
                              int height) {
        public static Rectangle frameFor(int width, int height, Point frameCenter, double frameWidth) {
            double frameHeight = frameWidth * ((double) (height - 1) / (double) (width - 1));
            return Rectangle.ofCenterAndSize(frameCenter, frameWidth, frameHeight);
        }

        public Rectangle frame() {
            return frameFor(width, height, frameCenter, frameWidth);
        }
    }

    private static Image computeImage(Parameters p) {
        WritableImage image = new WritableImage(p.width, p.height);
        PixelWriter pixWriter = image.getPixelWriter();

        Rectangle frame = p.frame();
        double delta = frame.width() / (p.width - 1);
        for (int x = 0; x < p.width; ++x) {
            for (int y = 0; y < p.height; ++y) {
                double cR = frame.minX() + delta * x, cI = frame.maxY() - delta * y;
                double zr = cR, zi = cI;
                int i = 1;
                while (zr * zr + zi * zi < 4d && i < p.maxIterations) {
                    double zr1 = zr * zr - zi * zi + cR;
                    double zi1 = 2d * zr * zi + cI;
                    zr = zr1;
                    zi = zi1;
                    i += 1;
                }

                double q = 1d - Math.pow((double) i / p.maxIterations, 0.25);
                int pI = (int) (q * 255.9999);
                int g = 0xFF000000 | (pI << 16) | (pI << 8) | pI;

                pixWriter.setArgb(x, y, g);
            }
        }
        return image;
    }
}
