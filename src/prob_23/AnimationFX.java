package prob_23;

import javafx.animation.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;


public class AnimationFX {
    private static GridPane grid = new GridPane();
    private static double colAmt = 10;
    private static double rowAmt = 10;
    private static int current =1;
    private static int max =24;
    private static String prefix = "L";
    private static  MediaPlayer audioMP;

    private static TextField animSpeed = new TextField("200");
    private static TextField prefixTF = new TextField("L");
    private static TextField maxTF = new TextField("52");
    private static TextField audio = new TextField(new File("src/prob_23", "anthem2.mp3").toURI().toString());

    private static File file = new File("src/prob_23/image", prefix+current+".gif");
    private static ImageView image = new ImageView(file.toURI().toString());
    private static  RotateTransition test = new RotateTransition(Duration.millis(100), image);

    private static void setRow(){
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(1/rowAmt*100);
        for(int i=0; i<rowAmt; i++){
            grid.getRowConstraints().add(row);
        }
    }
    private static void setCol(){
        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(1/colAmt*100);
        for (int i=0; i<colAmt; i++){
            grid.getColumnConstraints().add(col);
        }
    }
    private static void setAnimation(){
        test.setOnFinished(event -> {
            if(current>=max){
                current=0;
            }
            current++;
            image.setImage(new Image(new File("src/prob_23/image", "L"+current+".gif").toURI().toString()));
            test.play();
        });

        grid.add(image, 0,0);

        GridPane.setRowSpan(image, 5);
        GridPane.setColumnSpan(image, 5);

        GridPane.setColumnSpan(image, 10);
        GridPane.setRowSpan(image, 5);
    }
    private static void setText(){
        Text enter = new Text("Enter information for animation");
        Text speed = new Text("Animation speed in milliseconds");
        Text prefixT = new Text("Image file prefix");
        Text images = new Text("Number of Images");
        Text audio = new Text("Audio file URL");

        grid.add(enter, 0, 4);
        grid.add(speed, 0, 5);
        grid.add(prefixT, 0, 6);
        grid.add(images, 0, 7);
        grid.add(audio, 0, 8);
    }
    private static void setTextField(){
        grid.add(animSpeed, 2, 5);
        grid.add(prefixTF, 2, 6);
        grid.add(maxTF, 2, 7);
        grid.add(audio, 2, 8);

        GridPane.setColumnSpan(animSpeed, 8);
        GridPane.setColumnSpan(prefixTF, 8);
        GridPane.setColumnSpan(maxTF, 8);
        GridPane.setColumnSpan(audio, 8);
    }
    private static void setButton() {
        Button start = new Button("Start Animation");
        Button stop = new Button("Stop Animation");

        grid.add(start, 9, 0);

        start.setOnAction(event -> {
            try {
                max = Integer.valueOf(maxTF.getText());
                prefix = prefixTF.getText();
                test.setDuration(Duration.millis(Double.valueOf(animSpeed.getText())));
                audioMP = new MediaPlayer(new Media(audio.getText()));
                grid.getChildren().remove(start);
                grid.add(stop, 9,0);
                audioMP.play();
                audioMP.setOnEndOfMedia(() -> {
                    audioMP.stop();
                    audioMP.play();
                });
                test.play();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Number of Images or Animation speed is not a valid number");
                alert.showAndWait();
            }
        });

        stop.setOnAction(event->{
            test.pause();
            audioMP.stop();
            grid.getChildren().remove(stop);
            grid.add(start, 9, 0);
        });
    }



    public static void setGrid(){
        image.setOpacity(.5);
        setCol();
        setRow();
        setAnimation();
        setText();
        setTextField();
        setButton();
    }
    public static GridPane getGrid(){return grid;}
}
