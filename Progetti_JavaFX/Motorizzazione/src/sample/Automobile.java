package sample;
import java.util.*;

public class Automobile extends Record implements Comparable<Automobile>{
    private static ArrayList<Automobile> auto = new ArrayList<>();
    private String marca, modello;
    private int prezzo;

    public Automobile() {
        super();
    }

    public Automobile(String marca, String modello, int prezzo) {
        super(marca, modello, prezzo);
        this.marca = marca;
        this.modello = modello;
        this.prezzo = prezzo;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public String getModello() {
        return modello;
    }

    public boolean newAuto(String marca, String modello, int prezzo){
        Automobile a = new Automobile(marca, modello, prezzo);
        boolean tortn = false;
        for (Automobile i : auto) {
            if(i.equals(a)) tortn = true;
        }
        if(!tortn) auto.add(a);
        return tortn;

    }
    public String toString(){
        String tmp = "";
        Iterator itr = auto.iterator();
        while(itr.hasNext()) {
            Automobile p = (Automobile) itr.next();
            tmp = tmp + p.marca + " " + p.modello + " " + p.prezzo + "\n";
        }
        return tmp;
    }

    public void mescola(){
        Collections.shuffle(auto);
    }

    @Override
    public int compareTo(Automobile p) {
        return (this.getPrezzo() - p.getPrezzo());
    }

    public void ordinaModello(){
        modelloComparator autoComparator = new modelloComparator();
        Collections.sort(auto, autoComparator);
    }

    public void ordinaPrezzo(){
        prezzoComparator autoComparator = new prezzoComparator();
        Collections.sort(auto, autoComparator);
    }

    public int contaAuto(){
        return auto.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Automobile that = (Automobile) o;
        return prezzo == that.prezzo &&
                Objects.equals(marca, that.marca) &&
                Objects.equals(modello, that.modello);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), marca, modello, prezzo);
    }

    public static class prezzoComparator implements Comparator<Automobile> {
        @Override
        public int compare(Automobile p, Automobile p1) {
            return (p.getPrezzo() - p1.getPrezzo());
        }
    }

    public static class modelloComparator implements Comparator<Automobile> {
        @Override
        public int compare(Automobile p, Automobile p1) {
            return p.getModello().compareTo(p1.getModello());
        }
    }

    /*public static void main(String[] args){
        Automobile a = new Automobile();
        a.newAuto("Fiat", "500", 20);
        a.newAuto("Audi", "QSHLE", 5);
        a.newAuto("BMW", "M4", 1000);
        a.newAuto("Lamborghini", "Urus", 400);
        System.out.println(a.toString());
        System.out.println("Numero di record: " + a.contaAuto());
        System.out.println("-------MESCOLA-------");
        a.mescola();
        System.out.println(a.toString());
        System.out.println("-------MODELLO-------");
        a.ordinaModello();
        System.out.println(a.toString());
        System.out.println("-------PREZZO-------");
        a.ordinaPrezzo();
        System.out.println(a.toString());
    }*/
}


