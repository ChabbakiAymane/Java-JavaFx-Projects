package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class User extends Circle {
    private Circle circle;
    private static final int raggio = 20;

    public User() {
        circle = new Circle(raggio, Color.ORANGE);;
        circle.setStroke(Color.BLACK);
    }

    public User(Color color) {
        circle = new Circle(raggio, color);;
        circle.setStroke(Color.BLACK);
    }

    public Circle getCircle(){
        return this.circle;
    }
}
