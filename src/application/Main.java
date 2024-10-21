package application;

import javafx.application.Application;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import screens.MainScreen;
import screens.NewScreen;

import components.Database;

// a game called Sea Escape, the user takes control of a fish and moves it up and down to avoid obstacles while collecting shells.
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Get screen dimensions
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double width = bounds.getWidth();
        double height = bounds.getHeight();

        // Create the main screen
        NewScreen mainScreen = new MainScreen(primaryStage, width, height);
        Scene mainScene = mainScreen.getScene();
        
        Database.main(null); // Pass null since we're not using any command line arguments

        //Configure primary stage
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(true);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}

/* FROM THE C_ESCAPE/SRC DIRECTORY!!

javac --module-path "C:\Users\Dylan Patel\Desktop\JAVAFX SDK 17\javafx-sdk-17.0.13\lib" --add-modules javafx.controls application\Main.java

java --module-path "C:\Users\Dylan Patel\Desktop\JAVAFX SDK 17\javafx-sdk-17.0.13\lib" --add-modules javafx.controls -cp . application.Main
*/

/* from C_Escape directory NOT src
 java --module-path "C:\Users\Dylan Patel\Desktop\JAVAFX SDK 17\javafx-sdk-17.0.13\lib" --add-modules javafx.controls,javafx.graphics,javafx.fxml -jar SeaEscape.jar
 */