/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjslotmachine;

import com.sun.corba.se.pept.transport.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author Ayman
 */
public class Spinbar extends HBox implements EventHandler{
    Simbolo simbolo[] = new Simbolo[PrjSlotMachine.NUM_SPINNER];
    PrjSlotMachine sm = null;
    
    public Spinbar(PrjSlotMachine sm){
        setAlignment(Pos.CENTER);
        setSpacing(10); //Spazio tra le componenti
        this.sm = sm;
        initialize();
    }
    
    public void initialize(){
        getChildren().clear();
        for(int i = 0; i < PrjSlotMachine.NUM_SPINNER; i++){
            int tipo = PrjSlotMachine.randomGenerator.nextInt(PrjSlotMachine.NUM_FIGURE);
            int colore = PrjSlotMachine.randomGenerator.nextInt(PrjSlotMachine.NUM_COLORE_FIGURE);
            //simbolo[i] = new Simbolo(tipo, colore);
            //simbolo[i].addEventFilter(MouseEvent.MOUSE_CLICKED,this);
        }
        getChildren().addAll(simbolo);
        //Evita che si inizi il gioco con figure uguali
        if(areSymbolsEqual()) initialize();
    }
    
    private boolean areSymbolsEqual(){
        for(int i = 1; i < PrjSlotMachine.NUM_SPINNER; i++){
            if(!simbolo[0].getId().equals(simbolo[i].getId()))
                return false;
        }
        return true;
    }
}
