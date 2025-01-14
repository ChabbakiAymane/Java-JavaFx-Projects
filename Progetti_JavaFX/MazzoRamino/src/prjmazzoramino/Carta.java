/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjmazzoramino;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 *
 * @author aymane.chabbaki
 */
public class Carta {

    public final static int C = 0, Q = 1, F = 2, P = 3;
    private int numero;
    private int seme;
    private Image img;

    //Costruttore Default
    public Carta() throws IOException {
        this.numero = 0;
        this.seme = -1;
        img = ImageIO.read(new File("C:/Users/Ayman/Desktop/Università/Anno 1/Semestre 2/Linguaggi di Programmazione/Modulo 1/Laboratorio/ProgettiFX/prjMazzoRamino/ImmaginiCarte/joker.jpg"));
    }

    //Costruttore Specifico
    public Carta(int numero, int seme) {
        this.numero = numero;
        this.seme = seme;
    }
    
    public Carta(Carta c) {
        this.numero = c.numero;
        this.seme = c.seme;
        this.img = c.img;
    }

    //Metodi GET e SET
    public int getNumero() {
        return numero;
    }

    public int getSeme() {
        return seme;
    }

    public Image getImg() {
        return img;
    }
    
    @Override
    public String toString() {
        return "{" + "numero=" + numero + ", seme=" + seme + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(!(obj instanceof Carta)) return false;
        
        Carta c2 = (Carta) obj;
        return c2.numero == this.numero && c2.seme == this.seme;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, seme);
        /*int hash = 11;
        hash = 61 * hash + this.numero;
        hash = 61 * hash + this.seme;
        return hash;*/
    }
}
