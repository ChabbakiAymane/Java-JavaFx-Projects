package sample;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Random;

public class Main extends Application {

    private static int nCaselle = 0;
    private static int punti = 0;
    private static int vittorie = 0;
    private Random rand = new Random();
    private static int VrandX, VrandY, PrandX, PrandY;

    private static Text lbPunteggio = new Text("Punteggio: " + punti);
    private static Text lbVittorie = new Text("Partite Vinte: " + vittorie);
    private static Text txtCheat = new Text("Posizione Caselle:");

    private static final Button btnReset = new Button("Reset");
    private static final Button btnCheat = new Button("Cheat");

    private CasellaP p = new CasellaP();
    private CasellaV v = new CasellaV();

    @Override
    public void start(Stage primaryStage) throws Exception{
        do{
            String response = InputDialog.getResponse();
            nCaselle = Integer.parseInt(response);
        }while(nCaselle < 3 || nCaselle > 9);

        GridPane root = new GridPane();
        Scene scene = new Scene(root, nCaselle*100, 113*nCaselle, Color.BLANCHEDALMOND);
        inizializzaCampo(root);

        v.getS().setOnMouseClicked(e -> {
            vinto(root);
        });

        p.getS().setOnMouseClicked(e -> {
            System.out.println("Hai perso!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Hai perso!");
            alert.setContentText("Sei stato sfortunato! Ritenta, avrai più fortuna la prossima volta!");
            alert.showAndWait();
            alert.close();
            primaryStage.close();
        });

        btnReset.setMinWidth(100);
        btnReset.setMaxWidth(100);
        btnReset.setPrefWidth(100);
        btnCheat.setMinWidth(100);
        btnCheat.setMaxWidth(100);
        btnCheat.setPrefWidth(100);

        btnReset.setOnMouseClicked(e -> {
            System.out.println("Reset!");
            punti = 0;
            vittorie = 0;
            lbPunteggio.setText("Punteggio: " + punti);
            lbVittorie.setText("Vittorie: " + vittorie);
            root.getChildren().clear();
            //root.getChildren().removeAll();
            txtCheat.setText("Posizione Caselle:");
            inizializzaCampo(root);
        });

        btnCheat.setOnMouseClicked(e -> {
            VBox cheat = new VBox();
            cheat.getChildren().add(txtCheat);
            Scene secondScene = new Scene(cheat, nCaselle*nCaselle*15, nCaselle*nCaselle*20);
            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Cheat");
            newWindow.setScene(secondScene);
            newWindow.show();
        });

        root.setPrefWidth(nCaselle*100*nCaselle);
        root.setPrefHeight(nCaselle*100*nCaselle);

        primaryStage.setTitle("Play the Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void inizializzaCampo(GridPane root){
        root.add(lbPunteggio, nCaselle/2,0);
        root.add(lbVittorie, nCaselle/2+1,0);

        do {
            VrandX = rand.nextInt(nCaselle)+1;
            VrandY = rand.nextInt(nCaselle)+1;

            PrandX = rand.nextInt(nCaselle)+1;
            PrandY = rand.nextInt(nCaselle)+1;
        }while(VrandX == PrandX && VrandY == PrandY );

        for(int i = 1; i < nCaselle+1; i++){
            for(int j = 1; j < nCaselle+1; j++){
                System.out.println("Casella S in posizione (" + i + ", " + j + ")");
                if((i != VrandX || j != VrandY) && (i != PrandX || j != PrandY)){
                    CasellaS r = new CasellaS();
                    //Cheat
                    String tmp = txtCheat.getText();
                    tmp = tmp + "\n" + "Casella S in posizione: (" + i + ", " + j + ") con valore: " + r.getValore();
                    txtCheat.setText(tmp);
                    r.getS().setOnMouseClicked(e -> {
                        if(!r.stato()){
                            r.scopri();
                            punti = punti + (int) r.getValore();
                            System.out.println("Valore casella aggiunto: " + r.getValore() + " Contatore: " + punti);
                            if(punti > 10) {
                                lbPunteggio.setText("Punteggio: " + punti);
                                lbPunteggio.setFill(Color.RED);
                            }else if(punti < 10){
                                lbPunteggio.setText("Punteggio: " + punti);
                                lbPunteggio.setFill(Color.BLUE);
                            }else{
                                lbPunteggio.setText("Punteggio: " + punti);
                                lbPunteggio.setFill(Color.GREEN);
                                vittorie = vittorie + 1;
                                lbVittorie.setText("Vittorie: " + vittorie);
                                vinto(root);
                            }
                        }else{
                            r.nascondi();
                            punti = punti - (int) r.getValore();
                            System.out.println("Valore casella sottratto: " + r.getValore() + " Contatore: " + punti);
                            if(punti > 10) {
                                lbPunteggio.setText("Punteggio: " + punti);
                                lbPunteggio.setFill(Color.RED);
                            }else if(punti < 10){
                                lbPunteggio.setText("Punteggio: " + punti);
                                lbPunteggio.setFill(Color.BLUE);
                            }else{
                                lbPunteggio.setText("Punteggio: " + punti);
                                lbPunteggio.setFill(Color.GREEN);
                                vinto(root);
                            }
                        }
                    });
                    root.add(r.getS(), i, j);
                }
            }
        }
        String tmp = txtCheat.getText();

        root.add(p.getS(), VrandX, VrandY);
        System.out.println("Casella P in posizione (" + VrandX + ", " + VrandY + ")");
        tmp = tmp + "\n" + "Casella P in posizione: (" + VrandX + ", " + VrandY + ")";

        root.add(v.getS(), PrandX, PrandY);
        System.out.println("Casella V in posizione (" + PrandX + ", " + PrandY + ")");
        tmp = tmp + "\n" + "Casella V in posizione: (" + PrandX + ", " + PrandY + ")";
        tmp = tmp + "\n" + "------------------------------------------------";
        txtCheat.setText(tmp);

        root.add(btnReset, nCaselle/2,nCaselle+1);
        root.add(btnCheat, nCaselle/2+1,nCaselle+1);
    }

    private void vinto(GridPane root){
        System.out.println("Complimenti, hai vinto!");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText("Hai vinto!");
        alert.setContentText("Complimenti hai vinto!");
        alert.showAndWait();
        alert.close();
        punti = 0;
        vittorie = vittorie + 1;
        lbPunteggio.setText("Punteggio: " + punti);
        lbPunteggio.setFill(Color.BLACK);
        lbPunteggio.setText("Vittorie: " + punti);
        lbVittorie.setText("Vittorie: " + vittorie);
        root.getChildren().clear();
        txtCheat.setText("Posizione Caselle:");
        inizializzaCampo(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
