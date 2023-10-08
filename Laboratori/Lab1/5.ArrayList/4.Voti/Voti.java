import java.util.ArrayList;
import java.util.Scanner;

public class Voti {

    
    public static void main(String[] args) {
        ArrayList<Integer> ints = leggiNumeri();
        filtraVoti(ints);
    }

    public static ArrayList<Integer> leggiNumeri() {
        System.out.println("Inserisci i voti:");
        ArrayList<Integer> a = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            a.add(s.nextInt());
        }
        return a;
    }

    public static void filtraVoti(ArrayList<Integer> l) {
        ArrayList<Integer> sufficienti = new ArrayList<>();
        ArrayList<Integer> insufficienti = new ArrayList<>();
        for (int i : l) {
            if (i < 60) 
                insufficienti.add(i);
            else
                sufficienti.add(i);
        }
        System.out.println("Voti sufficienti: " + sufficienti);
        System.out.println("Voti insufficienti: " + insufficienti);
    }

}
