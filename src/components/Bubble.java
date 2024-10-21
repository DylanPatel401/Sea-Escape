package components;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Bubble extends Circle {
    private double centerY;
    private double centerX;
    private boolean isAlive;
    private boolean isPopping;
    private boolean bubbleOnScreen;
    private boolean gone;
    

    private Timeline bubbleAnimation; // Declare the variable outside the constructor
    
    
    Image bubblePop1 = new Image("https://i.ibb.co/rQvdG6z/bubble-pop-frame-02.png");
    Image bubblePop2 = new Image("https://i.ibb.co/HCxVy87/bubble-pop-frame-03.png");
    Image bubblePop3 = new Image("https://i.ibb.co/cTpjWf1/bubble-pop-frame-04.png");
    Image bubblePop4 = new Image("https://i.ibb.co/wBbr9Cv/bubble-pop-frame-05.png");
    Image bubblePop5 = new Image("https://i.ibb.co/Bghytrj/bubble-pop-frame-06.png");
    Image bubblePop6 = new Image("https://i.ibb.co/F6XJnj0/bubble-pop-frame-07.png");
    
    public Bubble(double x, double y, double radius) {
        super(x, y, radius);
        
        gone = false;
        this.centerY = y;
        this.centerX = x;
        this.isAlive = true;
        this.isPopping = false;
        this.bubbleOnScreen = true;

        // Load bubble image
        Image bubbleImage = new Image("https://i.ibb.co/R278Q4Y/bubble-pop-frame-01.png");


        
        // Apply image to bubble
        this.setFill(new ImagePattern(bubbleImage));

        bubbleAnimation = new Timeline(new KeyFrame(Duration.millis(10), event -> {
        	
            if (!isPopping) {
                centerY -= 1; // Move bubble up the screen
                centerX -=getRandomDouble(0.3,0.5); // move bubble left 
                setCenterY(centerY);
                setCenterX(centerX);

            }
            
            if (centerY <= radius && !isPopping) {
                isAlive = false;
                isPopping = true;
            }
        }));
        bubbleAnimation.setCycleCount(Animation.INDEFINITE);
        bubbleAnimation.play();
    }

    public boolean isAlive() {
        return isAlive;
    }
    
    public boolean isPopping() {
    	return isPopping;
    }
    
    public boolean bubbleOnScreen() {
    	return bubbleOnScreen;
    }
    
    public void Pop(Pane screenPane) {
       if(!gone) {
    	
    	// Create timeline to animate bubble popping
        Timeline popAnimation = new Timeline(
            new KeyFrame(Duration.millis(100), e -> setFill(new ImagePattern(bubblePop1))),
            new KeyFrame(Duration.millis(200), e -> setFill(new ImagePattern(bubblePop2))),
            new KeyFrame(Duration.millis(300), e -> setFill(new ImagePattern(bubblePop3))),
            new KeyFrame(Duration.millis(400), e -> setFill(new ImagePattern(bubblePop4))),
            new KeyFrame(Duration.millis(500), e -> setFill(new ImagePattern(bubblePop5))),
            new KeyFrame(Duration.millis(600), e -> setFill(new ImagePattern(bubblePop6))),
            new KeyFrame(Duration.millis(700), e -> {
                setFill(null); // Remove image from bubble
                screenPane.getChildren().remove(this); // Remove bubble from parent node
                bubbleOnScreen = false;

            })
        );
        popAnimation.play();
        this.gone=true;
       }
    }

	
    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }




}