package sample;
import javafx.scene.control.Alert;

import java.util.*;

public class Persona extends Record implements Comparable<Persona>{
    private static ArrayList<Persona> persone = new ArrayList<>();
    private String nome, cognome;
    private int annoNascita;

    public Persona() {
        super();
    }

    public Persona(String nome, String cognome, int annoNascita) {
        super(nome, cognome, annoNascita);
        this.nome = nome;
        this.cognome = cognome;
        this.annoNascita = annoNascita;
    }

    public int getAnnoNascita() {
        return annoNascita;
    }

    public String getCognome() {
        return cognome;
    }

    public boolean newPerson(String nome, String cognome, int annoNascita){
        Persona p = new Persona(nome, cognome, annoNascita);
        boolean tortn = false;
        for (Persona i : persone) {
            if(i.equals(p)) tortn = true;
        }
        if(!tortn) persone.add(p);
        return tortn;
    }

    public String toString(){
        String tmp = "";
        Iterator itr = persone.iterator();
        while(itr.hasNext()) {
            Persona p = (Persona) itr.next();
            tmp = tmp + p.nome + " " + p.cognome + " " + p.annoNascita + "\n";
        }
        return tmp;
    }

    public void mescola(){
        Collections.shuffle(persone);
    }

    @Override
    public int compareTo(Persona p) {
        return (this.getAnnoNascita() - p.getAnnoNascita());
    }

    public void ordinaCognome(){
        CognomeComparator personaComparator = new CognomeComparator();
        Collections.sort(persone, personaComparator);
    }

    public void ordinaAnno(){
        AnnoComparator personaComparator = new AnnoComparator();
        Collections.sort(persone, personaComparator);
    }

    public int contaPersone(){
        return persone.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Persona persona = (Persona) o;
        return annoNascita == persona.annoNascita &&
                Objects.equals(nome, persona.nome) &&
                Objects.equals(cognome, persona.cognome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nome, cognome, annoNascita);
    }

    public static class AnnoComparator implements Comparator<Persona> {
        @Override
        public int compare(Persona p, Persona p1) {
            return (p.getAnnoNascita() - p1.getAnnoNascita());
        }
    }

    public static class CognomeComparator implements Comparator<Persona> {
        @Override
        public int compare(Persona p, Persona p1) {
            return p.getCognome().compareTo(p1.getCognome());
        }
    }

    public static void main(String[] args){
        Persona p = new Persona();
        p.newPerson("Aymane", "Chabbaki", 2);
        p.newPerson("Shkelzen", "Gimolli", 5);
        p.newPerson("Edoardo", "Dodoo", 1);
        p.newPerson("Matteo", "Leonelli", 9);
        System.out.println(p.toString());
        System.out.println("Numero di record: " + p.contaPersone());
        System.out.println("-------MESCOLA-------");
        p.mescola();
        System.out.println(p.toString());
        System.out.println("-------COGNOME-------");
        p.ordinaCognome();
        System.out.println(p.toString());
        System.out.println("-------ANNO-------");
        p.ordinaAnno();
        System.out.println(p.toString());
    }
}

