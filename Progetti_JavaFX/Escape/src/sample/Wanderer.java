package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Wanderer extends Enemy {

    public Wanderer() {
        super(Color.DARKBLUE);
    }

    public Circle getCircle(){
        return super.getCircle();
    }

    public int getDirezione(){
        return super.getDirezione();
    }
}
