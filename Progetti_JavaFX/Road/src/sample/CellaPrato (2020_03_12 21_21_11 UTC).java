package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellaPrato extends CellaEmpty  {
    public CellaPrato(){
        rectangle = new Rectangle(49,49);
        stackPane = new StackPane();
        paintContent();
        stackPane.getChildren().addAll(rectangle);
    }

    public void paintContent() {
        rectangle.setFill(Color.GREEN);
        rectangle.setStroke(Color.BLACK);
    }
}