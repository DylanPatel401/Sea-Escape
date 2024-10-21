package components;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Shell extends Circle {
    private Timeline shellAnimation; 
    private boolean isAlive;
    private double centerY;
    private double centerX;
    private boolean canReturn = true;

    
    public Shell(double x, double y, double radius) {
        super(x, y, radius);
        this.centerY = y;
        this.centerX = x;
        this.isAlive = true;

        Image shellImg = new Image("https://em-content.zobj.net/source/microsoft-teams/337/spiral-shell_1f41a.png");
        this.setFill(new ImagePattern(shellImg));

	    shellAnimation = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            setCenterY(centerY);
	    	if(isAlive) {
                centerX -= 10;
	    	}
            setCenterX(centerX);
            
            if (centerX <= radius) {
                isAlive = false;
            }
	    }));
	    shellAnimation.setCycleCount(Animation.INDEFINITE);
        shellAnimation.play();
        
    }
    
	public void remove(Pane screenPane) {
        setFill(null); // Remove image from bubble
        screenPane.getChildren().remove(this); // Remove bubble from parent node
        isAlive = false;
	}
	
    static double getRandomDouble(double min, double max) {
	    return (Math.random() * (max - min)) + min;
	}

	public boolean isAlive() {
		return isAlive;
	}		

	public Boolean isColliding(double point1x, double point1y, double point2x, double point2y, double radius) {
	    // Assuming this is a Circle object
        if (!canReturn) {
            return false;
        }
        
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
	    	shellAnimation.stop();
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

