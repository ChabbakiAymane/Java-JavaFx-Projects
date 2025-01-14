/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjslotmachine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Ayman
 */
public class MyRect extends Rectangle{
    MyRect() {
        super(70,70);
        setLayoutX(30);
        setLayoutY(30);
        setStroke(Color.BLACK);
    }
}
