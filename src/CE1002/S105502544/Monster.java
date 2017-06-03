package CE1002.S105502544;

import CE1002.S105502544.Block.BlockType;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Monster extends Character{
    Image monsterImg = new Image(getClass().getResourceAsStream("super-mario-enemies-2x.png"));
    ImageView imageView = new ImageView(monsterImg);
    boolean dirRight;
    

    public Monster(){
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(this.imageView,Duration.millis(200),count,columns,offsetX,offsetY,width,height);
        getChildren().addAll(this.imageView);
        dirRight = true;
    }
    @Override
    public boolean moveX(int value){
        boolean movingRight = value > 0;
        for(int i = 0; i<Math.abs(value); i++) {
            for (Node platform : Game.platforms) {
                if(this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (this.getTranslateX() + Game.MARIO_SIZE == platform.getTranslateX()){
                            this.setTranslateX(this.getTranslateX() - 1);
                            return false;
                        }
                    } else {
                        if (this.getTranslateX() == platform.getTranslateX() + Game.BLOCK_SIZE) {
                            this.setTranslateX(this.getTranslateX() + 1);
                            return false;
                        }
                    }
                }

            }
            this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
        	return true;

        }
		return true;
    }
    public void Swing(){
    	if(dirRight){
    		if(this.moveX(5));
    		else{
    			dirRight = false;
    			this.moveX(-5);
    		}
    	}
    	else{
    		if(this.moveX(-5));
    		else{
    			dirRight = true;
    			this.moveX(5);
    		}
    	}



    }
}
