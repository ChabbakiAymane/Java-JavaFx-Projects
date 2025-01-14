/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjslotmachine;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
/**
 *
 * @author Ayman
 */
public class Title extends Group{
    public Title() {
        Label lab = new Label("Super Slots");
        lab.setAlignment(Pos.CENTER);
        lab.setFont(Font.font("Verdana", FontWeight.BOLD,34));
        lab.setLayoutX(200);
        lab.setLayoutY(25);
        Rectangle rect = new Rectangle(600, 100);
        rect.setFill(Color.YELLOW);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(6);
        this.getChildren().addAll(rect,lab);
    }
}
