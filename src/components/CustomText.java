package components;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CustomText extends Text {

    public CustomText(String text, String fontName, int fontSize, Color color, double x, double y) {
        super(text);
        setFont(Font.font(fontName, fontSize));
        setFill(color);
        double textWidth = getBoundsInLocal().getWidth();
        setLayoutX(x-(textWidth / 2));
        setLayoutY(y);
    }
}