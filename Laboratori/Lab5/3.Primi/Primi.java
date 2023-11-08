import java.util.Iterator;

public class Primi implements Iterator<Integer> {
    // OVERVIEW: iteratore Standalone per iterare sui numeri primi

    // attributes
    private int current;

    // constructor
    public Primi() {
        // MODIFIES: this
        // EFFECTS: istanzia un nuovo iteratore di numeri primi
        this.current = 2;

        assert repOk();
    }

    // methods

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        // MODIFIES: this
        // EFFECTS: cerca il primo numero primo successivo a current e lo ritorna
        while (!isPrimo(this.current)) {
            this.current++;
        }

        assert repOk();

        return this.current++;
    }

    public boolean repOk() {
        if (current < 2)
            return false;

        return true;
    }

    // functions (static methods)

    public static boolean isPrimo(int n) {
        // EFFECTS: ritorna true n è primo.
        // Nota: il primo numero primo è 2
        if (n < 2)
            return false;

        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Primi primi = new Primi();

        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            System.out.println(primi.next());
        }
    }
}
