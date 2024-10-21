package screens;

import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import components.CustomButton;
import components.CustomText; 
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;



// instructions on how to play the game
public class HowToPlayScreen extends NewScreen{
    

    public HowToPlayScreen(Stage primaryStage, double width, double height) {
        super(primaryStage, width, height);
        createScreen();
    }


    @Override
    public void createScreen() {
    	screenPane.setStyle("-fx-background-color: black;");
    	setTitle("How To Play");


        String instruction1txt = "Play as Fin and navigate through the sea while trying to avoid the rocks falling from above. Use the W and"
        		+ " to avoid the rocks falling from above. Use the W and S key to move up and down, try to get as far as you can!";

        String instruction2txt = "These are the obstacles you must try to avoid during"
        		+ "your journey with Fin. One hit fin will pass out!";
        
        CustomText title_txt = new CustomText("How To Play", "Century", 80, Color.DEEPSKYBLUE, width/2, height/10);
        CustomText instruction_1 = new CustomText(instruction1txt, "Century", 28, Color.DEEPSKYBLUE, width/2, height/4);
        CustomText instruction_2 = new CustomText(instruction2txt, "Century", 28, Color.DEEPSKYBLUE, width/2, height/1.5);

        
        //setting wrapping width so that it doesn't go off screen
        instruction_1.setWrappingWidth(width/2);
        instruction_2.setWrappingWidth(width/2);
        
        // new grid pane to make column and rows
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(width/30, 0, 0, -width/30));
        gridPane.setAlignment(Pos.CENTER);


        // Create the ImageView for the first column of the first row
        ImageView obstacleImg = new ImageView(new Image("https://i.ibb.co/vjPvV65/grey-large.png"));
        obstacleImg.setFitWidth(200);
        obstacleImg.setFitHeight(200);
        
        
        // Create the ImageView for the first column of the second row
        ImageView finImg = new ImageView(new Image("https://i.ibb.co/zXWTWZS/Fin.png"));
        finImg.setFitWidth(300);
        finImg.setFitHeight(150);
        
        // Add the nodes to the GridPane for the first row
        gridPane.add(obstacleImg, 0, 0);
        gridPane.add(instruction_1, 1, 0);
        GridPane.setHalignment(obstacleImg, HPos.CENTER);
        GridPane.setValignment(obstacleImg, VPos.CENTER);

        gridPane.add(finImg, 0, 1);
        gridPane.add(instruction_2, 1, 1);
        GridPane.setHalignment(finImg, HPos.CENTER);
        GridPane.setValignment(finImg, VPos.CENTER);


        // Set the width of the first and second columns to 50% of the screen width
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();       
        column1.setPercentWidth(50);
        column2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(column1, column2);

        // Set the width of the first and second row to 40% of the screen height
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();  
        row1.setPercentHeight(40);
        row2.setPercentHeight(40);

        // constraining the rows 
        gridPane.getRowConstraints().addAll(row1, row2);
        


        CustomButton mainscreen_btn = new CustomButton(primaryStage, "goto_mainscreen", "Home", "pink", 20, 20, width/10, height/10);
        
        HBox btn = new HBox();
        btn.setAlignment(Pos.BOTTOM_CENTER);
        btn.getChildren().addAll(mainscreen_btn);      
        HBox.setMargin(mainscreen_btn, new Insets(20, 0, height/30, 0));
     
        screenPane.setCenter(gridPane);
        screenPane.setBottom(btn);
        screenPane.getChildren().addAll(title_txt);


    }

}