package CE1002.S105502544;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class StartsceneController {
	@FXML
    private Pane pane;
	@FXML
	private Button startbutton;
	@FXML
	private Button stopbutton;
	
    public void setScene(Scene scene , Stage stage){
    	
    	startbutton.setOnAction(new EventHandler<ActionEvent> () {
            @Override
            public void handle ( ActionEvent event ) {
            	stage.setScene(scene);
            }
        } );
    	stopbutton.setOnAction(new EventHandler<ActionEvent> () {
            @Override
            public void handle ( ActionEvent event ) {
            	stage.close();
            }
        } );
    }

}
