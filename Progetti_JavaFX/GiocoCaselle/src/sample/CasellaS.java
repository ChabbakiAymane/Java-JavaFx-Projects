package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.Random;

public class CasellaS extends Casella{
    private Rectangle r;
    private StackPane s;
    private Text txt = new Text();
    private boolean scoperta;
    private int valore;

    //Random
    private Random rand = new Random();

    public CasellaS(){
        super();
        this.valore = rand.nextInt(9)+1;
        this.scoperta = false;

        this.r = super.getR();
        this.s = super.getS();

        this.txt.setText(""+ valore);
        this.txt.setVisible(false);

        this.s.getChildren().addAll(this.txt);
    }

    public StackPane getS() {
        return s;
    }

    public double getValore(){
        return this.valore;
    }

    public boolean stato(){
        return this.scoperta;
    }

    public void scopri() {
        txt.setVisible(true);
        this.scoperta = true;
    }

    public void nascondi() {
        txt.setVisible(false);
        this.scoperta = false;
    }
}
