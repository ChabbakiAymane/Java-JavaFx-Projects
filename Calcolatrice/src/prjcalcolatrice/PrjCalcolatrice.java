/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjcalcolatrice;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ayman
 */
public class PrjCalcolatrice extends Application {

    final static int NBUTTON = 10;
    Button num[] = new Button[NBUTTON];

    @Override
    public void start(Stage primaryStage) {
        MyLabel txt = new MyLabel();
        txt.setText("Ciao");

        for (int i = 0; i < NBUTTON; i++) {
            num[NBUTTON - i - 1] = new MyButton(i);
        }

        MyButton btnSomma = new MyButton("+");
        MyButton btnSottrazione = new MyButton("-");
        MyButton btnProdotto = new MyButton("*");
        MyButton btnDivisione = new MyButton("/");

        MyVbox operazioni = new MyVbox();
        operazioni.getChildren().addAll(btnSomma, btnSottrazione, btnProdotto, btnDivisione);

        MyButton btnCanc = new MyButton("C");
        btnCanc.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                txt.setText("");
            }
        });

        MyButton btnVirgola = new MyButton(",");

        TilePane numeri = new TilePane();
        numeri.setVgap(5);
        numeri.setHgap(5);
        numeri.setPrefColumns(3);
        numeri.setPrefRows(4);

        numeri.getChildren().addAll(num);
        numeri.getChildren().addAll(btnVirgola, btnCanc);

        BorderPane root = new BorderPane();
        BorderPane.setAlignment(root, Pos.TOP_CENTER);
        root.setTop(txt);

        root.setBottom(new Button("="));
        root.setRight(operazioni);
        root.setCenter(numeri);

        Scene scene = new Scene(root, 250, 350);

        primaryStage.setTitle("Calcolatrice");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
