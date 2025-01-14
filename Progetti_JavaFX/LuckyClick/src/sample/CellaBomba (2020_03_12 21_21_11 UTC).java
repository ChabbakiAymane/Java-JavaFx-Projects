package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Casella BOMBA che estende la classe CellaEmpty
 */
public class CellaBomba extends CellaEmpty  {
    public CellaBomba(){
        rectangle = new Rectangle(50,50);
        stackPane = new StackPane();
        text = new Text();
        num = 0;
        paintContent();
        rectangle.setFill(Color.BLUE);
        text.setText("BOOM");
        stackPane.getChildren().addAll(rectangle, text);
    }

    /**
     * Funzione che scopre la casella.
     * Se non è stata ancora scoperta (stato = false):
     *   - setta stato a true
     *   - modifica graficamente la casella
     *   - rende visibile e restituisce il suo valore
     * Se è già stata scoperta, restituisce il valore fitizio -1
     * @return: restituisce il valore della casella
     */
    @Override
    public int scopri() {
        int toRtn = -1;
        if(!stato){
            stato = true;
            rectangle.setFill(Color.BLUE);
            text.setVisible(true);
            toRtn = num;
        }
        return toRtn;
    }

    @Override
    public int getNumero() {
        return num;
    }
}