package mediaplayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class MediaPlayerController implements Initializable {
    
    private String path;
    private MediaPlayer mediaPlayer;
    
    @FXML
    private MediaView mediaView;
    
    @FXML
    private Button openFile;
    
    @FXML
    private Slider volumeSlider;
    
    @FXML
    private Slider progressBar;
    
    @FXML
    private Label label;
    
    @FXML
    private StackPane pane;
    
    @FXML
    private void OpenFileMethod(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("MP3 and MP4 files","*.mp3", "*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        path = file.toURI().toString();

        if(path != null){
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            
            //creating bindings
            DoubleProperty widthProp = mediaView.fitWidthProperty();
            DoubleProperty heightProp = mediaView.fitHeightProperty();
            
            widthProp.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            heightProp.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            
            //volume slider
            volumeSlider.setValue(mediaPlayer.getVolume()*100);
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volumeSlider.getValue()/100);
                }
            });
           
            //video slider
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<javafx.util.Duration>() {
                @Override
                public void changed(ObservableValue<? extends javafx.util.Duration> observable, javafx.util.Duration oldValue, javafx.util.Duration newValue) {
                    progressBar.setValue(newValue.toSeconds());
                }
            }
            );
            
            //mouse press
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
                }
            });
            
            //mouse drag
            progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
                }
            });

            //to run media player
            mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    javafx.util.Duration total = media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
            });
            
            mediaPlayer.play();
        }
    }
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    //pause
    public void pauseVideo(ActionEvent event){
        mediaPlayer.pause();
    }
    
    //stop
    public void stopVideo(ActionEvent event){
        mediaPlayer.stop();
    }
    
    //play
    public void playVideo(ActionEvent event){
        mediaPlayer.play();
        mediaPlayer.setRate(1);// 1x
    }
    
    //jump 5s
    public void skip5(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(5)));
    }
    
    //2x
    public void furtherSpeedUpVideo(ActionEvent event){
        mediaPlayer.setRate(2);
    }
    
    //back 5s
    public void back5(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(-5)));
    }
    
    //0.5s
    public void furtherSlowDownVideo(ActionEvent event){
        mediaPlayer.setRate(0.5);
    }

}