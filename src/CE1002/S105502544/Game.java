package CE1002.S105502544;

import javafx.animation.AnimationTimer;
import  javafx.application.Application;
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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Game extends Application {
	
    public static ArrayList<Block> platforms = new ArrayList<>();
    private HashMap<KeyCode,Boolean> keys = new HashMap<>();

    Image backgroundImg = new Image(getClass().getResourceAsStream("background.png"));
    public static final int BLOCK_SIZE = 45;
    public static final int MONSTER_SIZE = 45;
    public static final int MARIO_SIZE = 40;

    public static Pane appRoot = new Pane();
    public static Pane gameRoot = new Pane();

    public Character player;
    public Monster monster1;
    int levelNumber = 0;
    private int levelWidth;

    private void initContent(){
        ImageView backgroundIV = new ImageView(backgroundImg);
        backgroundIV.setFitHeight(14*BLOCK_SIZE);
        backgroundIV.setFitWidth(212*BLOCK_SIZE);

        levelWidth = LevelData.levels[levelNumber][0].length()*BLOCK_SIZE;
        for(int i = 0; i < LevelData.levels[levelNumber].length; i++){
            String line = LevelData.levels[levelNumber][i];
            for(int j = 0; j < line.length();j++){
                switch (line.charAt(j)){
                    case '0':
                        break;
                    case '1':
                        Block platformFloor = new Block(Block.BlockType.PLATFORM, j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case '2':
                        Block brick = new Block(Block.BlockType.BRICK,j*BLOCK_SIZE,i*BLOCK_SIZE);
                        break;
                    case '3':
                        Block bonus = new Block(Block.BlockType.BONUS,j*BLOCK_SIZE,i*BLOCK_SIZE);
                        break;
                    case '4':
                        Block stone = new Block(Block.BlockType.STONE,j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case '5':
                        Block PipeTopBlock = new Block(Block.BlockType.PIPE_TOP,j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case '6':
                        Block PipeBottomBlock = new Block(Block.BlockType.PIPE_BOTTOM,j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case '*':
                        Block InvisibleBlock = new Block(Block.BlockType.INVISIBLE_BLOCK,j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                }
            }

        }
        player =new Character();
        player.setTranslateX(0);
        player.setTranslateY(400);
        player.translateXProperty().addListener((obs,old,newValue)->{
            int offset = newValue.intValue();
            if(offset>640 && offset<levelWidth-640){
                gameRoot.setLayoutX(-(offset-640));
                backgroundIV.setLayoutX(-(offset-640));
            }
        });
        
        monster1 = new Monster();
        monster1.setTranslateX(800);
        monster1.setTranslateY(530);       

        
        gameRoot.getChildren().add(player);
        gameRoot.getChildren().add(monster1);

        appRoot.getChildren().addAll(backgroundIV,gameRoot);
        

     }

    private void update(){
        if(isPressed(KeyCode.UP) && player.getTranslateY()>=5){
            player.jumpPlayer();
        }
        if(isPressed(KeyCode.LEFT) && player.getTranslateX()>=5){
            player.setScaleX(-1);
            player.animation.play();
            player.moveX(-5);
        }
        if(isPressed(KeyCode.RIGHT) && player.getTranslateX()+40 <=levelWidth-5){
            player.setScaleX(1);
            player.animation.play();
            player.moveX(5);
        }
        if(player.playerVelocity.getY()<10){
           player.playerVelocity = player.playerVelocity.add(0,1);
        }
        player.moveY((int)player.playerVelocity.getY());
        
        
        if(monster1.playerVelocity.getY()<10){
        	monster1.playerVelocity = player.playerVelocity.add(0,1);
         }
        monster1.moveY((int)monster1.playerVelocity.getY());
        monster1.Swing();

    }
    private boolean isPressed(KeyCode key){
        return keys.getOrDefault(key,false);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        initContent();
        Scene scene = new Scene(appRoot,1200,620);
        scene.setOnKeyPressed(event-> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
             player.animation.stop();
        });
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startmario01.fxml"));
        StartsceneController startscenecontroller = new StartsceneController();
        fxmlLoader.setController(startscenecontroller);
        Parent root = fxmlLoader.load();
        Scene gamescene = new Scene ( root, 600, 400 );
        startscenecontroller.setScene(scene,primaryStage);
        primaryStage.setTitle("Mini Mario");
        primaryStage.setScene(gamescene);
        primaryStage.show();
        MediaPlayer mediaPlayer = new MediaPlayer( new Media (new File("mariosound.mp3").toURI().toString()));
    	mediaPlayer.setCycleCount(50);
        mediaPlayer.getCycleCount();
    	mediaPlayer.play();    		
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
