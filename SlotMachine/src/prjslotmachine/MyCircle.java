/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjslotmachine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Ayman
 */
public class MyCircle extends Circle{
    MyCircle() {
        super(35);
        setLayoutX(65);
        setLayoutY(65);
        setStroke(Color.BLACK);
    }
}
