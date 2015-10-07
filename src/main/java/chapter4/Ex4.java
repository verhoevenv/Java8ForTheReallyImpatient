package chapter4;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Ex4 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(100, 100, 100);
        circle.setFill(Color.RED);

        Pane pane = new Pane();
        pane.getChildren().add(circle);

        Scene scene = new Scene(pane);
        NumberBinding halfWidth = Bindings.divide(scene.widthProperty(), 2);
        NumberBinding halfHeight = Bindings.divide(scene.heightProperty(), 2);
        circle.centerXProperty().bind(halfWidth);
        circle.centerYProperty().bind(halfHeight);
        circle.radiusProperty().bind(Bindings.min(halfWidth, halfHeight));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
