package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class CasellaV extends Casella {
    private Rectangle r;
    private StackPane s;
    private boolean scoperta;

    public CasellaV(){
        super();
        this.r = super.getR();
        this.s = super.getS();
        this.scoperta = super.scoperta;
    }

    public void scopri() {
        super.scopri();
    }

    public void nascondi() {
        super.nascondi();
    }

    @Override
    public Rectangle getR() {
        return r;
    }

    public boolean isScoperta() {
        return scoperta;
    }

    public StackPane getS() {
        return s;
    }
}
