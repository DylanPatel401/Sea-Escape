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
import javafx.scene.layout.Pane;

import java.util.Random;


public class Boulder extends Circle{
    private Timeline boulderAnimation; 
    private double centerY;
    private double centerX;
    private boolean isAlive;
    
    
    public Boulder(double x, double y, double radius) {

        super(x, y, radius);
        this.centerY = y;
        this.centerX = x;
        this.isAlive = true;

        Image boulderImage = new Image("https://i.ibb.co/vjPvV65/grey-large.png");
        Image sharkImage = new Image("https://i.ibb.co/ZNktbHz/download.png");

        this.setFill(getRandomDouble(0,1) >= 0.5 ? new ImagePattern(boulderImage) :  new ImagePattern(sharkImage));
        
        boulderAnimation = new Timeline(new KeyFrame(Duration.millis(10), event -> {
        	
            if (isAlive) {
                centerY += getRandomDouble(0.05,0.3);
                
                if(radius <= 30) {
                    centerX -=getRandomDouble(10.3,11);               	
                }else if(radius <= 35) {
                    centerX -=getRandomDouble(9.7,9);              	              	
                }else {
                    centerX -=getRandomDouble(8.5,8.7);               	              	               	
                }
                setCenterY(centerY);
                setCenterX(centerX);
                
            }
            
            if (centerX <= radius) {
                isAlive = false;
            }
        }));
        boulderAnimation.setCycleCount(Animation.INDEFINITE);
        boulderAnimation.play();
    }
    
    public static double getRandomDouble(double min, double max) {
	    return (Math.random() * (max - min)) + min;
	}

	public boolean isAlive() {
		return isAlive;
	}
	
	public void remove(Pane screenPane) {
        setFill(null); // Remove image from bubble
        screenPane.getChildren().remove(this); // Remove bubble from parent node
        isAlive = false;
	}

	public Boolean isColliding(double point1x, double point1y, double point2x, double point2y, double radius) {
	    // Assuming this is a Circle object

	    double circleRadius = getRadius() > 0 ? getRadius(): radius;

	    // Assuming point1 is the top-left corner of the rectangle and point2 is the bottom-right corner
	    double rectX = point1x;
	    double rectY = point1y;
	    double rectWidth = point2x - point1x;
	    double rectHeight = point2y - point1y;

	    // Calculate the distance between the center of the circle and the closest point on the rectangle
	    double closestX = clamp(centerX, rectX, rectX + rectWidth);
	    double closestY = clamp(centerY, rectY, rectY + rectHeight);
	    double distanceX = centerX - closestX;
	    double distanceY = centerY - closestY;
	    double distanceSquared = distanceX * distanceX + distanceY * distanceY;

	    // Check for collision
	    if (distanceSquared <= circleRadius * circleRadius) {
	    	boulderAnimation.stop();
	        return true;
	        
	    }else {
	    	return false;
	    }
	}

	// max & minimum range
	private double clamp(double value, double min, double max) {
	    return Math.max(min, Math.min(max, value));
	}

}




