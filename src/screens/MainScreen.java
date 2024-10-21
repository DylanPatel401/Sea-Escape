package screens;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import components.CustomButton;
import components.CustomText;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainScreen extends NewScreen {

    public MainScreen(Stage primaryStage, double width, double height) {
        super(primaryStage, width, height);
        createScreen();
    }

    @Override
    public void createScreen() {

    	
    	
        setTitle("Home");

        CustomButton gameclick_btn = new CustomButton(primaryStage, "goto_gamescreen", "Play", "blue", 20, 20, width/10, height/10);
        CustomButton shopclick_btn = new CustomButton(primaryStage, "goto_shopscreen", "Shop", "blue", 20, 20, width/10, height/10);        
        CustomButton howtoplay_btn = new CustomButton(primaryStage, "goto_howtoplayscreen", "How to play", "blue", 20, 20, width/10, height/10);
        
        HBox button_row1 = new HBox(20);
        button_row1.setAlignment(Pos.CENTER);
        button_row1.getChildren().addAll(gameclick_btn, shopclick_btn);

        HBox button_row2 = new HBox(20);
        button_row2.setAlignment(Pos.CENTER);
        button_row2.getChildren().addAll(howtoplay_btn);

        VBox button_container = new VBox(20);
        button_container.setAlignment(Pos.CENTER);
        button_container.getChildren().addAll(button_row1, button_row2);

        CustomText title = new CustomText("Sea Escape", "Century", 46, Color.DEEPSKYBLUE, width/2, height/10);
        screenPane.getChildren().add(title);
        

        createLineDivider();
        screenPane.setStyle("-fx-background-color: black;");
        screenPane.setCenter(button_container);
        
        
    }
    
    private void createLineDivider() {
        // create an Image object with the URL of the image
        Image image = new Image("https://i.ibb.co/ZLpGyLc/neon-color-divider-fotor-bg-remover-20230409214241.png");

        // create an ImageView object with the Image object
        ImageView imageView = new ImageView(image);
        // set the size of the ImageView
        imageView.setFitWidth(300);
        imageView.setFitHeight(50);

       
        // set the location of the ImageView
        imageView.setLayoutX( width/2-(width/10));
        imageView.setLayoutY(height/9);
        
        screenPane.getChildren().add(imageView);

        
    }
}
