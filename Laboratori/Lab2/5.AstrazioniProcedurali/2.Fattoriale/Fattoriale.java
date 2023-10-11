import java.util.ArrayList;
import java.util.List;

public class Fattoriale {
    //OVERVIEW: static class that contains methods to deal with factorials
    
    public static void main(String[] args) {
        stampaFattoriali(listaFattoriali(Integer.parseInt(args[0])));
    }

    public static int fattoriale(int n) {
        //EFFECTS: Returns n! if n >= 1. Returns -1 otherwise.

        if (n < 1)
            return -1;

        int sum = 1;
        for(int i = 2; i <= n; i++) 
            sum *= i;

        return sum;
    }

    public static List<Integer> listaFattoriali(int n) {
        //EFFECTS: Returns a List containing factorials from 1! to n!
        //if n is < 1 the returned list is null
        if (n < 1)
            return null;

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(fattoriale(i));
        }

        return list;

    }

    public static void stampaFattoriali(List<Integer> lista) {
        //EFFECTS: prints a list of factorials in the format n: n!
        //It prints nothing if list is empty or null
        if (lista == null)
            return;

        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i+1) + ": " + lista.get(i));
        }

    }

}
