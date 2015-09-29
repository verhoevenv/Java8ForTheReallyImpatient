package chapter3;

import general.ImageViewer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.function.BiFunction;

public class Ex6 {

    public static void main(String[] args) {
        Image image = new Image(Ex6.class.getResourceAsStream("/lenna.png"));

        Image brightenedImage = transform(image,
                (c, factor) -> c.deriveColor(0, 1, factor, 1), // Brighten c by factor
                1.2); // Use a factor of 1.2

        ImageViewer.view(brightenedImage);
    }
    
    public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
        return Ex5.transform(in, (x, y, c) -> f.apply(c, arg));
    }
}
