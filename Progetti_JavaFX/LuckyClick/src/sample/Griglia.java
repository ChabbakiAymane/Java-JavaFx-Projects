package sample;

import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class Griglia extends GridPane {
    //COSTANTI STATICHE
    private static final int N = 10;
    private static final int N_BASE = 75;
    private static final int N_BOMBA = 5;
    private static final int N_MOLTIPLICATORE = 10;
    private static final int N_DIVISORE = 10;

    //VARIABILI PER PUNTEGGIO E TENTATIVI
    private int punteggio = 0;
    private int tentativi = 10;

    private GridPane gridPane = new GridPane(); //campo di gioco
    private CellaEmpty[][] celle = new CellaEmpty[N][N]; //contiene tutte le caselle
    private ArrayList<Pair> coordinate = new ArrayList<Pair>(); //contiene coordinate

    private Random rand = new Random(System.currentTimeMillis());

    public Griglia(){
        coordinateCampo(); //inizializzo l'arraylist di coordinate
        inizializza(); //inizalizzo il campo di gioco
    }

    /**
     * Funzione che genera tutte le possibili coordinate del campo di gioco (NxN)
     */
    private void coordinateCampo(){
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Pair tmp = new Pair(i, j);
                coordinate.add(tmp);
            }
        }
    }

    /**
     * Funzione che restituisce in modo random una coppia (colonna, riga) presa dall'arraylist contenente
     * tutte le possibili coordinate
     * @return: coppia (colonna, riga)
     */
    private Pair getPair(){
        int size, randIndice;
        //Salvo la dimensione attuale dell'arraylist di coordinate
        size = coordinate.size();
        //Genero un indice casuale
        randIndice = rand.nextInt(size);
        //Estraggo una coordianta in modo casuale
        Pair p = coordinate.get(randIndice);
        //Rimuovo la coordinata appena estratta
        coordinate.remove(randIndice);
        //Restituisco la coordinata
        return p;
    }

    /**
     * Funzione che inizializza il campo di gioco.
     * Aggiunge al campo:
     *      - 75 caselle BASE
     *      - 10 caselle MOLTIPLICATORE
     *      - 10 caselle DIVISIORE
     *      - 5 caselle BOMBA
     * Per ogni casella, setta anche l'evento per scoprirla quando premuta in base al suo tipo.
     */
    private void inizializza(){
        int stop;

        //Inizializzo e posiziono in modo casuale le caselle di tipo BOMBA
        stop = N_BOMBA;
        while(stop > 0){
            //Ottengo in modo casuale una coordinata del campo di gioco
            Pair p = getPair();

            //Salvo colonna e riga
            int colonna = (int) p.getKey();
            int riga = (int) p.getValue();

            //Istanzio una casella di tipo BOMBA
            CellaBomba bomba = new CellaBomba();
            bomba.getStackPane().setOnMouseClicked(e ->{
                bomba.scopri();
                punteggio = punteggio + effettoBomba(colonna, riga);
                tentativi = tentativi - 1;
            });

            //Aggiungo al GridPane e alla matrice cella la caselle BOMBA che ho appena istanziato
            gridPane.add(bomba.getStackPane(), colonna, riga);
            celle[colonna][riga] = bomba;
            //Decremento il numero di BOMBE
            stop--;
        }

        //Inizializzo e posiziono in modo casuale le caselle di tipo MOLTIPLICATORE
        stop = N_MOLTIPLICATORE;
        while(stop > 0){
            //Ottengo in modo casuale una coordinata del campo di gioco
            Pair p = getPair();

            //Salvo colonna e riga
            int colonna = (int) p.getKey();
            int riga = (int) p.getValue();

            //Istanzio una casella di tipo MOLTIPLICATORE
            CellaMoltiplicatore moltiplicatore = new CellaMoltiplicatore();
            moltiplicatore.getStackPane().setOnMouseClicked(e -> {
                if(moltiplicatore.scopri() != -1){
                    punteggio = punteggio * 2;
                    tentativi = tentativi - 1;
                }
            });

            //Aggiungo al GridPane e alla matrice cella la caselle MOLTIPLICATORE che ho appena istanziato
            gridPane.add(moltiplicatore.getStackPane(), colonna, riga);
            celle[colonna][riga] = moltiplicatore;
            //Decremento il numero di bombe
            stop--;
        }

        //Inizializzo e posiziono in modo casuale le caselle di tipo DIVISORE
        stop = N_DIVISORE;
        while(stop > 0){
            //Ottengo in modo casuale una coordinata del campo di gioco
            Pair p = getPair();

            //Salvo colonna e riga
            int colonna = (int) p.getKey();
            int riga = (int) p.getValue();

            //Istanzio una casella di tipo DIVISORE
            CellaDivisore divisore = new CellaDivisore();
            divisore.getStackPane().setOnMouseClicked(e -> {
                if(divisore.scopri() != -1){
                    punteggio = punteggio / 2;
                    tentativi = tentativi - 1;
                }
            });

            //Aggiungo al GridPane e alla matrice cella la caselle  DIVISORE che ho appena istanziato
            gridPane.add(divisore.getStackPane(), colonna, riga);
            celle[colonna][riga] = divisore;
            //Decremento il numero di bombe
            stop--;
        }

        //Inizializzo e posiziono in modo casuale le caselle di tipo BASE
        stop = N_BASE;
        while(stop > 0){
            //Ottengo in modo casuale una coordinata del campo di gioco
            Pair p = getPair();

            //Salvo colonna e riga
            int colonna = (int) p.getKey();
            int riga = (int) p.getValue();

            //Istanzio una casella di tipo BASE
            CellaBase base = new CellaBase();
            base.getStackPane().setOnMouseClicked(e -> {
                if(base.scopri() != -1){
                    punteggio = punteggio + base.getNumero();
                    tentativi = tentativi - 1;
                }
            });

            //Aggiungo al GridPane e alla matrice cella la caselle  BASE che ho appena istanziato
            gridPane.add(base.getStackPane(), colonna, riga);
            celle[colonna][riga] = base;
            //Decremento il numero di bombe
            stop--;
        }
    }

    /**
     * Funzione ricorsiva che implementa l'effetto delle caselle bomba
     * @param colonna: indice delle colonna dove si trova la bomba
     * @param riga: indice della riga dove si trova la bomba
     * @return: somma dei punti delle caselle
     */
    private int effettoBomba(int colonna, int riga){
        int punti = 0;

        //Scopro colonna
        for(int i = 0; i < N; i++){
            if(celle[colonna][i].scopri() != -1){
                punti = punti + celle[colonna][i].getNumero();
                //Se la casella ha num = 0, è una bomba e richiamo la funzione passando la posizione della bomba
                if(celle[colonna][i].getNumero() == 0){
                    punti = punti + effettoBomba(colonna, i);
                }
            }
        }

        //Scopro riga
        for(int j = 0; j < N; j++){
            if(celle[j][riga].scopri() != -1){
                punti = punti + celle[j][riga].getNumero();
                //Se la casella ha num = 0, è una bomba e richiamo la funzione passando la posizione della bomba
                if(celle[j][riga].getNumero() == 0){
                    punti = punti + effettoBomba(j, riga);
                }
            }
        }
        return punti;
    }

    /**
     * Funzione che elimina l'evento click sulle caselle
     */
    public void bloccaGioco(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                celle[i][j].getStackPane().setOnMouseClicked(null);
            }
        }
    }

    /**
     * Funzione che resetta il campo di gioco, il punteggio e i tentativi
     */
    public void reset(){
        gridPane.getChildren().clear();
        coordinateCampo();
        inizializza();
        punteggio = 0;
        tentativi = 10;
    }

    public int getPunteggio(){
        return this.punteggio;
    }

    public int getTentativi(){
        return this.tentativi;
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
