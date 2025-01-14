/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjmazzoramino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author aymane.chabbaki
 */
public class Mazzo extends LinkedList<Carta> {

    private LinkedList<Carta> mazzo;

    public Mazzo() {
        this.mazzo = new LinkedList<Carta>();
        inizializza(Carta.C);
        inizializza(Carta.Q);
        inizializza(Carta.F);
        inizializza(Carta.P);
    }

    //Metodo che inizializza il mazzo per seme
    private void inizializza(int seme) {
        for (int i = 0; i < 13; i++) {
            mazzo.add(new Carta(i + 1, seme));
            mazzo.add(new Carta(i + 1, seme));
        }
    }

    public void mescola() {
        Collections.shuffle(mazzo);
    }

    /*public LinkedList<Carta> estraiCarte(int num) {
        LinkedList<Carta> toRtn = new LinkedList();
        for (int i = 0; i < num; i++) {
            toRtn.add(mazzo.get(i));
        }
        return toRtn;
    }*/

    @Override
    public String toString() {
        String toRtn = "{ ";
        for (Carta c : mazzo) {
            toRtn += c.toString() + "\n";
            //TODO: a capo per seme
        }
        return toRtn;
    }
    
    public Carta coppia(int dim) {
        int i = 0;
        int j = 0;
        boolean trovato = false;
        while (i < dim && !trovato) {
            j = i + 1;
            while (j < dim && !trovato) {
                if (mazzo.get(i).equals(mazzo.get(j))) {
                    trovato = true;
                } else {
                    j++;
                }
            }
            i++;
        }

        Carta toRtn = null;
        
        if (trovato) {
            toRtn = new Carta(mazzo.get(j));
        }
        
        return toRtn;
    }
    
    public boolean doppiaVittoria(Carta vincente){
        Random r = new Random();
        int indexRand = r.nextInt(mazzo.size());
        if(vincente.getNumero() == mazzo.get(indexRand).getNumero()){
            return true;
        }
        
        return false;
    }
}
