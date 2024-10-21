package event_listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import screens.GameScreen;
import screens.MainScreen;
import screens.ShopScreen;
import screens.HowToPlayScreen;
import components.Database;

// handles action event
public class ButtonEvent implements EventHandler<ActionEvent> {

    private Stage primaryStage;
    Database db = new Database();


    public ButtonEvent(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();
        String shopBtnId = buttonId.split("_")[0];
        System.out.println(shopBtnId + " --> " + buttonId);

        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();

        if(shopBtnId.equals("sprit")){

        	switch(buttonId.split("_")[1].toLowerCase()) {
        	
        		case "yellow":
        			db.updateVariable("FISH_URL", "https://i.ibb.co/CQp02H2/Fish.gif");

        			if(!isOwned("sprit_yellow")) subtractShells(ShopScreen.shell_cost[0]);
        			break;
        		case "red":
        			db.updateVariable("FISH_URL", "https://i.ibb.co/j6nQYQr/final-red-sprit.png"); 
        			if(!isOwned("sprit_red")) subtractShells(ShopScreen.shell_cost[1]);
        			break;
        		case "purple":
        			db.updateVariable("FISH_URL", "https://i.ibb.co/MCpwKkd/final-purple-sprit.png");  
        			if(!isOwned("sprit_purple")) subtractShells(ShopScreen.shell_cost[2]);
        			break;
        		case "green":
        			db.updateVariable("FISH_URL", "https://i.ibb.co/jgs3vp6/final-green-sprit.png");  
        			if(!isOwned("sprit_green")) subtractShells(ShopScreen.shell_cost[3]);
        			break;
        		case "blue":
        			db.updateVariable("FISH_URL", "https://i.ibb.co/XZwWQfV/final-blue-sprit.png"); 
        			if(!isOwned("sprit_blue")) subtractShells(ShopScreen.shell_cost[4]);
        			break;        			        			
        		case "black":
        			db.updateVariable("FISH_URL", "https://i.ibb.co/mTkrZBn/final-black-sprit.png");   
        			if(!isOwned("sprit_black")) subtractShells(ShopScreen.shell_cost[5]);
        			break;

        	}
            primaryStage.setScene(new ShopScreen(primaryStage, width, height).getScene());
        	
        }

        switch(buttonId){
            case "goto_gamescreen":
                primaryStage.setScene(new GameScreen(primaryStage, width, height).getScene());
                break;
            case "goto_mainscreen":
                primaryStage.setScene(new MainScreen(primaryStage, width, height).getScene());
                break;
            case "goto_shopscreen":
                primaryStage.setScene(new ShopScreen(primaryStage, width, height).getScene());
                break;
            case "goto_howtoplayscreen":
                primaryStage.setScene(new HowToPlayScreen(primaryStage, width, height).getScene());
                break;
        }

    }
    
    
    private void subtractShells(int cost) {
		db.updateVariable("shells", Integer.parseInt(db.readFromFile("shells")) - cost + "");
    }    
    
    private Boolean isOwned(String fishName) {
    	if(db.readFromFile(fishName).equals("true")){
    		return true;
    	}else {
    		db.updateVariable(fishName, "true");
    		return false;
    	}
    	
    }
}
