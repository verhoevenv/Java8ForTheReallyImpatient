package general;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageViewer extends Application {

    //There must be a better way to pass an object to an application, but I'm not sure how
    private static Image img;

    public static void view(Image img) {
        ImageViewer.img = img;
        Application.launch(ImageViewer.class);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Title");
        Group root = new Group();
        Scene scene = new Scene(root, img.getWidth(), img.getHeight(), Color.WHITE);

        GridPane gridpane = new GridPane();

        final ImageView imv = new ImageView();
        imv.setImage(img);

        final HBox pictureRegion = new HBox();

        pictureRegion.getChildren().add(imv);
        gridpane.add(pictureRegion, 1, 1);


        root.getChildren().add(gridpane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
