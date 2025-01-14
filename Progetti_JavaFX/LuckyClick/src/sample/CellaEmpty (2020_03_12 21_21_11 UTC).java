package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

/**
 * Classe astratta di casella
 */
public abstract class CellaEmpty {

    protected Rectangle rectangle = null;
    protected StackPane stackPane = null;
    protected Text text = null;

    protected boolean stato = false; //stato della casella (scoperta o meno)
    protected int num; //valore della casella

    protected Random rand = new Random(System.currentTimeMillis());

    public abstract int getNumero();
    public abstract int scopri();

    /**
     * Funzione che setta le proprietà grafiche della casella
     */
    public void paintContent() {
        rectangle.setFill(Color.YELLOW);
        rectangle.setStroke(Color.BLACK);
        text.setText("" + num);
        text.setVisible(false); //Il valore della casella è visibile solo se la casella viene premuta
    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
