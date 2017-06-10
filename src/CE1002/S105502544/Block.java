package CE1002.S105502544;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class Block extends Pane {
    Image blocksImg = new Image(getClass().getResourceAsStream("1.png"));
    ImageView block;
    public Boolean touched = false;
    public SpriteAnimation animation;
    public static enum BlockType {
        PLATFORM, BRICK, BONUS, PIPE_TOP, PIPE_BOTTOM, INVISIBLE_BLOCK, STONE
    }
    public BlockType type;
    public Block(BlockType blockType, int x, int y) {
        block = new ImageView(blocksImg);
        block.setFitWidth(Game.BLOCK_SIZE);
        block.setFitHeight(Game.BLOCK_SIZE);
        setTranslateX(x);
        setTranslateY(y);
        type = blockType;
        switch (blockType) {
            case PLATFORM:
                block.setViewport(new Rectangle2D(0, 0, 16, 16));
                break;
            case BRICK:
                block.setViewport(new Rectangle2D(16, 0, 16, 16));
                break;
            case BONUS:
                block.setViewport(new Rectangle2D(384, 0, 16, 16));
                animation = new SpriteAnimation(block,Duration.millis(200),4,4,384,0,16,16);
                animation.setCycleCount(1);
                break;
            case PIPE_TOP:
                block.setViewport(new Rectangle2D(0, 128, 32, 16));
                block.setFitWidth(Game.BLOCK_SIZE * 2);
                break;
            case PIPE_BOTTOM:
                block.setViewport(new Rectangle2D(0, 145, 32, 14));
                block.setFitWidth(Game.BLOCK_SIZE * 2);
                break;
            case INVISIBLE_BLOCK:
                block.setViewport(new Rectangle2D(0, 0, 16, 16));
                block.setOpacity(0);
                break;
            case STONE:
                block.setViewport(new Rectangle2D(0, 16, 16, 16));
                break;
        }
        getChildren().add(block);
        Game.platforms.add(this);
        Game.gameRoot.getChildren().add(this);
    }
}



