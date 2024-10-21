package screens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import components.Bubble;
import components.CustomButton;
import components.CustomText;
import components.Boulder;

import java.util.HashMap;
import java.util.Map;

import components.Database;
import components.Shell;



public class GameScreen extends NewScreen {
    static Database db = new Database();

    private final double FISH_WIDTH = width/10;
    private final double FISH_HEIGHT = height/10;
    
    private final double FISH_X = width/15;
    private final double FISH_Y = height/2 - (FISH_HEIGHT/2);
    
    private final int MOVE_HEIGHT = 7;
 
    private int currentSpeed = 10;
    

    private Timeline createBoulder;
    private Timeline bgTimeline;
    private Timeline createBubble;
    private Timeline fish_movement;
    private Timeline createShell;
    private Timeline shellAnimation;

    private Boolean isGameover = false;
    private int total_shells = 0;
    private final String BACKGROUND_URL = "https://i.ibb.co/KqtGNgb/background3.png";


    public static String FISH_URL = db.readFromFile("FISH_URL");
    private CustomText shell_txt = new CustomText("Shells: " + this.total_shells, "century", (int) (width/50), Color.DARKMAGENTA, width/2, height/10);

    
    ImageView fishView = new ImageView(new Image(FISH_URL, FISH_WIDTH, FISH_HEIGHT, true, true));

    public GameScreen(Stage primaryStage, double width, double height) {
        super(primaryStage, width, height);
        createScreen();
        
    }
    
    @Override
    public void createScreen() {
        setTitle("Game");

        wallpaperAnimation();
        fish();
        bubbleAnimation();
        boulderAnimation();
        boulderAnimation();

        shellAnimation();
        
        screenPane.setRight(shell_txt);
        

    }
    private void fish() {
    	FISH_URL = db.readFromFile("FISH_URL");
        fishView.setFocusTraversable(true);
        fishView.setTranslateX(FISH_X);
        fishView.setTranslateY(FISH_Y);

        screenPane.getChildren().add(fishView);
        
        // this gap is between the outline of the fish and the top border of the image.
        final int FISH_TOP_GAP = 16;
        Map<KeyCode, Boolean> keyStates = new HashMap<>();
        fish_movement = new Timeline(new KeyFrame(Duration.millis(16), event -> {
            if (keyStates.getOrDefault(KeyCode.W, false)) {
                double newY = fishView.getTranslateY() - MOVE_HEIGHT;
                if (newY >= -FISH_TOP_GAP && newY + FISH_HEIGHT <= height) {
                    fishView.setTranslateY(newY);
                }
            }
            if (keyStates.getOrDefault(KeyCode.S, false)) {
                double newY = fishView.getTranslateY() + MOVE_HEIGHT;
                if (newY >= -FISH_TOP_GAP && newY + FISH_HEIGHT <= height) {
                    fishView.setTranslateY(newY);
                }
            }
        }));
        fish_movement.setCycleCount(Animation.INDEFINITE);
        fish_movement.play();

        screenPane.setOnKeyPressed(event -> {
            keyStates.put(event.getCode(), true);
        });
        screenPane.setOnKeyReleased(event -> {
            keyStates.put(event.getCode(), false);
        });
    }


    private void wallpaperAnimation() {

        ImageView backgroundView1 = new ImageView(new Image(BACKGROUND_URL));
        ImageView backgroundView2 = new ImageView(new Image(BACKGROUND_URL));
        
        backgroundView1.setFitWidth(width);
        backgroundView1.setFitHeight(height);
        backgroundView2.setFitWidth(width);
        backgroundView2.setFitHeight(height);
        backgroundView1.setTranslateX(-width);

        screenPane.getChildren().addAll(backgroundView1, backgroundView2);

        bgTimeline = new Timeline(new KeyFrame(Duration.millis(currentSpeed), event -> {
        	
            double bg1X = backgroundView1.getTranslateX();
            double bg2X = backgroundView2.getTranslateX();

            bg1X -= 1;
            bg2X -= 1;

            if (bg1X <= -width) {
                bg1X = bg2X + width;
            }
            if (bg2X <= -width) {
                bg2X = bg1X + width;
            }

            backgroundView1.setTranslateX(bg1X);
            backgroundView2.setTranslateX(bg2X);
            
        }));

        bgTimeline.setCycleCount(Animation.INDEFINITE);
        bgTimeline.play();
        



    }

