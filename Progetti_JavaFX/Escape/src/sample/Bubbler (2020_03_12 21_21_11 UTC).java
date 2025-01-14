package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bubbler extends Enemy{

    public Bubbler() {
        super(Color.LIGHTBLUE);
    }

    public Circle getCircle(){
        return super.getCircle();
    }
}
