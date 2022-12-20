# Media-Player in Java
To compile the Media Player java Program in terminal:-

Step 1: Move to Project's directory.\n
cd <<your Project's folder path>>\src\mediaplayer

Step 2: Compile with following command.
javac --module-path <your javafx lib folder path> --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.graphics MediaPlayer.java  

Now, 
To Run the Media Player java Program in terminal:-
  
Step 3: move back one directory.
cd <your folder path>\src
  
Step 4: Run with following command.
java --module-path <your javafx lib folder path> --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.graphics mediaplayer.MediaPlayer
