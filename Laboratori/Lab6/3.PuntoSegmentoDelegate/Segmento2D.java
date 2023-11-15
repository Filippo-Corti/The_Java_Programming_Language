import java.util.ArrayList;
import java.util.Scanner;

public class Segmento2D implements Segmento {
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

    public Segmento2D(Punto3D a2, Punto3D b2) {
    }

    // methods
    public Punto3D getA() {
        return a;
    }

    public Punto3D getB() {
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

    public static void main(String[] args) {
        double maxLen = Double.parseDouble(args[0]);

        ArrayList<Segmento2D> segmenti = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        System.out.println("Inserisci i segmenti nelformato ax ay bx by (termina con CTRL+D)");

        while (s.hasNext()) {
            segmenti.add(
                    new Segmento2D(
                            new Punto2D(s.nextDouble(), s.nextDouble()),
                            new Punto2D(s.nextDouble(), s.nextDouble())));
        }

        System.out.println("Segmenti di lunghezza superiore a " + maxLen);
        for (Segmento2D segmento : segmenti) {
            if (segmento.length() > maxLen)
                System.out.println(segmento + " - Lunghezza: " + segmento.length());
        }
    }

}
