package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Image Viewer");
        primaryStage.getIcons().add(new Image("sample/images/icons8-full-image-96.png"));

        primaryStage.setScene(new Scene(root));
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent doubleClicked) {
                if (doubleClicked.getClickCount() == 2) {
                    primaryStage.setFullScreen(true);
                }
            }
        });
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
