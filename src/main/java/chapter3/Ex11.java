package chapter3;

import chapter3.Ex5.ColorTransformer;
import general.ImageViewer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.function.UnaryOperator;

public class Ex11 {
    public static void main(String[] args) {
        Image image = new Image(Ex8.class.getResourceAsStream("/lenna.png"));

        ColorTransformer framer = Ex8.createFramer(image.getWidth(), image.getHeight(), 20, Color.GREY);

        ColorTransformer t = compose(asTransformer(Color::brighter), framer);

        Image transformed = Ex5.transform(image, t);
        ImageViewer.view(transformed);
    }

    public static ColorTransformer compose(ColorTransformer t1, ColorTransformer t2) {
        return (x, y, c) -> t2.apply(x, y, t1.apply(x, y, c));
    }

    public static ColorTransformer asTransformer(UnaryOperator<Color> op) {
        return (x, y, c) -> op.apply(c);
    }


}
