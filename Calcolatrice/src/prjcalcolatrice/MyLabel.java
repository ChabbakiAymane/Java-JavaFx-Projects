/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjcalcolatrice;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javax.swing.border.StrokeBorder;

/**
 *
 * @author Ayman
 */
public class MyLabel extends Label {

    public MyLabel() {
        super();
        this.setAlignment(Pos.CENTER);
        this.setPrefWidth(250);
        this.setPadding(new Insets(5));
    }

}
