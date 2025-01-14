package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Enemy extends Circle {
    private Circle circle;
    private int raggio = 20;
    private int posX, posY;

    private int direzione;
    private Random rand = new Random();

    public Enemy(Color c) {
        circle = new Circle(raggio);;
        circle.setFill(c);
        circle.setStroke(Color.BLACK);
        this.posY = 0;
        this.posX = 0;
        this.direzione = rand.nextInt(8);
    }

    public Circle getCircle(){
        return this.circle;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int poxX) {
        this.posX = poxX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getDirezione() {
        return this.direzione;
    }

    public int getRaggio(){
        return this.raggio;
    }

    public void setRaggio(int raggio){
        this.raggio = raggio;
    }

    public void setDirezione(int direzione) {
        this.direzione = direzione;
    }
}
