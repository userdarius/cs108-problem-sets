package cs108;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public final class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Mandelbrot mandelbrot = new Mandelbrot();
        ImageView imageView = new ImageView();
        imageView.imageProperty().bind(mandelbrot.imageProperty());


        BorderPane mainPane = new BorderPane(imageView);
        mandelbrot.heightProperty().bind(mainPane.heightProperty());
        mandelbrot.widthProperty().bind(mainPane.widthProperty());

        imageView.setOnMouseClicked(event -> {
           if ((event.getClickCount() == 2)) {
                Rectangle frame = mandelbrot.getFrame();
                double top = frame.width() / mandelbrot.width();
                double x = event.getX() * top;
                double y = frame.height() - event.getY() * top;
                double scale = (event.isControlDown() ? 2 : 0.9);
                Rectangle frame2 = frame.translatedBy(x, y).scaledBy(scale).translatedBy(-x * scale, -y * scale);
                mandelbrot.setFrameCenter(frame2.center());
                mandelbrot.setFrameWidth(frame2.width());
            }
        });


        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Mandelbrot");
        primaryStage.show();
    }
}
