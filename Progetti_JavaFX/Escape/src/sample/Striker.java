package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Striker extends Enemy {
    public Striker() {
        super(Color.BLUE);
    }

    public Circle getCircle(){
        return super.getCircle();
    }
}
