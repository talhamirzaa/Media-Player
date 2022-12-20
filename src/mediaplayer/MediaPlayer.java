package mediaplayer;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MediaPlayer extends Application {
    
    @Override
    public void start(Stage stage) {

        Parent root;
        try{
        root = FXMLLoader.load(getClass().getResource("MediaPlayer.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Media Player-DBIT");
        stage.setScene(scene);
        stage.show();
        }catch(IOException e){
            System.out.println("error");
            System.out.println(e);    
    }

}
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
