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
public class MyHexagon extends Polygon{
    MyHexagon() {
        super(35.0,2.0, 68.0,20.0, 68.0,53.0, 35.0,70.0, 2.0,53.0, 2.0,20.0 );
        setLayoutX(30);
        setLayoutY(30);
        setStroke(Color.BLACK);
    }
}
