import java.util.ArrayList;
import java.util.Scanner;

public class Segmento2D {
    // OVERVIEW: modella un segmento 2D. Immutabile

    // attributes
    private final Punto2D a, b; // Qua public non andrebbe bene (sono oggetti)

    // constructors
    public Segmento2D(Punto2D a, Punto2D b) throws IllegalArgumentException, NullPointerException {
        // MODIFIES: this
        // EFFECTS: inizilizza this con p1 e p2
        // lancia NullPointerException se p1 o p2 sono nulli
        // lancia IllegalArgumentException se p1 == p2
        if (a == null)
            throw new NullPointerException("a null");

        if (b == null)
            throw new NullPointerException("b null");

        if (a.equals(b))
            throw new IllegalArgumentException("a equals b");

        this.a = a;
        this.b = b;
    }

    // methods
    public Punto2D getA() {
        return a;
    }

    public Punto2D getB() {
        return b;
    }

    public double length() {
        // EFFECTS: ritorna la lunghezza di this
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    @Override
    public String toString() {
        return "Segmento - a: " + a.toString() + ", b: " + b.toString();
    }

    // Essendo immutabile, la repOk non è fondamentale
    // (a patto che i controlli nel costruttore siano tutti)


}
