package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class SquareTile extends Tile {
    private Rectangle rectangle;
    private final static int lato = 40;

    public SquareTile(){
        super();
        this.rectangle = new Rectangle(lato, lato, randomColor());
        this.rectangle.setStroke(Color.BLACK);
        super.getStackPane().getChildren().addAll(rectangle, super.getTxt());
    }

    public Color randomColor(){
        int randColor = rand.nextInt(10);
        Color c = null;
        switch (randColor){
            case 0: c = Color.LIGHTBLUE; break;
            case 1: c = Color.RED; break;
            case 2: c = Color.YELLOW; break;
            case 3: c = Color.GREEN; break;
            case 4: c = Color.ORANGE; break;
            case 5: c = Color.VIOLET; break;
            case 6: c = Color.SALMON; break;
            case 7: c = Color.WHITE; break;
            case 8: c = Color.LIMEGREEN; break;
            case 9: c = Color.BROWN; break;
        }
        return c;
    }

    public int getNum() { return super.getNum(); }

    public void setNum(int num){ super.setNum(num); }

    public StackPane getStackPane() { return super.getStackPane(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SquareTile that = (SquareTile) o;
        return super.getNum() == that.getNum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(rectangle, super.getNum());
    }
}
