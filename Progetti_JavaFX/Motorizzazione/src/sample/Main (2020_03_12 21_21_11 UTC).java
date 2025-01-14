package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
    Persona p = new Persona();
    Automobile a = new Automobile();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 500, Color.WHITE);

        //Creazione GridPane che contiene il layout del programma
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(0));

        //Creazione delle lable per Persone e Automobili
        TextArea label = new TextArea();
        TextArea label1 = new TextArea();

        //Set della dimensione della lable che andrà a contenere i dati delle auto e delle persone
        label.setPrefWidth(300);
        label.setPrefHeight(250);
        label1.setPrefWidth(300);
        label1.setPrefHeight(250);
        label.setEditable(false);
        label1.setEditable(false);

        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setHalignment(label1, HPos.CENTER);

        /**
         * Creazione dei vari button
         */
        Button btnMescolaPersone = new Button("Mescola");
        Button btnMescolaAuto = new Button("Mescola");

        Button btnContaPersone = new Button("Conta");
        Button btnContaAuto = new Button("Conta");

        Button btnOrdinaPersone = new Button("Ordina");
        Button btnOrdinaAuto = new Button("Ordina");

        Button btnOrdinaAnnoPersone = new Button("Ordina per anno");
        Button btnOrdinaPrezzoAuto = new Button("Ordina per prezzo");

        Button btnNewPersona = new Button("New Person'");
        Button btnNewAuto = new Button("New Auto'");

        /**
         * Set delle posizioni dei vari Button
         */
        GridPane.setHalignment(btnMescolaPersone, HPos.RIGHT);
        GridPane.setHalignment(btnMescolaAuto, HPos.LEFT);
        GridPane.setHalignment(btnContaPersone, HPos.RIGHT);
        GridPane.setHalignment(btnContaAuto, HPos.LEFT);
        GridPane.setHalignment(btnOrdinaPersone, HPos.RIGHT);
        GridPane.setHalignment(btnOrdinaAuto, HPos.LEFT);
        GridPane.setHalignment(btnOrdinaAnnoPersone, HPos.RIGHT);
        GridPane.setHalignment(btnOrdinaPrezzoAuto, HPos.LEFT);
        GridPane.setHalignment(btnNewPersona, HPos.RIGHT);
        GridPane.setHalignment(btnNewAuto, HPos.LEFT);

        /**
         * Aggiungo i vari item al GridPane
         */
        gridpane.add(label, 0, 0);
        gridpane.add(label1, 1, 0);

        gridpane.add(btnMescolaPersone, 0, 1);
        gridpane.add(btnOrdinaPersone, 0, 2);
        gridpane.add(btnContaPersone, 0, 3);
        gridpane.add(btnOrdinaAnnoPersone, 0, 4);
        gridpane.add(btnNewPersona, 0, 20);

        gridpane.add(btnMescolaAuto, 1, 1);
        gridpane.add(btnOrdinaAuto, 1, 2);
        gridpane.add(btnContaAuto, 1, 3);
        gridpane.add(btnOrdinaPrezzoAuto, 1, 4);
        gridpane.add(btnNewAuto, 1, 20);


        /**
         * Eventi suoi vari Button
         */
        //Ordina le persone per anno di nascita
        btnOrdinaAnnoPersone.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                p.ordinaAnno();
                label.setText(p.toString());
            }
        });

        //Ordina le auto per prezzo
        btnOrdinaPrezzoAuto.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                a.ordinaPrezzo();
                label1.setText(a.toString());
            }
        });

        //Ordina per cognome le persone
        btnOrdinaPersone.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                p.ordinaCognome();
                label.setText(p.toString());
            }
        });

        //Ordina per modello le auto
        btnOrdinaAuto.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                a.ordinaModello();
                label1.setText(a.toString());
            }
        });

        //Mescola e stampa le persone
        btnMescolaPersone.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                p.mescola();
                label.setText(p.toString());
            }
        });

        //Mescola e stampa le auto
        btnMescolaAuto.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                a.mescola();
                label1.setText(a.toString());
            }
        });

        //Stampa il numero di persone salvate
        btnContaPersone.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                p.contaPersone();
                System.out.println("Persone: " + p.contaPersone());
                label.setText("Persone: " + p.contaPersone());
            }
        });

        //Stampa il numero di auto salvate
        btnContaAuto.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                a.contaAuto();
                System.out.println("Auto: " + a.contaAuto());
                label1.setText("Auto: " + a.contaAuto());
            }
        });

        //Evento che inserisce la nuova persona nell'arraylist persone
        btnNewPersona.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label lbNome = new Label("Nome");
                Label lbCognome = new Label("Cognome");
                Label lbAnnoNascita = new Label("Anno Nascita");

                final TextField txtNome = new TextField();
                final TextField txtCognome = new TextField();
                final TextField txtAnnoNascita = new TextField();

                Button btnControllaInserisci = new Button("Controlla e inserisci");

                lbNome.setPrefWidth(100);
                lbCognome.setPrefWidth(100);
                lbAnnoNascita.setPrefWidth(100);

                Pane l1 = new HBox();
                Pane l2 = new HBox();
                Pane l3 = new HBox();
                Pane l = new VBox();

                l1.getChildren().addAll(lbNome, txtNome);
                l2.getChildren().addAll(lbCognome, txtCognome);
                l3.getChildren().addAll(lbAnnoNascita, txtAnnoNascita);
                l.getChildren().addAll(l1,l2,l3, btnControllaInserisci);

                Scene secondScene = new Scene(l, 230, 100);
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("New Person");
                newWindow.setScene(secondScene);
                newWindow.show();

                btnControllaInserisci.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        String annoS = txtAnnoNascita.getText();
                        String nome = txtNome.getText();
                        String cognome = txtCognome.getText();

                        //cambi vuoti
                        if(nome.length() == 0 || cognome.length() == 0 || annoS.length() == 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Errore");
                            alert.setHeaderText("Campi vuoti");
                            alert.setContentText("Controlla i campo, hai lasciato qualcosa vuota!");
                            alert.showAndWait();
                            alert.close();
                            newWindow.close();
                        }else {
                            try {
                                int anno = Integer.parseInt(txtAnnoNascita.getText());
                                boolean check = p.newPerson(nome, cognome, anno);
                                if(check){
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Errore");
                                    alert.setHeaderText("Duplicato");
                                    alert.setContentText("Errore, stai cercando di inserire un elemento già presente!");
                                    alert.showAndWait();
                                    alert.close();
                                    newWindow.close();
                                }else{
                                    System.out.println(p.toString());
                                    p.mescola();
                                    label.setText(p.toString());
                                    txtNome.setText("");
                                    txtCognome.setText("");
                                    txtAnnoNascita.setText("");
                                }
                            } catch (NumberFormatException ex) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Errore");
                                alert.setHeaderText("Errore in anno");
                                alert.setContentText("Controlla il campo anno, non hai inserito un anno valido!");
                                alert.showAndWait();
                                alert.close();
                                newWindow.close();
                            }
                        }
                    }
                });
            }
        });

        //Evento che inserisce la nuova auto nell'arraylist auto
        btnNewAuto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label lbMarca = new Label("Marca");
                Label lbModello = new Label("Modello");
                Label lbPrezzo = new Label("Prezzo");

                TextField txtMarca = new TextField();
                TextField txtModello = new TextField();
                TextField txtPrezzo = new TextField();

                Button btnControllaInserisci = new Button("Controlla e inserisci");

                lbMarca.setPrefWidth(100);
                lbModello.setPrefWidth(100);
                lbPrezzo.setPrefWidth(100);

                Pane l1 = new HBox();
                Pane l2 = new HBox();
                Pane l3 = new HBox();
                Pane l = new VBox();

                l1.getChildren().addAll(lbMarca, txtMarca);
                l2.getChildren().addAll(lbModello, txtModello);
                l3.getChildren().addAll(lbPrezzo, txtPrezzo);
                l.getChildren().addAll(l1,l2,l3, btnControllaInserisci);

                Scene secondScene = new Scene(l, 230, 100);
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("New Auto");
                newWindow.setScene(secondScene);
                newWindow.show();

                btnControllaInserisci.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        String marca = txtMarca.getText();
                        String modello = txtModello.getText();
                        String prezzoS = txtPrezzo.getText();

                        //cambi vuoti
                        if(marca.length() == 0 || modello.length() == 0 || prezzoS.length() == 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Errore");
                            alert.setHeaderText("Campi vuoti");
                            alert.setContentText("Controlla i campo, hai lasciato qualcosa vuoto!");
                            alert.showAndWait();
                            alert.close();
                            newWindow.close();
                        }else {
                            try{
                                int prezzo = Integer.parseInt(txtPrezzo.getText());
                                boolean check = a.newAuto(marca, modello, prezzo);
                                if(check){
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Errore");
                                    alert.setHeaderText("Duplicato");
                                    alert.setContentText("Errore, stai cercando di inserire un elemento già presente!");
                                    alert.showAndWait();
                                    alert.close();
                                    newWindow.close();
                                }else{
                                    System.out.println(a.toString());
                                    a.mescola();
                                    label1.setText(a.toString());
                                    txtMarca.setText("");
                                    txtModello.setText("");
                                    txtPrezzo.setText("");
                                }
                            }catch (NumberFormatException ex){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Errore");
                                alert.setHeaderText("Errore in anno");
                                alert.setContentText("Controlla il campo prezzo, non hai inserito un prezzo valido!");
                                alert.showAndWait();
                                alert.close();
                                newWindow.close();
                            }
                        }
                    }
                });
            }
        });

        root.getChildren().add(gridpane);
        primaryStage.setTitle("AAA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
