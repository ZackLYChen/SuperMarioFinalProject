package CE1002.S105502544;

import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

class Anima extends Pane{
    Image blocksImg = new Image(getClass().getResourceAsStream("1.png"));
    ImageView block;
    public SpriteAnimation animation;
    public Anima(int x, int y) {
        block = new ImageView(blocksImg);
        block.setFitWidth(Game.BLOCK_SIZE);
        block.setFitHeight(Game.BLOCK_SIZE);
        setTranslateX(x);
        setTranslateY(y);
        block.setViewport(new Rectangle2D(385,16,16,16));
    	animation = new SpriteAnimation(block,Duration.millis(200),3,3,385,16,16,16);
        animation.setCycleCount(1); 
        getChildren().add(block);
        Game.gameRoot.getChildren().add(this);
    }
    public void play(){
      	animation.play();
      	animation.setOnFinished(new EventHandler<ActionEvent>(){
    		public void handle(ActionEvent event) {
          		getChildren().remove(block);
          		Game.gameRoot.getChildren().remove(this);
          	}
      	});

    }

}