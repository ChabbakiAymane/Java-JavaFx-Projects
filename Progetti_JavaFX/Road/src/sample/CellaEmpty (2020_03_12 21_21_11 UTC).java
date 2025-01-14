package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public abstract class CellaEmpty{
    protected Rectangle rectangle = null;
    protected StackPane stackPane = null;

    protected boolean colore; //Variabile per identificare se la casella è prato o strada
    protected int tipo; //Identifica il tipo di strada posizonata nella casella (N,S,E,O)
    protected boolean stato = false;

    public boolean getStato(){
        return stato;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    //METODO ASTRATTI
    /*public abstract int getTipo();
    public abstract void scopri();
    public abstract void reset();
    public abstract void evidenzia();
    public abstract void normale();*/
}