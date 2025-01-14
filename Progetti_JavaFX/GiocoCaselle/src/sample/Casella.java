package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Casella extends Rectangle{
    public final Rectangle r;
    private final StackPane s;
    protected boolean scoperta;

    public Casella(){
        r = new Rectangle(100, 100);
        s = new StackPane();
        r.setFill(Color.YELLOW);
        r.setStroke(Color.BLACK);
        s.getChildren().add(r);
    }

    public Rectangle getR() {
        return r;
    }

    public StackPane getS() {
        return s;
    }

    public void scopri(){
        this.scoperta = true;
    }

    public void nascondi(){
        this.scoperta = false;
    }

    public boolean isScoperta() {
        return scoperta;
    }
}
