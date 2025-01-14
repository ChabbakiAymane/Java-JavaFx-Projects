package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

public abstract class Tile extends Shape implements Comparable<Tile> {
    private Text txt = new Text();
    private StackPane stackPane = new StackPane();
    private int num;

    Random rand = new Random();

    public Tile(){
        this.num = rand.nextInt(10);
        this.txt.setText("" + num);
    }

    public StackPane getStackPane() {
        return this.stackPane;
    }

    public Text getTxt() {
        return this.txt;
    }

    public void setNum(int num){
        this.num = num;
        this.txt.setText("" + num);
    }

    public int getNum() {
        return num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return this.num == tile.getNum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }

    @Override
    public int compareTo(Tile tile) {
        return (this.getNum() - tile.getNum());
    }
}
