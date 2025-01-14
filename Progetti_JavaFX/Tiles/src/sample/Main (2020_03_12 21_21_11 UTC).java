package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main extends Application {
    private static int nTile = 6;
    private Random rand = new Random();
    private ArrayList<CircleTile> circle = new ArrayList<CircleTile>();
    private ArrayList<SquareTile> square = new ArrayList<SquareTile>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane borderPane = new BorderPane();
        HBox orrizontale = new HBox();
        VBox verticale = new VBox();
        HBox btn = new HBox();

        Button btnReset = new Button("Reset");
        Button btnOrdina = new Button("Riordina");

        btnOrdina.setOnMouseClicked(e ->{
            ordina(verticale);
        });

        borderPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
               CircleTile circleTile = new CircleTile();
               SquareTile squareTile = new SquareTile();
               int tasto = 0;
                switch (event.getCode()){
                    case DIGIT0: tasto = 0; break;
                    case DIGIT1: tasto = 1; break;
                    case DIGIT2: tasto = 2; break;
                    case DIGIT3: tasto = 3; break;
                    case DIGIT4: tasto = 4; break;
                    case DIGIT5: tasto = 5; break;
                    case DIGIT6: tasto = 6; break;
                    case DIGIT7: tasto = 7; break;
                    case DIGIT8: tasto = 8; break;
                    case DIGIT9: tasto = 9; break;
                    case R: ordina(verticale); break;
                }

                circleTile = trovaIndiceCircle(tasto);
                squareTile = trovaIndiceSquare(tasto);

                if(circleTile != null){
                    int num = (circleTile.getNum() + 1);
                    if(circleTile.getNum() >= 9) circleTile.setNum(1); else circleTile.setNum(num);
                    //TODO: SISTEMARE STA MERDA;
                    controllaCopieCircle(circleTile, orrizontale, verticale);
                }
                if(squareTile != null){
                    int num = (squareTile.getNum() - 1);
                    if(squareTile.getNum() <= 0) squareTile.setNum(9); else squareTile.setNum(num);
                    //TODO: SISTEMARE STA MERDA;
                    controllaCopieSquare(squareTile, orrizontale, verticale);
                }
            }
        });

        btnReset.setOnMouseClicked(e ->{
            circle.clear();
            square.clear();
            verticale.getChildren().clear();
            orrizontale.getChildren().clear();
            inizializzaCampo(verticale, orrizontale);
        });

        btn.getChildren().addAll(btnReset, btnOrdina);

        //AGGIUNTO COMPONENTI NEL BORDERPANE
        borderPane.setTop(btn);
        borderPane.setCenter(verticale);
        borderPane.setBottom(orrizontale);

        //SETTO POSIZIONE COMPONENTI
        verticale.setAlignment(Pos.CENTER);
        orrizontale.setAlignment(Pos.CENTER);
        btnOrdina.setAlignment(Pos.TOP_LEFT);

        inizializzaCampo(verticale, orrizontale);

        primaryStage.setTitle("Tile");
        primaryStage.setScene(new Scene(borderPane, nTile*70, nTile*70));
        primaryStage.show();
    }

    private void inizializzaCampo(VBox verticale, HBox orrizontale){
        boolean presente = false;
        boolean shift;
        while(circle.size() + square.size() != nTile){
            int n = rand.nextInt(50);
            if(n%2 == 0){
                CircleTile cerchio = new CircleTile();
                for(int k = 0; k < circle.size() && !presente; k++){
                    if(cerchio.equals(circle.get(k))){
                        presente = true;
                    }
                }
                if(!presente){
                    circle.add(cerchio);
                    verticale.getChildren().add(cerchio.getStackPane());
                    cerchio.getStackPane().setOnMouseClicked(e ->{
                        int num = (cerchio.getNum() + 1);
                        if(cerchio.getNum() >= 9) cerchio.setNum(1); else cerchio.setNum(num);
                        //TODO: SISTEMARE STA MERDA;
                        if(!controllaCopieCircle(cerchio, orrizontale, verticale)){
                            verticale.getChildren().remove(cerchio.getStackPane());
                            int indice = square.size() + circle.size() - 1;
                            verticale.getChildren().add(indice, cerchio.getStackPane());
                        }
                    });
                }
                presente = false;
            }else{
                SquareTile quadrato = new SquareTile();
                for(int k = 0; k < square.size() && !presente; k++){
                    if(quadrato.equals(square.get(k))){
                        presente = true;
                    }
                }
                if(!presente){
                    square.add(quadrato);
                    verticale.getChildren().add(quadrato.getStackPane());
                    quadrato.getStackPane().setOnMouseClicked(e ->{
                        int num = (quadrato.getNum() - 1);
                        if(quadrato.getNum() <= 0) quadrato.setNum(9); else quadrato.setNum(num);
                        //TODO: SISTEMARE STA MERDA;
                        if(!controllaCopieSquare(quadrato, orrizontale, verticale)){
                            verticale.getChildren().remove(quadrato.getStackPane());
                            int indice = 0;
                            verticale.getChildren().add(indice, quadrato.getStackPane());
                        }
                    });
                }
                presente = false;
            }
        }
    }

    private void ordina(VBox verticale){
        verticale.getChildren().clear();
        ArrayList<Tile> tmp = new ArrayList<Tile>();

        tmp.addAll(circle);
        tmp.addAll(square);

        Collections.sort(tmp);

        for(Tile tile : tmp){
            verticale.getChildren().add(tile.getStackPane());
        }
    }

    private CircleTile trovaIndiceCircle(int num) {
        for (CircleTile circleTile : circle) {
            if (circleTile.getNum() == num) {
                return circleTile;
            }
        }
        return null;
    }

    private SquareTile trovaIndiceSquare(int num){
        for(SquareTile squareTile: square){
            if(squareTile.getNum() == num){
                return squareTile;
            }
        }
        return null;
    }

    private boolean controllaCopieCircle(Tile tile, HBox orrizontale, VBox verticale) {
        boolean eliminato = false;
        boolean trovato = false;
        int indicePrimo = -1;
        int indiceSecondo = -1;

        for(int i = 0; i < circle.size() && !trovato; i++){
            if(circle.get(i).getNum() == tile.getNum()){
                trovato = true;
                indicePrimo = i;
            }
        }

        for(int j = 0; j < circle.size() && !eliminato; j++){
            if(circle.get(j).getNum() == tile.getNum() && j != indicePrimo ){
                eliminato = true;
                indiceSecondo = j;
            }
        }

        if(eliminato){
            verticale.getChildren().removeAll(circle.get(indicePrimo).getStackPane(), circle.get(indiceSecondo).getStackPane());
            orrizontale.getChildren().addAll(circle.get(indicePrimo).getStackPane(), circle.get(indiceSecondo).getStackPane());
            circle.get(indicePrimo).getStackPane().setOnMouseClicked(null);
            circle.get(indiceSecondo).getStackPane().setOnMouseClicked(null);
        }
        return eliminato;
    }

    private boolean controllaCopieSquare(Tile tile, HBox orrizontale, VBox verticale) {
        boolean eliminato = false;
        boolean trovato = false;
        int indicePrimo = -1;
        int indiceSecondo = -1;

        for(int i = 0; i < square.size() && !trovato; i++){
            if(square.get(i).getNum() == tile.getNum()){
                trovato = true;
                indicePrimo = i;
            }
        }

        for(int j = 0; j < square.size() && !eliminato; j++){
            if(square.get(j).getNum() == tile.getNum() && j != indicePrimo ){
                eliminato = true;
                indiceSecondo = j;
            }
        }

        if(eliminato){
            verticale.getChildren().removeAll(square.get(indicePrimo).getStackPane(), square.get(indiceSecondo).getStackPane());
            orrizontale.getChildren().addAll(square.get(indicePrimo).getStackPane(), square.get(indiceSecondo).getStackPane());
            square.get(indicePrimo).getStackPane().setOnMouseClicked(null);
            square.get(indiceSecondo).getStackPane().setOnMouseClicked(null);
        }
        return eliminato;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
