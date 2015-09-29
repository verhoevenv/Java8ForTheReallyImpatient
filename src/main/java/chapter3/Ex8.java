package chapter3;

import chapter3.Ex5.ColorTransformer;
import general.ImageViewer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Ex8 {
    public static void main(String[] args) {
        Image image = new Image(Ex5.class.getResourceAsStream("/lenna.png"));

        ColorTransformer framer = createFramer(image.getWidth(), image.getHeight(), 20, Color.FUCHSIA);
        Image transformed = Ex5.transform(image, framer);

        ImageViewer.view(transformed);
    }

    private static ColorTransformer createFramer(double imageWidth, double imageHeight, int borderWidth, Color color) {
        return (x, y, c) -> {
            if(x < borderWidth || y < borderWidth || imageWidth - x < borderWidth || imageHeight - y < borderWidth) {
                return color;
            }
            return c;
        };
    }
}
