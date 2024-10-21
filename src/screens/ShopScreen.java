package screens;

import javafx.geometry.Pos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import components.CustomButton;
import components.CustomText;
import components.Database;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import javafx.geometry.Insets;


public class ShopScreen extends NewScreen {

    public static int[] shell_cost =  {5,10,150,250,500,1000};

	Database db = new Database();

    public ShopScreen(Stage primaryStage, double width, double height) {
        super(primaryStage, width, height);
        createScreen();
    }

    @Override
    public void createScreen() {
    	final double FISH_WIDTH = width/6;
    	final double FISH_HEIGHT = height/6;
    	
        setTitle("Shop");
    	screenPane.setStyle("-fx-background-color: black;");
    	        


        String[] urls = {
                "https://i.ibb.co/CQp02H2/Fish.gif",
                "https://i.ibb.co/j6nQYQr/final-red-sprit.png",
                "https://i.ibb.co/MCpwKkd/final-purple-sprit.png",
                "https://i.ibb.co/jgs3vp6/final-green-sprit.png",
                "https://i.ibb.co/XZwWQfV/final-blue-sprit.png",
                "https://i.ibb.co/mTkrZBn/final-black-sprit.png"
        };

        HBox imgRow1 = new HBox(width/15);
        HBox imgRow2 = new HBox(width/15);

        for (int i = 0; i < urls.length; i++) {
            Image image = new Image(urls[i]);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(FISH_WIDTH);
            imageView.setFitHeight(FISH_HEIGHT);
            if (i < 3) {
                imgRow1.getChildren().add(imageView);
            } else {
                imgRow2.getChildren().add(imageView);
            }
        }


		HBox btnRow1 = new HBox(width/15);
		HBox btnRow2 = new HBox(width/15);
		

        String[] button_ids = {"sprit_yellow", "sprit_red", "sprit_purple", "sprit_green", "sprit_blue", "sprit_black"};
        String[] button_color = {"#CCFF00", "Red", "Purple", "Green", "#64EAF2", "grey"};

        for (int i = 0; i < button_ids.length; i++) {
            CustomButton button = new CustomButton(primaryStage, button_ids[i] + "_" + shell_cost[i], shell_cost[i] + " " + "Shells", button_color[i], 20, width/50, FISH_WIDTH, height/25);
            
            if (shell_cost[i] > Integer.parseInt(db.readFromFile("shells"))) {
                button.setDisable(true);  
                button.setText(shell_cost[i] + "");    
            }
            
            if(db.readFromFile(button_ids[i]).equals("true")) {
                button.setDisable(false);  
                button.setText("select"); 
            }
            
            if(db.readFromFile("FISH_URL").equals(urls[i])) {
                button.setDisable(true);  
                button.setText("equipped"); 
            }

            if(i < 3) {
            	btnRow1.getChildren().add(button);
            }else {
            	btnRow2.getChildren().add(button);
            }
        }
                
       
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        
        gridPane.add(imgRow1, 0, 0);
        gridPane.add(imgRow2, 0, 2);

        gridPane.add(btnRow1, 0, 1);
        gridPane.add(btnRow2, 0, 3);
        gridPane.setPadding(new Insets(height/50, 0, 0, 0)); // 10 pixels top margin

        gridPane.setVgap(height/30);

        CustomButton mainscreen_btn = new CustomButton(primaryStage, "goto_mainscreen", "Home", "pink", 20, 20, width/10, height/13);
        HBox homescreen_btn = new HBox(mainscreen_btn);
        homescreen_btn.setAlignment(Pos.BOTTOM_CENTER);
        HBox.setMargin(mainscreen_btn, new Insets(height/30));

        CustomText titleTxt = new CustomText("Shop", "Century", (int)width/25, Color.DEEPSKYBLUE, width/2, height/10);
        CustomText shell_txt = new CustomText("Total Shells: " + db.readFromFile("shells"), "century", (int) width/35, Color.AQUA, width/2,height-165);

        screenPane.setCenter(gridPane);
        screenPane.setBottom(homescreen_btn);
        screenPane.getChildren().addAll(titleTxt, shell_txt);


    }



}