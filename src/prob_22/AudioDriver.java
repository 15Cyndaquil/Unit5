package prob_22;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Cyndaquil on 4/25/2017.
 */
public class AudioDriver extends Application{
    @Override
    public void start(Stage primaryStage){
        AudioFX.setGrid();

        Scene scene = new Scene(AudioFX.getGrid(), 200, 50);

        primaryStage.setTitle("Audio");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
