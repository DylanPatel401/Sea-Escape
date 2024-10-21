package screens;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import components.CustomButton;

public abstract class NewScreen {

    protected BorderPane screenPane;
    protected Stage primaryStage;
    protected double width;
    protected double height;

    public NewScreen(Stage primaryStage, double width, double height) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double screen_width = primaryScreenBounds.getWidth();
        double screen_height = primaryScreenBounds.getHeight();

        double taskbarHeight = screen_height - Screen.getPrimary().getVisualBounds().getHeight();
        screen_height -= taskbarHeight;
        
        this.primaryStage = primaryStage;
        this.width = screen_width;
        this.height = screen_height;
        this.screenPane = new BorderPane();

        this.screenPane.setPrefSize(screen_width, screen_height-35);
        this.primaryStage.setResizable(false);
        


        
    }
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Scene getScene() {
        return new Scene(screenPane);
    }

    protected void setTitle(String title) {
        primaryStage.setTitle("Sea Escape: " + title);
    }

    
    public abstract void createScreen();
}
