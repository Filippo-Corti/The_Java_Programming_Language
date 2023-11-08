import java.util.Iterator;

public class Fibonacci implements Iterator<Integer> {
    // OVERVIEW: iteratore Standalone per iterare sulla sequenza di Fibonacci

    // attributes
    int n1, n2;

    //constructors
    public Fibonacci() {
    }

    //methods
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        // MODIFIES this
        // EFFECTS: ritorna il numero successivo nella sequenza di Fibonacci,
        // aggiornando n1 e n2

        if (n1 == 0 && n2 == 0) { // Gestisco separatamente il primo caso (Fibonacci parte da 0 non da 1)
            n1 = 1;
            n2 = 0;
            return 0;
        }

        int temp = n2;
        n2 += n1;
        n1 = temp;
        return n2;
    }


    public boolean repOk() {
        if (n1 >= n2)
            return false;
        return true;
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            System.out.println(fibonacci.next());
        }
    }

}
