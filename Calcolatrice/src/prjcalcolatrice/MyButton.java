/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjcalcolatrice;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Ayman
 */
public class MyButton extends Button {

    public MyButton(int i) {
        super("" + i);
        String text = "" + i;
        this.setId(text);
        this.setFont(Font.font(text, FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20));
        this.setPrefWidth(50);
        this.setPrefHeight(50);
    }

    public MyButton(String c) {
        super(c);
        this.setId(c);
        this.setFont(Font.font(c, FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20));
        this.setPrefWidth(50);
        this.setPrefHeight(50);
    }
}
