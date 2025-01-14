package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CellaBianca extends CellaEmpty {
    public CellaBianca() {
        rectangle = new Rectangle(60, 60);
        stackPane = new StackPane();
        circle = new Circle(20);
        text = new Text();

        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.WHITE);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.WHITE);
        text.setText("");

        colore = false;
        tipo = 0;
        stackPane.getChildren().addAll(rectangle, circle, text);
    }

    @Override
    public void scopri(int scelta) {
        if(!stato){
            switch (scelta){
                //PEDONE
                case 1:
                    circle.setFill(Color.YELLOW);
                    text.setText("P" + indicePedone);
                    text.setVisible(true);
                    indicePedone = indicePedone + 1;
                    stato = true;
                    tipo = scelta;
                    break;
                //TORRE
                case 2:
                    circle.setFill(Color.YELLOW);
                    text.setText("T" + indiceTorre);
                    text.setVisible(true);
                    indiceTorre = indiceTorre + 1;
                    stato = true;
                    tipo = scelta;
                    break;
                //ALFIERE
                case 3:
                    circle.setFill(Color.YELLOW);
                    text.setText("A" + indiceAlfiere);
                    text.setVisible(true);
                    indiceAlfiere = indiceAlfiere + 1;
                    stato = true;
                    tipo = scelta;
                    break;
            }
        }
    }

    public void reset(){
        tipo = 0;
        indicePedone = 1;
        indiceTorre = 1;
        indiceAlfiere = 1;

        circle.setFill(Color.WHITE);
        circle.setStroke(Color.WHITE);
        text.setText("");
        text.setVisible(false);
    }

    public void evidenzia(){
        rectangle.setFill(Color.RED);
        rectangle.setStroke(Color.RED);
        if(!stato){
            circle.setFill(Color.RED);
            circle.setStroke(Color.RED);
        }else{
            circle.setFill(Color.YELLOW);
            circle.setStroke(Color.BLACK);
        }
    }

    public void normale(){
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.WHITE);
        if(stato){
            circle.setFill(Color.YELLOW);
        }else{
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.WHITE);
        }
    }

    public int getTipo(){
        return tipo;
    }
}