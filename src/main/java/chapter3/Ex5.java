package chapter3;

import general.ImageViewer;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Ex5 {

    public static void main(String[] args) {
        Image image = new Image(Ex5.class.getResourceAsStream("/lenna.png"));

        Image transformed = transform(image, (x, y, c) -> {
            if(x < 10 || y < 10 || x > image.getWidth() - 10 || y > image.getHeight() - 10) {
                return Color.GREY;
            }
            return c;
        });

        ImageViewer.view(transformed);
    }

    public static Image transform(Image in, ColorTransformer f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                out.getPixelWriter().setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));
        return out;
    }

    @FunctionalInterface
    public interface ColorTransformer {
        Color apply(int x, int y, Color colorAtXY);
    }
}
