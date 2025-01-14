package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {
    //COMPONENTI GRAFICHE
    private BorderPane root = new BorderPane();
    private Button btnReset = new Button("Reset");
    private Button btnPedone = new Button("Pedone");
    private Button btnTorre = new Button("Torre");
    private Button btnAlfiere = new Button("Alfiere");
    private Label lbSelezione = new Label("Selected = none");

    @Override
    public void start(Stage primaryStage) throws Exception{
        int dimensione = getDimensione();
        //GRIGLIA DI GIOCO
        Griglia griglia = new Griglia(dimensione);
        griglia.setAlignment(Pos.CENTER);

        //SET ON-CLICK BUTTON EVENT
        btnReset.setOnMouseClicked(e -> {
            enableButton();
            lbSelezione.setText("Selected = none");
            griglia.reset();
        });
        btnPedone.setOnMouseClicked(e -> {
            buttonClick(btnPedone);
            griglia.posizionaElemento(1);
        });
        btnTorre.setOnMouseClicked(e -> {
            buttonClick(btnTorre);
            griglia.posizionaElemento(2);
        });
        btnAlfiere.setOnMouseClicked(e -> {
            buttonClick(btnAlfiere);
            griglia.posizionaElemento(3);
        });

        //SET ON-TYPED KEYBOARD EVENT
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case R:
                        enableButton();
                        lbSelezione.setText("Selected = none");
                        griglia.reset();
                        break;

                    case P:
                        buttonClick(btnPedone);
                        griglia.posizionaElemento(1);
                        break;

                    case T:
                        buttonClick(btnTorre);
                        griglia.posizionaElemento(2);
                        break;

                    case A:
                        buttonClick(btnAlfiere);
                        griglia.posizionaElemento(3);
                        break;
                }
            }
        });

        //COMPONENTI DI GIOCO
        HBox hButton = new HBox();
        hButton.setSpacing(10);
        hButton.setAlignment(Pos.CENTER);
        hButton.getChildren().addAll(btnReset, btnPedone, btnTorre, btnAlfiere);

        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(40);
        bottom.getChildren().addAll(hButton, lbSelezione);

        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        HBox hBox3 = new HBox();

        vBox1.setMinWidth(100);
        vBox2.setMinWidth(100);
        hBox3.setMinWidth(200);

        vBox1.setMaxWidth(100);
        vBox2.setMaxWidth(100);
        hBox3.setMaxWidth(200);

        root.setBottom(bottom);
        root.setLeft(vBox1);
        root.setRight(vBox2);
        root.setTop(hBox3);
        root.setCenter(griglia.getGridPane());

        primaryStage.setTitle("Numbers!");
        Scene scene = new Scene (root, dimensione*60 + 200, dimensione*60 + 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Funzione che abilita tutti i pulsanti
     */
    private void enableButton(){
        btnReset.setDisable(false);
        btnPedone.setDisable(false);
        btnTorre.setDisable(false);
        btnAlfiere.setDisable(false);
    }

    /**
     * Funzione che abilità i pulsanti (in caso fossero disabilitati), disabilità il pulsante premuto e setta
     * la label con il tipo di pedina scelta.
     * @param btn: pulsante premuto
     */
    private void buttonClick(Button btn){
        enableButton();
        btn.setDisable(true);
        setSelezione(btn.getText());
    }

    /**
     * Funzione che modifica la label che contiene la casella selazionata
     */
    private void setSelezione(String selezione){
        lbSelezione.setText("Selected = " + selezione);
    }

    private static int getDimensione(){
        TextInputDialog dialog = new TextInputDialog("6");
        dialog.setTitle("Scacchiera");
        dialog.setHeaderText("Dimensione Scacchiera");
        dialog.setContentText("Inserisci la dimensione della schacchiera:");
        int num = 0;
        do{
            Optional<String> result = dialog.showAndWait();
            if(result.isPresent()){
                try{
                    num = Integer.parseInt(result.get());
                }catch (NumberFormatException e){
                    dialog.setContentText("Errore! Riprova ad inserire la dimensione della scacchiera (deve essere compresa" +
                            " tra 4 e 11): ");
                }
            }
        }while (num > 11 || num < 3);
        return num;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