    private void bubbleAnimation() {

        Bubble bubble = new Bubble(fishView.getTranslateX()+FISH_WIDTH/2, fishView.getTranslateY(), 20);
        screenPane.getChildren().add(bubble);

        Timeline bubbleAnimation =  new Timeline(new KeyFrame(Duration.millis(250), e -> {
            if(!bubble.isAlive()) {
                bubble.Pop(screenPane);
            }
        }));
        bubbleAnimation.setCycleCount(Animation.INDEFINITE);
        bubbleAnimation.play();

        createBubble = new Timeline(new KeyFrame(Duration.millis(7500), event -> {
        	int bubble_radius = getRandomNumber(10,20);
            if (!bubble.bubbleOnScreen()) {
                Bubble newBubble = new Bubble(fishView.getTranslateX()+FISH_WIDTH/2, fishView.getTranslateY()+FISH_HEIGHT/2, bubble_radius);
                screenPane.getChildren().add(newBubble);
                Timeline newBubbleAnimation = new Timeline(new KeyFrame(Duration.millis(250), e -> {
                    if(!newBubble.isAlive()) {
                        newBubble.Pop(screenPane);
                    }
                }));
                newBubbleAnimation.setCycleCount(Animation.INDEFINITE);
                newBubbleAnimation.play();
            }
        }));
        createBubble.setCycleCount(Animation.INDEFINITE);
        createBubble.play();
    }

    private void boulderAnimation() {
        createBoulder = new Timeline(new KeyFrame(Duration.seconds(4), event -> {
            int randomY = getRandomNumber(0, (int)height/2); // generate a random x-coordinate for the boulder
            int randomRadius = getRandomNumber(25,40);
            Boulder boulder = new Boulder(width-randomRadius, randomY, randomRadius);
            screenPane.getChildren().add(boulder);

            Timeline boulderAnimation = new Timeline(new KeyFrame(Duration.millis(50), e -> {
                if (!boulder.isAlive()) {
                	boulder.remove(screenPane);
                	
                }
                if(boulder.isColliding(fishView.getTranslateX(), fishView.getTranslateY(), fishView.getTranslateX()+FISH_WIDTH/2, fishView.getTranslateY() + FISH_HEIGHT/2, randomRadius)) {
                    handleGameOver();
                }
            }));
            boulderAnimation.setCycleCount(Animation.INDEFINITE);
            boulderAnimation.play();
        }));
        createBoulder.setCycleCount(Animation.INDEFINITE);
        createBoulder.play();
    }


    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public void setFishUrl(String url) {
        FISH_URL = url;
    }

    private void handleGameOver() {
    	if(isGameover == true) return;
        createBoulder.stop();
        createBubble.stop();
        bgTimeline.stop();
        fish_movement.stop();
        createShell.stop();
        
        CustomButton home_btn = new CustomButton(primaryStage, "goto_mainscreen", "Home", "pink", 20, 20, width/10, height/13);
        CustomText gameOverTxt = new CustomText("Game Over", "century", (int) (width/50), Color.DARKMAGENTA, width/2, height/10);
        
        screenPane.getChildren().add(gameOverTxt);
        screenPane.setCenter(home_btn);
        
        db.updateVariable("shells", (Integer.parseInt(db.readFromFile("shells")) + total_shells) +"");
        
        isGameover = true;
        
    }
    
    private void handleShell() {
        Timeline addShell = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
	        total_shells++;
	        shell_txt.setText("Shells: " + total_shells);
	        screenPane.setRight(shell_txt);
        }));
        addShell.setCycleCount(1);
        addShell.play();
    }

    private void shellAnimation() {
        final int shell_radius = (int)width/50;

        
        createShell = new Timeline(new KeyFrame(Duration.seconds(4), event -> {
            int randomY = getRandomNumber(shell_radius, (int)height-shell_radius);
            Shell shell = new Shell(width-(shell_radius/2), randomY, shell_radius);
            screenPane.getChildren().add(shell);

            shellAnimation = new Timeline(new KeyFrame(Duration.millis(50), e -> {
                if (!shell.isAlive()) {
                    shell.remove(screenPane);
                    shellAnimation.stop();                   
                }
                if(shell.isColliding(fishView.getTranslateX(), fishView.getTranslateY(), fishView.getTranslateX()+FISH_WIDTH/2, fishView.getTranslateY() + FISH_HEIGHT/2, shell_radius)) {
                    shell.remove(screenPane);
                    handleShell();
                    shellAnimation.stop();
                }
             }));

            shellAnimation.setCycleCount(Animation.INDEFINITE);
            shellAnimation.play();
        }));

        createShell.setCycleCount(Animation.INDEFINITE);
        createShell.play();
        


    }

}


