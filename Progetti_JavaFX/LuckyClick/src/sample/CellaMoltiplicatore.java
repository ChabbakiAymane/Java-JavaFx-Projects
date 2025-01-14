package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Casella MOLTIPLICATORE che estende la classe CellaEmpty
 */
public class CellaMoltiplicatore extends CellaEmpty  {
    public CellaMoltiplicatore(){
        rectangle = new Rectangle(50,50);
        stackPane = new StackPane();
        text = new Text();
        num = 2;
        paintContent();
        text.setText("x2");
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
            rectangle.setFill(Color.GREEN);
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
