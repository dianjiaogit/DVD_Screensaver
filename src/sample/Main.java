package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("DVD Screensaver");
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 600, Color.BLACK);
        File p = new File("res/Purple.png");
        final Image purple = new Image(p.toURI().toString());
        File r = new File("res/Red.png");
        final Image red = new Image(r.toURI().toString());
        File y = new File("res/Yellow.png");
        final Image yellow = new Image(y.toURI().toString());
        ImageView currentLogo = new ImageView(purple);
        currentLogo.setX(100);
        currentLogo.setY(200);
        currentLogo.setFitWidth(137);
        currentLogo.setFitHeight(66);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {

            int count = 0;
            double dx = 2;
            double dy = 3;

            @Override
            public void handle(ActionEvent event) {
                double x = currentLogo.getX() + dx;
                double y = currentLogo.getY() + dy;

                if (x + 137 > 1000 || x < 0) {
                    dx = -dx;
                    count = (count + 1) % 3;
                }

                if (y + 66 > 600 || y < 0) {
                    dy = -dy;
                    count = (count + 1) % 3;
                }

                if (count == 0) {
                    currentLogo.setImage(purple);
                } else if (count == 1) {
                    currentLogo.setImage(yellow);
                } else if (count == 2) {
                    currentLogo.setImage(red);
                }

                currentLogo.setX(x);
                currentLogo.setY(y);
            }
        }));
        root.getChildren().add(currentLogo);
        primaryStage.setScene(scene);
        primaryStage.show();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
