package sample;

import javafx.scene.layout.GridPane;

public class Griglia extends GridPane {
    private static int N;
    private GridPane gridPane = new GridPane();
    private CellaEmpty[][] celle;

    private int colonna = -1;
    private int riga = -1;
    private int scelta = 0;

    public Griglia(int num){
        N = num;
        celle = new CellaEmpty[N][N];
        inizializza();
    }

    /**
     * Metodo che inizializza la scacchiera e setta gli eventi ON-CLICK, ON-MOUSE-ENTERED e ON-MOUSE-EXITED per ogni casella
     */
    private void inizializza(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                CellaEmpty casella = null;
                if(i % 2 == 0){
                    if(j % 2 != 0){
                        casella = new CellaNera();
                    }else{
                        casella = new CellaBianca();
                    }
                }else{
                    if(j % 2 != 0){
                        casella = new CellaBianca();
                    }else{
                        casella = new CellaNera();
                    }
                }
                gridPane.add(casella.getStackPane(), i, j);
                celle[i][j] = casella;
                int finalJ = j;
                int finalI = i;
                //Evento ON-CLICK, la casella viene settata in base alla scelta dell'utente
                casella.getStackPane().setOnMouseClicked(e -> {
                    this.colonna = finalI;
                    this.riga = finalJ;
                    scopri();
                });
                //Evento ON-MOUSE-ENTERED, vengono mostrate le mosse che si possono fare in base al tipo della pedina
                casella.getStackPane().setOnMouseEntered(e -> {
                    this.colonna = finalI;
                    this.riga = finalJ;
                    mostraMossa();
                });
                //Evento ON-MOUSE-ENTERED, viene ristabilita la scacchiera senza le mosse possibili
                casella.getStackPane().setOnMouseExited(e -> {
                    this.colonna = finalI;
                    this.riga = finalJ;
                    togliMossa();
                });
            }
        }
    }

    /**
     * Metodo che resetta le caselle che sono state modificate per mostrare le possibili mosse
     */
    private void togliMossa(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                celle[i][j].normale();
            }
        }
    }

    /**
     * Metodo che mostra le possibili mosse
     */
    private void mostraMossa(){
        int newScelta = celle[colonna][riga].getTipo();
        if(celle[colonna][riga].getStato()){
            switch (newScelta){
                //PEDONE
                case 1: mossaPedone(); break;
                //TORRE
                case 2: mossaTorre(); break;
                //ALFIERE
                case 3: mossaAlfiere(); break;
            }
        }
    }

    /**
     * Metodo che implementa lo spostamento di un PEDONE
     */
    private void mossaPedone(){
        if(riga != 0)
            celle[colonna][riga-1].evidenzia();
        celle[colonna][riga].evidenzia();
    }

    /**
     * Metodo che implementa lo spostamento di una TORRE
     */
    private void mossaTorre(){
        for(int i = 0; i < N; i++){
            celle[colonna][i].evidenzia();
            celle[i][riga].evidenzia();
        }
    }

    /**
     * Metodo che implementa lo spostamento di un ALFIERE
     */
    private void mossaAlfiere(){
        int row = riga;
        int column = colonna;
        celle[colonna][riga].evidenzia();

        //TUTTE LE POSSIBILI MOSSE NELLA DIAGONALE BASSA A DESTRA
        for (int j = column + 1, i = row + 1; j < N && i < N; j++, i++) {
            celle[j][i].evidenzia();
        }

        //TUTTE LE POSSIBILI MOSSE NELLA DIAGONALE BASSA A SINISTRA
        for (int j = column - 1, i = row + 1; j > -1 && i < N; j--, i++) {
            celle[j][i].evidenzia();
        }

        //TUTTE LE POSSIBILI MOSSE NELLA DIAGONALE ALTA A SINISTRA
        for (int j = column - 1, i = row - 1; j > -1 && i > -1; j--, i--) {
            celle[j][i].evidenzia();
        }

        //TUTTE LE POSSIBILI MOSSE NELLA DIAGONALE ALTA A DESTRA
        for (int j = column + 1, i = row - 1; j < N && i > -1; j++, i--) {
            celle[j][i].evidenzia();
        }
    }

    /**
     * Metodo che setta la casella premuta in base alla scelta dell'utente
     */
    private void scopri(){
        celle[colonna][riga].scopri(scelta);
    }

    /**
     * Metodo che setta la scelta in base al bottone premuto dall'utente
     * @param scelta:
     *              - 1: PEDONE
     *              - 2: TORRE
     *              - 3: ALFIERE
     */
    public void posizionaElemento(int scelta){
        this.scelta = scelta;
    }

    /**
     * Metodo che resetta il campo, togliendo tutte le pedine posizionate
     */
    public void reset(){
        scelta = 0;
        colonna = -1;
        riga = -1;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                celle[i][j].reset();
            }
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
