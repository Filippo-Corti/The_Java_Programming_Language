import java.util.ArrayList;
import java.util.List;

public class Abbondanti {
    // OVERVIEW: static class dealing with abundant numbers

    public static List<Integer> elencoDivisoriPropri(int n) {
        // REQUIRES: n > 0
        // EFFECTS: returns the proper divisors of n, in the form of a list
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0)
                list.add(i);
        }

        return list;
    }

    public static int sommaDivisoriPropri(int n) {
        // REQUIRES: n > 0
        // EFFECTS: returns the sum of the proper divisors of n.
        // Zero if n has no proper divisors
        List<Integer> list = elencoDivisoriPropri(n);

        int sum = 0;
        for (int i : list) {
            sum += i;
        }

        return sum;
    }

    public static boolean abbondante(int n) {
        // REQUIRES: n > 0
        // EFFECTS: returns true if n is an abundant numbers. False otherwise
        return (sommaDivisoriPropri(n) > n);
    }

    public static List<Integer> numeriAbbondanti(int limite) {
        //REQUIRES: limite > 0
        //EFFECTS: returns all abundant numbers <= limite, in the form of a list
        List<Integer> abundantNumbers = new ArrayList<>();
        for (int i = 12; i < limite; i++) { //12 is the first abundant number
            if(abbondante(i))
                abundantNumbers.add(i);
        }
        return abundantNumbers;
    }

    public static void stampaNumeriAbbondanti(List<Integer> numeri) {
        //REQUIRES: numeri not null
        //EFFECTS: prints the list numeri, separating items with a space. 
        for (int x : numeri) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int limite = Integer.parseInt(args[0]);
        if (limite <= 0) {
            System.out.println("La soglia inserita non è positiva");
        } else {
            System.out.print("Numeri abbondanti: ");
            stampaNumeriAbbondanti(numeriAbbondanti(limite));
        }
    }

}
