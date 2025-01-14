package sample;

import javafx.scene.layout.GridPane;

public class Griglia extends GridPane {
    private static int N = 10;
    private GridPane gridPane = new GridPane();
    private CellaEmpty[][] celle;

    public Griglia(){
        celle = new CellaEmpty[N][N];
        inizializza();
    }

    private void inizializza(){
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                CellaEmpty casella = new CellaPrato();
                gridPane.add(casella.getStackPane(), i, j);
                celle[i][j] = casella;
            }
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
