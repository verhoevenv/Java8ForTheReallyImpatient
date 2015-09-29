package chapter3;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Ex10 {
    public static void main(String[] args) {
        Image image = new Image(Ex10.class.getResourceAsStream("/lenna.png"));

        UnaryOperator<Color> op = Color::brighter;

        //  uncomment to find a compiler error
        //Image finalImage = transform(image, op.compose(Color::grayscale));

        //  There are two things going on here...
        //  compose() is a default method of Function and takes a Function instead of a UnaryOperator
        //    this makes it hard to bind the generic parameters of the lambda needed to call compose, I guess?
        //  and it will return a Function, not an UnaryOperator
        //    this makes it impossible to use in a method that expects a UnaryOperator
    }


    public static Image transform(Image in, UnaryOperator<Color> f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y)));
        return out;
    }
}
