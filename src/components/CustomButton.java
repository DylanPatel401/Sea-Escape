package components;

import event_listeners.ButtonEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// this custombutton class makes it faster and easier. 

public class CustomButton extends Button {
    public CustomButton( Stage primaryStage, String id, String text, String color, double borderRadius, double fontSize, double prefWidth, double prefHeight) {
        super(text);
        setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-size: " + fontSize + "; -fx-background-radius: " + borderRadius + "px;");
        setId(id);
        setOnAction(new ButtonEvent(primaryStage));
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);
        setWrapText(true);
    }
}
