package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Classe astratta per le caselle nere e bianche che compongono la scacchiera
 */
public abstract class CellaEmpty{
    protected Rectangle rectangle = null;
    protected StackPane stackPane = null;
    protected Circle circle = null;
    protected Text text = null;

    protected boolean colore; //Variabile per identificare se la casella è nera o bianca
    protected int tipo; //Identifica il tipo di pedina posizonata nella casella
    protected boolean stato = false;

    protected static int indicePedone = 1;
    protected static int indiceTorre = 1;
    protected static int indiceAlfiere = 1;

    /**
     * Funzione che restituisce la variabile stato, che specifica se sulla casella è già presente una pedina
     * @return
     */
    public boolean getStato(){
        return stato;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    //METODO ASTRATTI
    public abstract int getTipo();
    public abstract void scopri(int scelta);
    public abstract void reset();
    public abstract void evidenzia();
    public abstract void normale();
}
