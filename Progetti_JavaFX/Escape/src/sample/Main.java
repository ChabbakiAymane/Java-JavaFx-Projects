package sample;

import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {
    /**COSTANTI*/
    private static final int WIDTH_FINESTRA = 500;
    private static final int HEIGHT_FINESTRA = 500;
    private static final int WIDTH_FINESTRA_SECONDARIA = 100;
    private static final int HEIGHT_FINESTRA_SECONDARIA = 200;
    private static final int SPOSTAMENTO = 10;
    private static final int PUNTI = 100;
    private static final int ITERAZIONI = 10;
    private static int puntiPlayer = 0;

    //RANDOM
    private Random rand = new Random();

    //TEXT PER PUNTEGGIO
    private Text txtPunti = new Text("Punteggio: " + puntiPlayer);

    //PANE DI GIOCO
    Pane pane = new Pane();

    //ISTANZIO USER E ARRAYLIST CHE CONTIENE TUTTI GLI OGGETTI PRESENTI NEL CAMPO
    private User player = new User();
    private ArrayList<Striker> strikers = new ArrayList<Striker>();
    private ArrayList<Wanderer> wanderers = new ArrayList<Wanderer>();
    private ArrayList<Bubbler> bubblers = new ArrayList<Bubbler>();

    //CONTATORE DI ITERAZIONI
    int iter = 0;

    //POSIZIONE GIOCATORE INIZIALE
    int newPlayerX = WIDTH_FINESTRA/2;
    int newPlayerY = HEIGHT_FINESTRA/2;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //INIZIALIZZO IL CAMPO DI GIOCO
        inizializzaCampo();

        Scene scene = new Scene(pane, WIDTH_FINESTRA, HEIGHT_FINESTRA);
        primaryStage.setTitle("Escape"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show();

        scene.setOnKeyPressed(e ->{
            KeyCode mossa = e.getCode();
            if(mossa == KeyCode.DOWN || mossa == KeyCode.UP || mossa == KeyCode.LEFT || mossa == KeyCode.RIGHT){
                iter = iter + 1;
                movePlayer(mossa);
                puntiPlayer = puntiPlayer + PUNTI;
                spostaStriker();
                spostaWanderer(iter);
                spostaBubbler(iter);
                newEnemy(iter);
                gameOver(scene);
            }else e.consume();
        });
    }

    /**
     * Funzione che in base alla direzione sposta l'enemy in quella direzione.
     * @param enemy: enemy da spostare:
     *             - Stricker: ha una sola direzione
     *             - Wanderer: ogni 5 iterazioni cambia direzione
     *             - Bubbler: ogni 5 iterazioni cambia direzione e ad ogni iterazione con probabilità 10% aumenta del 20%
     *             il suo raggio
     *
     * @param direzione: direzione dell'enemy:
     *                 - 0: RIGHT
     *                 - 1: LEFT
     *                 - 2: UP
     *                 - 3: DOWN
     *                 - 4: RIGHT-UP (diagonale)
     *                 - 5: RIGHT-DOWN (diagonale)
     *                 - 6: LEFT-UP (diagonale)
     *                 - 7: LEFT-RIGHT (diagonale)
     */
    private void spostaEnemy(Enemy enemy, int direzione){
        int posX, posY;
        switch (direzione){
            case 0: //RIGHT
                posX = enemy.getPosX() + SPOSTAMENTO;
                posX = controlloBordi(true, posX);
                enemy.setPosX(posX);
                enemy.getCircle().setCenterX(posX);
                break;

            case 1: //LEFT
                posX = enemy.getPosX() - SPOSTAMENTO;
                posX = controlloBordi(true, posX);
                enemy.setPosX(posX);
                enemy.getCircle().setCenterX(posX);
                break;

            case 2: //UP
                posY = enemy.getPosY() + SPOSTAMENTO;
                posY = controlloBordi(false, posY);
                enemy.setPosY(posY);
                enemy.getCircle().setCenterY(posY);
                break;

            case 3://DOWN
                posY = enemy.getPosY() - SPOSTAMENTO;
                posY = controlloBordi(false, posY);
                enemy.setPosY(posY);
                enemy.getCircle().setCenterY(posY);
                break;

            case 4: //RIGHT-UP
                posX = enemy.getPosX() + SPOSTAMENTO;
                posY = enemy.getPosY() + SPOSTAMENTO;

                posX = controlloBordi(true, posX);
                posY = controlloBordi(false, posY);

                enemy.setPosX(posX);
                enemy.getCircle().setCenterX(posX);

                enemy.setPosY(posY);
                enemy.getCircle().setCenterY(posY);
                break;

            case 5: //RIGHT-DOWN
                posX = enemy.getPosX() + SPOSTAMENTO;
                posY = enemy.getPosY() - SPOSTAMENTO;

                posX = controlloBordi(true, posX);
                posY = controlloBordi(false, posY);

                enemy.setPosX(posX);
                enemy.getCircle().setCenterX(posX);

                enemy.setPosY(posY);
                enemy.getCircle().setCenterY(posY);
                break;

            case 6: //LEFT-UP
                posX = enemy.getPosX() - SPOSTAMENTO;
                posY = enemy.getPosY() + SPOSTAMENTO;

                posX = controlloBordi(true, posX);
                posY = controlloBordi(false, posY);

                enemy.setPosX(posX);
                enemy.getCircle().setCenterX(posX);

                enemy.setPosY(posY);
                enemy.getCircle().setCenterY(posY);
                break;

            case 7: //LEFT-DOWN
                posX = enemy.getPosX() - SPOSTAMENTO;
                posY = enemy.getPosY() - SPOSTAMENTO;

                posX = controlloBordi(true, posX);
                posY = controlloBordi(false, posY);

                enemy.setPosX(posX);
                enemy.getCircle().setCenterX(posX);

                enemy.setPosY(posY);
                enemy.getCircle().setCenterY(posY);
                break;
        }
    }

    private void spostaStriker(){
        int direzione;
        for(Striker striker:strikers){
            direzione = striker.getDirezione();

            //SPOSTO WANDERER in base alla sua direzione
            spostaEnemy(striker, direzione);
        }
    }

    private void spostaWanderer(int iter){
        int direzione;
        for(Wanderer wanderer: wanderers){
            direzione = wanderer.getDirezione();
            //OGNI 5 ITERAZIONI CAMBIO LA DIREZIONE
            if(iter % 5 == 0){
                wanderer.setDirezione(rand.nextInt(4));
                direzione = wanderer.getDirezione();
            }

            //SPOSTO WANDERER in base alla sua direzione
            spostaEnemy(wanderer, direzione);
        }
    }

    private void spostaBubbler(int iter){
        int direzione;
        for(Bubbler bubbler: bubblers){
            direzione = bubbler.getDirezione();
            //OGNI 5 ITERAZIONI CAMBIO LA DIREZIONE
            if(iter % 5 == 0){
                bubbler.setDirezione(rand.nextInt(4));
                direzione = bubbler.getDirezione();
            }

            //AUMENTO IL RAGGIO
            int cambiaRaggio = rand.nextInt(100);
            if(cambiaRaggio < 10){
                int r = bubbler.getRaggio();
                r = r + (r*20/100);
                bubbler.getCircle().setRadius(r);
            }

            //SPOSTO BUBBLER in base alla sua direzione
            spostaEnemy(bubbler, direzione);
        }
    }

    /**
     * Funzione che controlla se ci sono collisioni.
     * Cicla tutti gli Enemy presenti sul campo e confronta le loro coordiate con le coordinate del player.
     * In caso ci sia una collisione, setta i colori del player e dell'enemy a rosso e setta la variabile gameOver a true,
     * che verrà restituita al chiamante.
     */
    private void gameOver(Scene scene){
        int dist;
        boolean gameOver = false;
        if(!gameOver){
            for(Striker striker : strikers){
                dist = striker.getRaggio() + (striker.getRaggio()/2 + 4);
                if((newPlayerX > striker.getPosX() - dist && newPlayerX < striker.getPosX() + dist) && (newPlayerY > striker.getPosY() - dist && newPlayerY < striker.getPosY() + dist)){
                    player.getCircle().setFill(Color.RED);
                    striker.getCircle().setFill(Color.RED);
                    gameOver = true;
                    break;
                }
            }
            for(Wanderer wanderer : wanderers){
                dist = wanderer.getRaggio() + (wanderer.getRaggio()/2 + 4);
                if((newPlayerX > wanderer.getPosX() - dist && newPlayerX < wanderer.getPosX() + dist) && (newPlayerY > wanderer.getPosY() - dist && newPlayerY < wanderer.getPosY() + dist)){
                    player.getCircle().setFill(Color.RED);
                    wanderer.getCircle().setFill(Color.RED);
                    gameOver = true;
                    break;
                }
            }
            for(Bubbler bubbler : bubblers){
                dist = bubbler.getRaggio() + (bubbler.getRaggio()/2);
                if((newPlayerX > bubbler.getPosX() - dist && newPlayerX < bubbler.getPosX() + dist) && (newPlayerY > bubbler.getPosY() - dist && newPlayerY < bubbler.getPosY() + dist)){
                    player.getCircle().setFill(Color.RED);
                    bubbler.getCircle().setFill(Color.RED);
                    gameOver = true;
                    break;
                }
            }
        }

        if(gameOver){
            scene.setOnKeyPressed(null);
            txtPunti.setText("Points: " + puntiPlayer + "\n GAME OVER!");
            txtPunti.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));

            VBox secondaryLayout = new VBox();
            secondaryLayout.setAlignment(Pos.CENTER);
            secondaryLayout.getChildren().add(txtPunti);

            Scene secondScene = new Scene(secondaryLayout, WIDTH_FINESTRA_SECONDARIA, HEIGHT_FINESTRA_SECONDARIA);

            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Game Over");
            newWindow.setScene(secondScene);
            newWindow.show();
        }
    }

    /**
     * Funzione che controlla se la posizione di Player o Enemy è sul bordo o lo ha passato.
     * Se le coordiante X e Y sono vicine/superano il bordo, questa casella viene traslata dall'altra parte (effetto Pacman)
     * @param tipo:
     *            - true: spostamento in orrizontale (X)
     *            - false: spostamento in verticale (Y)
     *
     * @param coor: coordinate della casella da controllare
     * @return
     */
    private int controlloBordi(boolean tipo, int coor){
        if(tipo){
            //Esco dal bordo destro, devo riposizionarlo a sinistra
            if(coor > WIDTH_FINESTRA-20){
                coor = 30;
                return coor;
            }
            //Esco dal bordo sinisto, devo riposizionarlo a destra
            if(coor < 30){
                coor = WIDTH_FINESTRA-30;
                return coor;
            }
        }else{
            //Esco dal bordo in alto, devo riposizionarlo in basso
            if(coor < 30){
                coor = HEIGHT_FINESTRA-30;
                return coor;
            }
            //Esco dal bordo in basso, devo riposizionarlo in cima
            if(coor > HEIGHT_FINESTRA-20){
                coor = 30;
                return coor;
            }
        }
        return coor;
    }

    /**
     * Funzione che inizializza il campo di gioco.
     * Istanza e aggiunge al campo:
     *  - Player
     *  - Stricker
     *  - Wanderer
     *  - Bubbler
     *  Richiama la funzione posizionaEnemy() passandole le 3 classi appena create cosichè vengano posizionate sul campo.
     */
    private void inizializzaCampo(){
        //POSIZIONO IL PLAYER
        player.getCircle().setCenterX(newPlayerX);
        player.getCircle().setCenterY(newPlayerY);
        pane.getChildren().add(player.getCircle());

        //ISTANZIO ENEMY
        Striker striker = new Striker();
        Wanderer wanderer = new Wanderer();
        Bubbler bubbler = new Bubbler();

        //AGGIUNGO ENEMY NEI ARRAYLIST
        strikers.add(striker);
        wanderers.add(wanderer);
        bubblers.add(bubbler);

        //POSIZIONO ENEMY
        posizionaEnemy(striker);
        posizionaEnemy(wanderer);
        posizionaEnemy(bubbler);
    }


    /**
     * Funzione che posiziona le caselle Enemy.
     * Genera in modo casuale le coordinate X e Y (controllando che non siano vicine a Player ne vicino al bordo).
     * Salva anche le coordite della casella (per il gameOver).
     * @param enemy
     */
    private void posizionaEnemy(Enemy enemy){
        //CALCOLO COORDINATE IN MODO RANDOM
        int posX, posY;

        //CONTINUO A CICLARE SE LE COORDINATE APPENA CREATE SONO VICINE A QUELLA DEL PLAYER (in un intervallo di 20 pixel) OPPURE SUL BORDO
        do{
            posX = rand.nextInt(501);
            posY = rand.nextInt(501);
        }while((posX < newPlayerX + 30 && posX > newPlayerX/2 - 30) || (posY < newPlayerY/2 + 30 && posY > newPlayerY/2 - 30)
         || (posX > WIDTH_FINESTRA-20) || (posX < 30) || (posY < 30) || (posY > HEIGHT_FINESTRA-20));

        //TODO: EVITARE DI GENERARE ENEMY CHE COLLIDONO

        //POSIZIONO LA CASELLA
        enemy.getCircle().setCenterX(posX);
        enemy.getCircle().setCenterY(posY);

        //SALVO LA POSIZIONE DELLE CASELLA
        enemy.setPosX(posX);
        enemy.setPosY(posY);

        //AGGIUNGO AL PANE L'ENEMY
        pane.getChildren().add(enemy.getCircle());
    }

    private void movePlayer(KeyCode code){
        if(code == KeyCode.RIGHT){
            newPlayerX = newPlayerX + SPOSTAMENTO;

            //CONTROLLO CHE IL PLAYER NON RAGGIUNGA IL BORDO
            newPlayerX = controlloBordi(true, newPlayerX);
            player.getCircle().setCenterX(newPlayerX);
        }else if(code == KeyCode.LEFT){
            newPlayerX = newPlayerX - SPOSTAMENTO;

            //CONTROLLO CHE IL PLAYER NON RAGGIUNGA IL BORDO
            newPlayerX = controlloBordi(true, newPlayerX);
            player.getCircle().setCenterX(newPlayerX);
        }else if(code == KeyCode.UP){
            newPlayerY = newPlayerY - SPOSTAMENTO;

            //CONTROLLO CHE IL PLAYER NON RAGGIUNGA IL BORDO
            newPlayerY = controlloBordi(false, newPlayerY);
            player.getCircle().setCenterY(newPlayerY);
        }else if(code == KeyCode.DOWN){
            newPlayerY = newPlayerY + SPOSTAMENTO;

            //CONTROLLO CHE IL PLAYER NON RAGGIUNGA IL BORDO
            newPlayerY = controlloBordi(false, newPlayerY);
            player.getCircle().setCenterY(newPlayerY);
        }
    }

    private void newEnemy(int iter){
        //DOPO 10 ITERAZIONI AGGIUNGO UN NUOVO ENEMY
        if(iter == ITERAZIONI){
            iter = 0;
            int scelta = rand.nextInt(3);
            switch (scelta){
                //Aggiungo Stricker
                case 0:
                    Striker striker = new Striker();
                    strikers.add(striker);
                    posizionaEnemy(striker);
                    break;

                //Aggiungo Wanderer
                case 1:
                    Wanderer wanderer = new Wanderer();
                    wanderers.add(wanderer);
                    posizionaEnemy(wanderer);
                    break;

                //Aggiungo Bubbler
                case 2:
                    Bubbler bubbler = new Bubbler();
                    bubblers.add(bubbler);
                    posizionaEnemy(bubbler);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
