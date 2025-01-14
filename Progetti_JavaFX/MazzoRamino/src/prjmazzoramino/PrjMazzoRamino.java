/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjmazzoramino;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author aymane.chabbaki
 */
public class PrjMazzoRamino extends Application {

    public final int numero = 10;

    @Override
    public void start(Stage primaryStage) {

        Mazzo m = new Mazzo();
        System.out.println(m);

        m.mescola();

        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Numero");
        dialog.setHeaderText("Inserisci un numero maggiore di 0 e minore di 52.");
        dialog.setContentText("Numero:");
        boolean failure = true;
        int z = 1;
        do {
            try {
                String s = dialog.showAndWait().get();
                z = Integer.parseInt(s);

                if (z >= 2 && z <= 52) {
                    failure = false;
                } else {
                    Alert r = new Alert(Alert.AlertType.WARNING);
                    r.setTitle("ERRORE");
                    r.setContentText("Numero errato. Deve essere compreso tra 2 e 52.");
                    r.showAndWait();
                    failure = true;
                }

            } catch (NumberFormatException ex) {
                failure = true;
                Alert r = new Alert(Alert.AlertType.ERROR);
                r.setTitle("ERRORE");
                r.setContentText("Numero non valido. Inserire un numero!");
                r.showAndWait();
            }
        } while (failure);

        //LinkedList<Carta> estrazione = m.estraiCarte(z);
        Carta cartaVincente = m.coppia(z);

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("ESITO");
        if (cartaVincente != null) {
            if (m.doppiaVittoria(cartaVincente)) {
                a.setContentText("HAI VINTO DOPPIO");
            } else {
                a.setContentText("HAI VINTO");
            }
        } else {
            a.setContentText("HAI PERSO");
        }

        a.show();

        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);

        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        disegnaCarta(gc, cartaVincente);
        root.getChildren().add(canvas);

        primaryStage.setTitle("Mazzo di Ramino");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void disegnaCarta(GraphicsContext gc, Carta vincente) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        gc.strokeText("La carta che ha vinto è: ", 5, 50, 200);
        gc.fillText(vincente.toString(), 10, 200, 300);
           
        switch (vincente.getSeme()) {
            case Carta.C:
                BufferedImage img =ImageIO.read(vincente.getImg());
                ImageIcon icon=new ImageIcon(img);
                JFrame frame=new JFrame();
                frame.setLayout(new FlowLayout());
                frame.setSize(200,300);
                JLabel lbl=new JLabel();
                lbl.setIcon(icon);
                frame.add(lbl);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                break;
            case Carta.F:
                
                break;
            case Carta.P:
                
                break;
            case Carta.Q:
                
                break;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
