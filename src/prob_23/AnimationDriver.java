package prob_23;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Cyndaquil on 4/26/2017.
 */
public class AnimationDriver extends Application {
    @Override
    public void start(Stage primaryStage) {
        AnimationFX.setGrid();
        Scene scene = new Scene(AnimationFX.getGrid(), 1000, 500);

        primaryStage.setTitle("Audio");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
