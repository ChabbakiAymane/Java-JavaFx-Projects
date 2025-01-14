/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjslotmachine;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Ayman
 */
public class PrjSlotMachine extends Application {
    public static final int NUM_MONETE = 3;
    public static final int NUM_SPINNER = 3;
    public static final int NUM_FIGURE = 6;
    public static final int COSTO_PARTITA = 100;
    public static final int PUNTI_PER_PARTITA = 128;
    public static final int PUNTI_PER_MONETA = 100;
    public static final int NUM_COLORE_FIGURE = 5;
    
    static Random randomGenerator = new Random();
    
    @Override
    public void start(Stage primaryStage) {
        /*MONETE*/
        Coin c1 = new Coin();
        Coin c2 = new Coin();
        Coin c3 = new Coin();
        
        Title tl = new Title();
        /*--------------------------------------------------------------------*/
        /*PULSATI*/
        Button btnNewGame = new Button();   btnNewGame.setText("New Game");
        Button btnSpin = new Button();      btnSpin.setText("Spin");
        Button btnPay = new Button();       btnPay.setText("New Game");
        /*--------------------------------------------------------------------*/
        /*--------------------------------------------------------------------*/
        /*EVENTI*/
        btnNewGame.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        /*--------------------------------------------------------------------*/
        /*ELEMENTI SLOT MACHINE*/
        TilePane btn = new TilePane();
        btn.setVgap(10); btn.setHgap(10); btn.setPrefColumns(3);
        btn.getChildren().addAll(btnNewGame,btnSpin, btnPay);
        
        Pane coins = new VBox();
        coins.getChildren().addAll(c1,c2,c3);
        /*--------------------------------------------------------------------*/
        BorderPane slotmachine = new BorderPane();
        BorderPane.setAlignment(tl, Pos.TOP_CENTER);
        BorderPane.setAlignment(coins, Pos.CENTER_RIGHT);
        
        
        slotmachine.setTop(tl);
        slotmachine.setRight(coins);
        Scene scene = new Scene(slotmachine, 800, 550);
        
        primaryStage.setTitle("Slot Machine");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
