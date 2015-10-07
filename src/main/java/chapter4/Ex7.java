package chapter4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Ex7 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = setUpLabel();
        TextArea text = setUpTextArea();

        label.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.DASHED, new CornerRadii(20), null, null)));

        label.textProperty().bind(text.textProperty());

        showIt(primaryStage, label, text);
    }

    private Label setUpLabel() {
        Label label = new Label("");
        label.setFont(new Font(100));
        return label;
    }

    private TextArea setUpTextArea() {
        TextArea textArea = new TextArea();
        textArea.setText("Hello");
        return textArea;
    }

    private void showIt(Stage primaryStage, Label label, TextArea text) {
        VBox box = new VBox();
        box.getChildren().addAll(text, label);

        primaryStage.setScene(new Scene(box));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
