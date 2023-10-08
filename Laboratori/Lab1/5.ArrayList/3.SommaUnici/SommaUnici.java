import java.util.ArrayList;

public class SommaUnici {

    public static void main(String[] args) {
        ArrayList<Integer> numeri = leggiNumeri(args);

        int sommaUnici = 0; 
        for (Integer n : numeri) {
            if (occorrenze(numeri, n) == 1)
                sommaUnici += n;
        }

        System.out.println(sommaUnici);
    }

    // Dovrò pur passargli gli argomenti a questa povera funzione
    public static ArrayList<Integer> leggiNumeri(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        for (String string : args) {
            try {
                a.add(Integer.parseInt(string));
            } catch (Exception e) {
                // Lasciamolo stare per il momento
            }
        }
        return a;
    }

    public static int occorrenze(ArrayList<Integer> numeri, int n) {
        int c = 0;
        for (Integer i : numeri) {
            if (i == n) {
                c++;
            }
        }
        return c;
    }

}
