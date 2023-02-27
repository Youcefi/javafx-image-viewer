package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML private ImageView imageView;
    @FXML private  Button zoom;
    @FXML private  Button dezoomer;
    @FXML private Label nom; //nom de file
    @FXML private Button rotateRight;
    @FXML private Button rotateLeft;
    @FXML private Button remove;
    private String imageFile;
    private File file;

    @FXML private Button screenButton ;
    @FXML private Button filter1;
    @FXML private Button filter2;
    @FXML private Button save;


    @FXML
    public void pickImage (ActionEvent actionEvent) {
        // select a file from the dialog box
        FileChooser fileChooser = new FileChooser();
        // image file extensions
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                         "*.png", "*.jpg", "*.gif"));
        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            imageFile = file.toURI().toString();
            Image image = new Image(imageFile);
            imageView.setImage(image);
            imageView.setFitWidth(image.getWidth());
            imageView.setFitWidth(image.getHeight());
            nom.setText(file.getName());
            zoom.setDisable(false);
            dezoomer.setDisable(false);
            rotateRight.setDisable(false);
            rotateLeft.setDisable(false);
            remove.setDisable(false);
            screenButton.setDisable(false);
            filter1.setDisable(false);
            filter2.setDisable(false);
            save.setDisable(false);

        }
    }


    @FXML
    private void dezoomer(ActionEvent actionEvent) {
        // zoom out the image when it exceeds 250
        if (imageView.getFitWidth() > 250) {
            imageView.setFitWidth(imageView.getFitWidth() /2);
        }
        if (imageView.getFitHeight() >250) {
            imageView.setFitHeight(imageView.getFitHeight() / 2);
        }
        if (imageView.getFitHeight() == 250){
            dezoomer.setDisable(true);
        }
    }

    @FXML
    private void zoom(ActionEvent actionEvent) {
        if (imageView.getFitWidth() <4000) {
            imageView.setFitWidth(imageView.getFitWidth() * 2);
        }
        if (imageView.getFitHeight() <4000) {
            imageView.setFitHeight(imageView.getFitHeight() * 2);
        }

        if (imageView.getFitHeight() == 4000){
            zoom.setDisable(true);
        }
    }


    @FXML
    private void rotationR(ActionEvent e){
            imageView.setRotate(imageView.getRotate() + 60);
    }

    @FXML
    private void rotationL(ActionEvent e){
        imageView.setRotate(imageView.getRotate() - 60);
    }


    @FXML
    private void delete(Event event){
            file.delete();
            imageView.setImage(null);
            nom.setText(null);

    }

    @FXML
    private void Screen(ActionEvent event) {
      Stage stage = (Stage) screenButton.getScene().getWindow();
           stage.setFullScreen(true);
    }

     @FXML
    private void  filter1(){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1);
        imageView.setEffect(colorAdjust);
    }
    @FXML
    private void  filter2(){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(0);
        imageView.setEffect(colorAdjust);
    }



    @FXML public void save(Event event) throws IOException{

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Save image", ".png"));
        WritableImage imageCapture= imageView.snapshot(new SnapshotParameters(), null);
        BufferedImage   imageCaptureSave = SwingFXUtils.fromFXImage( imageCapture, null);
        File file = fileChooser.showSaveDialog(null);
        ImageIO.write( imageCaptureSave, "png", file);
    }

}


