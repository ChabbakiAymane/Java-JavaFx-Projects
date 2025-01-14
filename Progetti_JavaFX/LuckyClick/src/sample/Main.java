package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    int punteggio = 0;
    int tentativi = 10;

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();

        //----------------LABEL PUNTEGGIO E TENTATIVI-----------------
        Label lbPunteggio = new Label("Punteggio: " + punteggio);
        Label lbTentativi = new Label("Tentativi: " + tentativi);

        lbPunteggio.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lbTentativi.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        //GRIGLIA DI GIOCO
        Griglia griglia = new Griglia();
        griglia.getGridPane().setOnMouseClicked( e -> {
            punteggio = griglia.getPunteggio();
            tentativi = griglia.getTentativi();
            //Controllo tentativi
            if(tentativi > 0){
                lbPunteggio.setText("Punteggio: " + punteggio);
                lbTentativi.setText("Tentativi: " + tentativi);
            }else{
                lbTentativi.setText("GAME OVER!");
                griglia.bloccaGioco(); //blocco i click
            }
        });
        root.setCenter(griglia.getGridPane());

        //----------------BOTTONE RESET-----------------
        Button btnReset = new Button("Reset");
        btnReset.setOnMouseClicked(e -> {
            griglia.reset();
            punteggio = griglia.getPunteggio();
            tentativi = griglia.getTentativi();
            lbPunteggio.setText("Punteggio: " + punteggio);
            lbTentativi.setText("Tentativi: " + tentativi);
        });

        //-------------VERTICAL BOX CON LABEL-------------
        VBox text = new VBox();
        text.setSpacing(5);
        text.setAlignment(Pos.CENTER);
        text.getChildren().addAll(lbPunteggio,lbTentativi);

        HBox top = new HBox();
        top.getChildren().addAll(text, btnReset);
        top.setAlignment(Pos.CENTER);
        top.setSpacing(20);
        root.setTop(top);

        Scene scene = new Scene(root);

        primaryStage.setTitle("TRE!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
