package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();
        GridPane btnMovimento = new GridPane();
        HBox buttom = new HBox();
        VBox btnOpzioni = new VBox();

        //GRIGLIA DI GIOCO
        Griglia griglia = new Griglia();
        griglia.setAlignment(Pos.CENTER);

        //BUTTON MOVIMENTO
        Button btnNord = new Button("Strada NORD");
        Button btnSud = new Button("Strada SUD");
        Button btnEst = new Button("Strada EST");
        Button btnOvest = new Button("Strada OVEST");
        Button btnPrato = new Button("PRATO");

        //SET BUTTON MOVIMENTO
        btnNord.setMinWidth(100);
        btnSud.setMinWidth(100);
        btnEst.setMinWidth(100);
        btnOvest.setMinWidth(100);
        btnPrato.setMinWidth(100);

        //POSIZIONAMENTO A CROCE DI BUTTON MOVIMENTO
        btnMovimento.add(btnNord, 2, 0);
        btnMovimento.add(btnSud, 2, 3);
        btnMovimento.add(btnEst, 0, 2);
        btnMovimento.add(btnOvest, 4, 2);
        btnMovimento.add(btnPrato, 2, 2);

        btnMovimento.setAlignment(Pos.CENTER);

        //BUTTON OPZIONI
        Button btnNewAuto = new Button("Aggiungi auto");
        Button btnMoveAuto = new Button("Muovi auto");
        Button btnReset = new Button("Reset");

        //SET BUTTON OPZIONI
        btnNewAuto.setMinWidth(100);
        btnMoveAuto.setMinWidth(100);
        btnReset.setMinWidth(100);

        btnOpzioni.getChildren().addAll(btnNewAuto, btnMoveAuto, btnReset);
        btnOpzioni.setAlignment(Pos.CENTER);

        //BUTTOM DEL GIOCO
        buttom.getChildren().addAll(btnMovimento, btnOpzioni);
        buttom.setAlignment(Pos.CENTER);
        buttom.setSpacing(20);

        root.setCenter(griglia.getGridPane());
        root.setBottom(buttom);
        root.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        primaryStage.setTitle("Make a road!");
        Scene scene = new Scene (root, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
