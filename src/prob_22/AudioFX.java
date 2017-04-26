package prob_22;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.io.File;


public class AudioFX {
    private static GridPane grid = new GridPane();
    private static double rowAmt = 1;
    private static double colAmt = 3;
    private static File file = new File("src/prob_22", "anthem2.mp3");
    private static MediaPlayer mediaPlayer = new MediaPlayer(new Media(file.toURI().toString()));

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
    private static void setButtons(){
        Button play = new Button("Play");
        Button loop = new Button("Loop");
        Button stop = new Button("Stop");

        play.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        loop.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        stop.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        play.setOnAction(e->{playBT();});
        loop.setOnAction(e->{loopBT();});
        stop.setOnAction(e->{stopBT();});

        grid.add(play, 0, 0);
        grid.add(loop, 1, 0);
        grid.add(stop, 2, 0);

        GridPane.setHalignment(play, HPos.CENTER);
        GridPane.setHalignment(loop, HPos.CENTER);
        GridPane.setHalignment(stop, HPos.CENTER);

        GridPane.setFillHeight(play, true);
        GridPane.setFillHeight(loop, true);
        GridPane.setFillHeight(stop, true);

        GridPane.setFillWidth(play, true);
        GridPane.setFillWidth(loop, true);
        GridPane.setFillWidth(stop, true);
    }

    private static void playBT() {
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.stop();
            }
        });
    }
    private static void loopBT(){
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.stop();
                mediaPlayer.play();
            }
        });
    }
    private static void stopBT(){
        mediaPlayer.stop();
    }


    public static void setGrid(){


        grid.setHgap(5);
        grid.setVgap(5);
        grid.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5,5,5,5))));
        grid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        setCol();
        setRow();
        setButtons();
    }
    public static GridPane getGrid(){return grid;}
}
