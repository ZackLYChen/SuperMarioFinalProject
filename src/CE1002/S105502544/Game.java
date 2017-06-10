package CE1002.S105502544;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.animation.Timeline;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Game extends Application {

	public static ArrayList<Block> platforms = new ArrayList<>();
	private HashMap<KeyCode, Boolean> keys = new HashMap<>();

	Image backgroundImg = new Image(getClass().getResourceAsStream("background.png"));
	public static final int BLOCK_SIZE = 45;
	public static final int Mushroom_SIZE = 45;
	public static final int MARIO_SIZE = 40;

	public static Pane appRoot = new Pane();
	public static Pane gameRoot = new Pane();
	public Character player;
	public Mushroom mushroom1;
	public Mushroom mushroom2;
	public Mushroom mushroom3;
	public Mushroom mushroom4;
	public Mushroom mushroom5;
	public Block block;
	public Block platformFloor;
	public Block brick;
	public Block bonus;
	public Block stone;
	public Block PipeTopBlock;
	public Block PipeBottomBlock;
	public Block InvisibleBlock;
	public Turtle turtle1;
	public Turtle turtle2;
	public Turtle turtle3;
	public Turtle turtle4;
	public Turtle turtle5;
	public Turtle turtle6;
	public Turtle turtle7;
	public boolean winornot = false;
	int levelNumber = 0;
	private int levelWidth;

	private void initContent() {
		ImageView backgroundIV = new ImageView(backgroundImg);
		backgroundIV.setFitHeight(14 * BLOCK_SIZE);// 14*45=630
		backgroundIV.setFitWidth(212 * BLOCK_SIZE);// 212*45=9540

		levelWidth = LevelData.levels[levelNumber][0].length() * BLOCK_SIZE;
		for (int i = 0; i < LevelData.levels[levelNumber].length; i++) {
			String line = LevelData.levels[levelNumber][i];
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
				case '0':
					break;
				case '1':
					platformFloor = new Block(Block.BlockType.PLATFORM, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '2':
					brick = new Block(Block.BlockType.BRICK, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '3':
					bonus = new Block(Block.BlockType.BONUS, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '4':
					stone = new Block(Block.BlockType.STONE, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '5':
					Block PipeTopBlock = new Block(Block.BlockType.PIPE_TOP, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '6':
					Block PipeBottomBlock = new Block(Block.BlockType.PIPE_BOTTOM, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '*':
					Block InvisibleBlock = new Block(Block.BlockType.INVISIBLE_BLOCK, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				}
			}

		}
		player = new Character();
		player.setTranslateX(0);
		player.setTranslateY(400);
		player.translateXProperty().addListener((obs, old, newValue) -> {
			int offset = newValue.intValue();
			if (offset > 640 && offset < levelWidth - 640) {
				gameRoot.setLayoutX(-(offset - 640));
				backgroundIV.setLayoutX(-(offset - 640));
			}
		});
		turtle1 = new Turtle();
		turtle1.setTranslateX(8500);
		turtle1.setTranslateY(530);

		turtle2 = new Turtle();
		turtle2.setTranslateX(7500);
		turtle2.setTranslateY(530);

		turtle3 = new Turtle();
		turtle3.setTranslateX(2278);
		turtle3.setTranslateY(530);

		turtle4 = new Turtle();
		turtle4.setTranslateX(500);
		turtle4.setTranslateY(530);

		turtle5 = new Turtle();
		turtle5.setTranslateX(1096);
		turtle5.setTranslateY(530);

		turtle6 = new Turtle();
		turtle6.setTranslateX(3000);
		turtle6.setTranslateY(530);

		turtle7 = new Turtle();
		turtle7.setTranslateX(5500);
		turtle7.setTranslateY(530);

		mushroom1 = new Mushroom();
		mushroom1.setTranslateX(800);
		mushroom1.setTranslateY(530);

		mushroom2 = new Mushroom();
		mushroom2.setTranslateX(1600);
		mushroom2.setTranslateY(530);

		mushroom3 = new Mushroom();
		mushroom3.setTranslateX(3000);
		mushroom3.setTranslateY(530);

		mushroom4 = new Mushroom();
		mushroom4.setTranslateX(5000);
		mushroom4.setTranslateY(530);

		mushroom5 = new Mushroom();
		mushroom5.setTranslateX(2000);
		mushroom5.setTranslateY(530);

		gameRoot.getChildren().add(player);
		gameRoot.getChildren().add(mushroom1);
		gameRoot.getChildren().add(mushroom2);
		gameRoot.getChildren().add(mushroom3);
		gameRoot.getChildren().add(mushroom4);
		gameRoot.getChildren().add(mushroom5);
		gameRoot.getChildren().add(turtle1);
		gameRoot.getChildren().add(turtle2);
		gameRoot.getChildren().add(turtle3);
		gameRoot.getChildren().add(turtle4);
		gameRoot.getChildren().add(turtle5);
		gameRoot.getChildren().add(turtle6);
		gameRoot.getChildren().add(turtle7);
		appRoot.getChildren().addAll(backgroundIV, gameRoot);

	}

	private void update() {
		if (player.getTranslateX() >= 8932) {
			winornot = true;
		}
		if (isPressed(KeyCode.UP) && player.getTranslateY() >= 5) {
			player.jumpPlayer();
		}
		if (isPressed(KeyCode.LEFT) && player.getTranslateX() >= 5) {
			player.setScaleX(-1);
			player.animation.play();
			player.moveX(-5);
		}
		if (isPressed(KeyCode.RIGHT) && player.getTranslateX() + 40 <= levelWidth - 5) {
			player.setScaleX(1);
			player.animation.play();
			player.moveX(5);
		}
		if (player.playerVelocity.getY() < 10) {
			player.playerVelocity = player.playerVelocity.add(0, 1);
		}
		player.moveY((int) player.playerVelocity.getY());

		if (mushroom1.playerVelocity.getY() < 10) {
			mushroom1.playerVelocity = player.playerVelocity.add(0, 1);
		}
		if (mushroom2.playerVelocity.getY() < 10) {
			mushroom2.playerVelocity = player.playerVelocity.add(0, 1);
		}
		if (mushroom3.playerVelocity.getY() < 10) {
			mushroom3.playerVelocity = player.playerVelocity.add(0, 1);
		}
		if (mushroom4.playerVelocity.getY() < 10) {
			mushroom4.playerVelocity = player.playerVelocity.add(0, 1);
		}
		if (mushroom5.playerVelocity.getY() < 10) {
			mushroom5.playerVelocity = player.playerVelocity.add(0, 1);
		}
		if (turtle1.playerVelocity.getY() < 10) {
			turtle1.playerVelocity = player.playerVelocity.add(0, 1);
		}
		if (turtle2.playerVelocity.getY() < 10) {
			turtle2.playerVelocity = player.playerVelocity.add(0, 1);
		}
		if (turtle3.playerVelocity.getY() < 10) {
			turtle3.playerVelocity = player.playerVelocity.add(0, 1);
		}
		if (turtle3.playerVelocity.getY() < 10) {
			turtle3.playerVelocity = player.playerVelocity.add(0, 1);
		}
		if (turtle4.playerVelocity.getY() < 10) {
			turtle4.playerVelocity = player.playerVelocity.add(0, 1);
		}
		if (turtle5.playerVelocity.getY() < 10) {
			turtle5.playerVelocity = player.playerVelocity.add(0, 1);
		}
		if (turtle6.playerVelocity.getY() < 10) {
			turtle6.playerVelocity = player.playerVelocity.add(0, 1);
		}
		if (turtle7.playerVelocity.getY() < 10) {
			turtle7.playerVelocity = player.playerVelocity.add(0, 1);
		}
		if ((mushroom1.die == false) && player.getBoundsInParent().intersects(mushroom1.getBoundsInParent())
				&& player.getTranslateY() + player.height >= mushroom1.getTranslateY() - 5
				&& mushroom1.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			mushroom1.animation2.play();
			player.canJump = true;
			player.jumpPlayer();
			mushroom1.die = true;
		} else if ((mushroom1.disapear == false) && mushroom1.die == true
				&& player.getBoundsInParent().intersects(mushroom1.getBoundsInParent())
				&& player.getTranslateY() + player.height >= mushroom1.getTranslateY() - 5
				&& mushroom1.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			mushroom1.disapear = true;
			gameRoot.getChildren().remove(mushroom1);
		} else if (((mushroom1.disapear == false) && player.getTranslateX() + player.width >= mushroom1.getTranslateX()
				&& player.getTranslateX() < mushroom1.getTranslateX()
				&& player.getTranslateY() + player.height / 2 == mushroom1.getTranslateY() + mushroom1.width / 2)
				|| ((mushroom1.die == false) && (player.getTranslateX() <= mushroom1.getTranslateX() + mushroom1.width
						&& player.getTranslateX() + player.width > mushroom1.getTranslateX() + mushroom1.width
						&& player.getTranslateY() + player.height / 2 == mushroom1.getTranslateY()
								+ mushroom1.width / 2))) {
			player.setTranslateX(0);
			player.setTranslateY(400);
			Game.gameRoot.setLayoutX(0);
		}
		if ((mushroom2.die == false) && player.getBoundsInParent().intersects(mushroom2.getBoundsInParent())
				&& player.getTranslateY() + player.height >= mushroom2.getTranslateY() - 5
				&& mushroom2.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			mushroom2.animation2.play();
			player.canJump = true;
			player.jumpPlayer();
			mushroom2.die = true;
		} else if ((mushroom2.disapear == false) && mushroom2.die == true
				&& player.getBoundsInParent().intersects(mushroom2.getBoundsInParent())
				&& player.getTranslateY() + player.height >= mushroom2.getTranslateY() - 5
				&& mushroom2.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			mushroom2.disapear = true;
			gameRoot.getChildren().remove(mushroom2);
		} else if (((mushroom2.disapear == false) && player.getTranslateX() + player.width >= mushroom2.getTranslateX()
				&& player.getTranslateX() < mushroom2.getTranslateX()
				&& player.getTranslateY() + player.height / 2 == mushroom2.getTranslateY() + mushroom2.width / 2)
				|| ((mushroom2.die == false) && (player.getTranslateX() <= mushroom2.getTranslateX() + mushroom2.width
						&& player.getTranslateX() + player.width > mushroom2.getTranslateX() + mushroom2.width
						&& player.getTranslateY() + player.height / 2 == mushroom2.getTranslateY()
								+ mushroom2.width / 2))) {
			player.setTranslateX(0);
			player.setTranslateY(400);
			Game.gameRoot.setLayoutX(0);
		}
		if ((mushroom3.die == false) && player.getBoundsInParent().intersects(mushroom3.getBoundsInParent())
				&& player.getTranslateY() + player.height >= mushroom3.getTranslateY() - 5
				&& mushroom3.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			mushroom3.animation2.play();
			player.canJump = true;
			player.jumpPlayer();
			mushroom3.die = true;
		} else if ((mushroom3.disapear == false) && mushroom3.die == true
				&& player.getBoundsInParent().intersects(mushroom3.getBoundsInParent())
				&& player.getTranslateY() + player.height >= mushroom3.getTranslateY() - 5
				&& mushroom3.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			mushroom3.disapear = true;
			gameRoot.getChildren().remove(mushroom3);
		} else if (((mushroom3.disapear == false) && player.getTranslateX() + player.width >= mushroom3.getTranslateX()
				&& player.getTranslateX() < mushroom3.getTranslateX()
				&& player.getTranslateY() + player.height / 2 == mushroom3.getTranslateY() + mushroom3.width / 2)
				|| ((mushroom3.die == false) && (player.getTranslateX() <= mushroom3.getTranslateX() + mushroom3.width
						&& player.getTranslateX() + player.width > mushroom3.getTranslateX() + mushroom3.width
						&& player.getTranslateY() + player.height / 2 == mushroom3.getTranslateY()
								+ mushroom3.width / 2))) {
			player.setTranslateX(0);
			player.setTranslateY(400);
			Game.gameRoot.setLayoutX(0);
		}
		if ((mushroom4.die == false) && player.getBoundsInParent().intersects(mushroom4.getBoundsInParent())
				&& player.getTranslateY() + player.height >= mushroom4.getTranslateY() - 5
				&& mushroom4.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			mushroom4.animation2.play();
			player.canJump = true;
			player.jumpPlayer();
			mushroom4.die = true;
		} else if ((mushroom4.disapear == false) && mushroom4.die == true
				&& player.getBoundsInParent().intersects(mushroom4.getBoundsInParent())
				&& player.getTranslateY() + player.height >= mushroom4.getTranslateY() - 5
				&& mushroom4.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			mushroom4.disapear = true;
			gameRoot.getChildren().remove(mushroom4);
		} else if (((mushroom4.disapear == false) && player.getTranslateX() + player.width >= mushroom4.getTranslateX()
				&& player.getTranslateX() < mushroom4.getTranslateX()
				&& player.getTranslateY() + player.height / 2 == mushroom4.getTranslateY() + mushroom4.width / 2)
				|| ((mushroom4.die == false) && (player.getTranslateX() <= mushroom4.getTranslateX() + mushroom4.width
						&& player.getTranslateX() + player.width > mushroom4.getTranslateX() + mushroom4.width
						&& player.getTranslateY() + player.height / 2 == mushroom4.getTranslateY()
								+ mushroom4.width / 2))) {
			player.setTranslateX(0);
			player.setTranslateY(400);
			Game.gameRoot.setLayoutX(0);
		}
		if ((mushroom5.die == false) && player.getBoundsInParent().intersects(mushroom5.getBoundsInParent())
				&& player.getTranslateY() + player.height >= mushroom5.getTranslateY() - 5
				&& mushroom5.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			mushroom5.animation2.play();
			player.canJump = true;
			player.jumpPlayer();
			mushroom5.die = true;
		} else if ((mushroom5.disapear == false) && mushroom5.die == true
				&& player.getBoundsInParent().intersects(mushroom5.getBoundsInParent())
				&& player.getTranslateY() + player.height >= mushroom5.getTranslateY() - 5
				&& mushroom5.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			mushroom5.disapear = true;
			gameRoot.getChildren().remove(mushroom5);
		} else if (((mushroom5.disapear == false) && player.getTranslateX() + player.width >= mushroom5.getTranslateX()
				&& player.getTranslateX() < mushroom5.getTranslateX()
				&& player.getTranslateY() + player.height / 2 == mushroom5.getTranslateY() + mushroom5.width / 2)
				|| ((mushroom5.die == false) && (player.getTranslateX() <= mushroom5.getTranslateX() + mushroom5.width
						&& player.getTranslateX() + player.width > mushroom5.getTranslateX() + mushroom5.width
						&& player.getTranslateY() + player.height / 2 == mushroom5.getTranslateY()
								+ mushroom5.width / 2))) {
			player.setTranslateX(0);
			player.setTranslateY(400);
			Game.gameRoot.setLayoutX(0);
		}

		if (turtle1.die == false && player.getBoundsInParent().intersects(turtle1.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle1.getTranslateY() - 5
				&& turtle1.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle1.die = true;
			player.canJump = true;
			player.jumpPlayer();
			turtle1.animation2.play();
		} else if ((turtle1.disapear == false) && turtle1.die == true
				&& player.getBoundsInParent().intersects(turtle1.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle1.getTranslateY() - 5
				&& turtle1.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle1.disapear = true;
			gameRoot.getChildren().remove(turtle1);
		} else if (((turtle1.disapear == false) && player.getTranslateX() + player.width >= turtle1.getTranslateX()
				&& player.getTranslateX() < turtle1.getTranslateX()
				&& player.getTranslateY() + player.height / 2 == turtle1.getTranslateY() + turtle1.width / 2)
				|| ((turtle1.die == false) && (player.getTranslateX() <= turtle1.getTranslateX() + turtle1.width
						&& player.getTranslateX() + player.width > turtle1.getTranslateX() + turtle1.width
						&& player.getTranslateY() + player.height / 2 == turtle1.getTranslateY()
								+ turtle1.width / 2))) {
			player.setTranslateX(0);
			player.setTranslateY(400);
			Game.gameRoot.setLayoutX(0);
		}

		if (turtle2.die == false && player.getBoundsInParent().intersects(turtle2.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle2.getTranslateY() - 5
				&& turtle2.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle2.die = true;
			player.canJump = true;
			player.jumpPlayer();
			turtle2.animation2.play();
		} else if ((turtle2.disapear == false) && turtle2.die == true
				&& player.getBoundsInParent().intersects(turtle2.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle2.getTranslateY() - 5
				&& turtle2.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle2.disapear = true;
			gameRoot.getChildren().remove(turtle2);
		} else if (((turtle2.disapear == false) && player.getTranslateX() + player.width >= turtle2.getTranslateX()
				&& player.getTranslateX() < turtle2.getTranslateX()
				&& player.getTranslateY() + player.height / 2 == turtle2.getTranslateY() + turtle2.width / 2)
				|| ((turtle2.die == false) && (player.getTranslateX() <= turtle2.getTranslateX() + turtle2.width
						&& player.getTranslateX() + player.width > turtle2.getTranslateX() + turtle2.width
						&& player.getTranslateY() + player.height / 2 == turtle2.getTranslateY()
								+ turtle2.width / 2))) {
			player.setTranslateX(0);
			player.setTranslateY(400);
			Game.gameRoot.setLayoutX(0);
		}
		if (turtle3.die == false && player.getBoundsInParent().intersects(turtle3.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle3.getTranslateY() - 5
				&& turtle3.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle3.die = true;
			player.canJump = true;
			player.jumpPlayer();
			turtle3.animation2.play();
		} else if ((turtle3.disapear == false) && turtle3.die == true
				&& player.getBoundsInParent().intersects(turtle3.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle3.getTranslateY() - 5
				&& turtle3.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle3.disapear = true;
			gameRoot.getChildren().remove(turtle3);
		} else if (((turtle3.disapear == false) && player.getTranslateX() + player.width >= turtle3.getTranslateX()
				&& player.getTranslateX() < turtle3.getTranslateX()
				&& player.getTranslateY() + player.height / 2 == turtle3.getTranslateY() + turtle3.width / 2)
				|| ((turtle3.die == false) && (player.getTranslateX() <= turtle3.getTranslateX() + turtle3.width
						&& player.getTranslateX() + player.width > turtle3.getTranslateX() + turtle3.width
						&& player.getTranslateY() + player.height / 2 == turtle3.getTranslateY()
								+ turtle3.width / 2))) {
			player.setTranslateX(0);
			player.setTranslateY(400);
			Game.gameRoot.setLayoutX(0);
		}
		if (turtle4.die == false && player.getBoundsInParent().intersects(turtle4.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle4.getTranslateY() - 5
				&& turtle4.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle4.die = true;
			player.canJump = true;
			player.jumpPlayer();
			turtle4.animation2.play();
		} else if ((turtle4.disapear == false) && turtle4.die == true
				&& player.getBoundsInParent().intersects(turtle4.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle4.getTranslateY() - 5
				&& turtle4.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle4.disapear = true;
			gameRoot.getChildren().remove(turtle4);
		} else if (((turtle4.disapear == false) && player.getTranslateX() + player.width >= turtle4.getTranslateX()
				&& player.getTranslateX() < turtle4.getTranslateX()
				&& player.getTranslateY() + player.height / 2 == turtle4.getTranslateY() + turtle4.width / 2)
				|| ((turtle4.die == false) && (player.getTranslateX() <= turtle4.getTranslateX() + turtle4.width
						&& player.getTranslateX() + player.width > turtle4.getTranslateX() + turtle4.width
						&& player.getTranslateY() + player.height / 2 == turtle4.getTranslateY()
								+ turtle4.width / 2))) {
			player.setTranslateX(0);
			player.setTranslateY(400);
			Game.gameRoot.setLayoutX(0);
		}
		if (turtle5.die == false && player.getBoundsInParent().intersects(turtle5.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle5.getTranslateY() - 5
				&& turtle5.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle5.die = true;
			player.canJump = true;
			player.jumpPlayer();
			turtle5.animation2.play();
		} else if ((turtle5.disapear == false) && turtle5.die == true
				&& player.getBoundsInParent().intersects(turtle5.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle5.getTranslateY() - 5
				&& turtle5.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle5.disapear = true;
			gameRoot.getChildren().remove(turtle5);
		} else if (((turtle5.disapear == false) && player.getTranslateX() + player.width >= turtle5.getTranslateX()
				&& player.getTranslateX() < turtle5.getTranslateX()
				&& player.getTranslateY() + player.height / 2 == turtle5.getTranslateY() + turtle5.width / 2)
				|| ((turtle5.die == false) && (player.getTranslateX() <= turtle5.getTranslateX() + turtle5.width
						&& player.getTranslateX() + player.width > turtle5.getTranslateX() + turtle5.width
						&& player.getTranslateY() + player.height / 2 == turtle5.getTranslateY()
								+ turtle5.width / 2))) {
			player.setTranslateX(0);
			player.setTranslateY(400);
			Game.gameRoot.setLayoutX(0);
		}
		if (turtle6.die == false && player.getBoundsInParent().intersects(turtle6.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle6.getTranslateY() - 5
				&& turtle6.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle6.die = true;
			player.canJump = true;
			player.jumpPlayer();
			turtle6.animation2.play();
		} else if ((turtle6.disapear == false) && turtle6.die == true
				&& player.getBoundsInParent().intersects(turtle6.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle6.getTranslateY() - 5
				&& turtle6.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle6.disapear = true;
			gameRoot.getChildren().remove(turtle6);
		} else if (((turtle6.disapear == false) && player.getTranslateX() + player.width >= turtle6.getTranslateX()
				&& player.getTranslateX() < turtle6.getTranslateX()
				&& player.getTranslateY() + player.height / 2 == turtle6.getTranslateY() + turtle6.width / 2)
				|| ((turtle6.die == false) && (player.getTranslateX() <= turtle6.getTranslateX() + turtle6.width
						&& player.getTranslateX() + player.width > turtle6.getTranslateX() + turtle6.width
						&& player.getTranslateY() + player.height / 2 == turtle6.getTranslateY()
								+ turtle6.width / 2))) {
			player.setTranslateX(0);
			player.setTranslateY(400);
			Game.gameRoot.setLayoutX(0);
		}
		if (turtle7.die == false && player.getBoundsInParent().intersects(turtle7.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle7.getTranslateY() - 5
				&& turtle7.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle7.die = true;
			player.canJump = true;
			player.jumpPlayer();
			turtle7.animation2.play();
		} else if ((turtle7.disapear == false) && turtle7.die == true
				&& player.getBoundsInParent().intersects(turtle7.getBoundsInParent())
				&& player.getTranslateY() + player.height >= turtle7.getTranslateY() - 5
				&& turtle7.getTranslateY() + 5 >= player.getTranslateY() + player.height) {
			turtle7.disapear = true;
			gameRoot.getChildren().remove(turtle7);
		} else if (((turtle7.disapear == false) && player.getTranslateX() + player.width >= turtle7.getTranslateX()
				&& player.getTranslateX() < turtle7.getTranslateX()
				&& player.getTranslateY() + player.height / 2 == turtle7.getTranslateY() + turtle7.width / 2)
				|| ((turtle7.die == false) && (player.getTranslateX() <= turtle7.getTranslateX() + turtle7.width
						&& player.getTranslateX() + player.width > turtle7.getTranslateX() + turtle7.width
						&& player.getTranslateY() + player.height / 2 == turtle7.getTranslateY()
								+ turtle7.width / 2))) {
			player.setTranslateX(0);
			player.setTranslateY(400);
			Game.gameRoot.setLayoutX(0);
		}
		turtle1.moveY((int) turtle1.playerVelocity.getY());
		turtle1.Swing();
		turtle2.moveY((int) turtle2.playerVelocity.getY());
		turtle2.Swing();
		turtle3.moveY((int) turtle3.playerVelocity.getY());
		turtle3.Swing();
		turtle4.moveY((int) turtle4.playerVelocity.getY());
		turtle4.Swing();
		turtle5.moveY((int) turtle5.playerVelocity.getY());
		turtle5.Swing();
		turtle6.moveY((int) turtle6.playerVelocity.getY());
		turtle6.Swing();
		turtle7.moveY((int) turtle7.playerVelocity.getY());
		turtle7.Swing();

		mushroom1.moveY((int) mushroom1.playerVelocity.getY());
		mushroom1.Swing();
		mushroom2.moveY((int) mushroom2.playerVelocity.getY());
		mushroom2.Swing();
		mushroom3.moveY((int) mushroom3.playerVelocity.getY());
		mushroom3.Swing();
		mushroom4.moveY((int) mushroom4.playerVelocity.getY());
		mushroom4.Swing();
		mushroom5.moveY((int) mushroom5.playerVelocity.getY());
		mushroom5.Swing();

	}

	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initContent();
		Scene scene = new Scene(appRoot, 1200, 620);
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> {
			keys.put(event.getCode(), false);
			player.animation.stop();
		});
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startmario01.fxml"));
		StartsceneController startscenecontroller = new StartsceneController();
		fxmlLoader.setController(startscenecontroller);
		Parent root = fxmlLoader.load();
		Scene gamescene = new Scene(root, 600, 400);
		startscenecontroller.setScene(scene, primaryStage);
		primaryStage.setTitle("Mini Mario");
		primaryStage.setScene(gamescene);
		primaryStage.show();

		FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("closemario.fxml"));
		StopsceneController stopscenecontroller = new StopsceneController();
		fxmlLoader2.setController(stopscenecontroller);
		Parent root2 = fxmlLoader2.load();
		Scene gamescene2 = new Scene(root2, 600, 400);
		

		MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("mariosound.mp3").toURI().toString()));
		mediaPlayer.setCycleCount(100);
		mediaPlayer.getCycleCount();
		mediaPlayer.play();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
				if (winornot) {
					stopscenecontroller.setScene(gamescene2, primaryStage);
				}
			}
		};
		timer.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
