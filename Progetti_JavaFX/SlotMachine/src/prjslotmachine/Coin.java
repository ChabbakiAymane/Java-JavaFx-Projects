/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjslotmachine;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Ayman
 */
public class Coin extends StackPane{
    Coin(){
        Circle circle = new Circle(40);
        circle.setFill(Color.LIGHTBLUE);
        circle.setStroke(Color.BLACK);
        Text s = new Text ("1 Euro");
        s.setLayoutX(-20);
        s.setLayoutY(5);
        this.getChildren().addAll(circle,s);
    }
}
