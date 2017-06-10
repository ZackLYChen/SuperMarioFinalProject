package CE1002.S105502544;

import CE1002.S105502544.Block.BlockType;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Mushroom extends Pane{
	Image monsterImg = new Image(getClass().getResourceAsStream("super-mario-enemies-2x.png"));
    ImageView enemyimageView = new ImageView(monsterImg);
    int count = 2;
    int columns = 2;
    int offsetX = 0;
    int offsetY = 32;
    int width = 32;
    int height = 32;
    public SpriteAnimation animation1;
    public SpriteAnimation animation2;
    public Point2D playerVelocity = new Point2D(0,0);
    private boolean canJump = true;
    boolean dirRight;
    boolean die;
    boolean disapear;
    

    public Mushroom(){
    	enemyimageView.setFitHeight(40);
    	enemyimageView.setFitWidth(40);
    	enemyimageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation1 = new SpriteAnimation(this.enemyimageView,Duration.millis(200),count,columns,offsetX,offsetY,width,height);
        animation2 = new SpriteAnimation(this.enemyimageView,Duration.millis(200),1,columns,64,offsetY,width,height);
        getChildren().addAll(this.enemyimageView);
        dirRight = true;
        die = false;
        disapear = false;
    }
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
    public void moveY(int value){
        boolean movingDown = value >0;
        for(int i = 0; i < Math.abs(value); i++){
            for(Block platform :Game.platforms){
                if(getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(movingDown){
                        if(this.getTranslateY()+ Game.MARIO_SIZE == platform.getTranslateY()){
                            this.setTranslateY(this.getTranslateY()-1);
                            canJump = true;
                            return;
                        }
                    }
                    else{
                        if(this.getTranslateY() == platform.getTranslateY()+ Game.BLOCK_SIZE){
                            this.setTranslateY(this.getTranslateY()+1);
                            playerVelocity = new Point2D(0,10);
                            return;
                        }
                    }
                }
            }
            this.setTranslateY(this.getTranslateY() + (movingDown?1:-1));
        }
    }
    public void Swing(){
    	animation1.play();
    	if(dirRight&&!die){
    		if(this.moveX(6));
    		else{
    			dirRight = false;
    			this.moveX(-6);
    		}
    	}
    	else if (die){
    		this.moveX(0);
    	}
    	else{
    		if(this.moveX(-6));
    		else{
    			dirRight = true;
    			this.moveX(6);
    		}
    	}



    }
}
