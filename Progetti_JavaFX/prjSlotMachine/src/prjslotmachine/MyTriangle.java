/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjslotmachine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Ayman
 */
public class MyTriangle extends Polygon{
    MyTriangle(){
        super(35.0,0.0, 0.0,70.0, 70.0,70.0);
        setLayoutX(30);
        setLayoutY(30);
        setStroke(Color.BLACK);
    }
}
