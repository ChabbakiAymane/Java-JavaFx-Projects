/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjslotmachine;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Ayman
 */
public class Simbolo extends StackPane{
    String classi[] = {"MyCircle", "MyRect", "MyTriangle", "MyHexagon"};
    Color colorArray[] = {Color.AQUA, Color.BROWN, Color.RED, Color.YELLOW, Color.FORESTGREEN};
    public Simbolo(EventHandler simbolListener, int tipo, int colorIndex){
        addEventFilter(MouseEvent.MOUSE_CLICKED, simbolListener);
        setId(""+tipo+colorIndex);
        Shape tmp = null;
        String packname = this.getClass().getPackage().getName();
        String className = packname+"."+classi[tipo];
        try {
            tmp = (Shape) (Class.forName(className).newInstance());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.err.println("Impossibile istanziare la classe "+className);
        }
        
        tmp.setFill(colorArray[colorIndex]);
        
        Rectangle rect= new Rectangle(130,130);
        rect.setFill(Color.WHITE);
        rect.setStrokeWidth(7);
        rect.setStroke(Color.BLACK);
        getChildren().addAll(rect,tmp);
    }
}
