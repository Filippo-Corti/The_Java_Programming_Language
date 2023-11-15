import java.util.ArrayList;
import java.util.Scanner;

public class Segmento3D implements Segmento {
    // OVERVIEW: modella un segmento 3D, ottenuto estendendo Segmento2D

    private final Punto3D a, b;

    // constructor
    public Segmento3D(Punto3D a, Punto3D b) throws IllegalArgumentException, NullPointerException {
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
    public Punto3D getA() {
        return a;
    }

    public Punto3D getB() {
        return b;
    }

    public double length() {
        // EFFECTS: ritorna la lunghezza di this
        return Math.sqrt(
                Math.pow(a.p.x - b.p.x, 2) +
                        Math.pow(a.p.y - b.p.y, 2) +
                        Math.pow(a.z - b.z, 2));
    }

    @Override
    public String toString() {
        return "Segmento - a: " + a.toString() + ", b: " + b.toString();
    }

    public static void main(String[] args) {
        double maxLen = Double.parseDouble(args[0]);

        ArrayList<Segmento3D> segmenti = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        System.out.println("Inserisci i segmenti nelformato ax ay az bx by bz (termina con CTRL+D)");

        while (s.hasNext()) {
            segmenti.add(
                    new Segmento3D(
                            new Punto3D(s.nextDouble(), s.nextDouble(), s.nextDouble()),
                            new Punto3D(s.nextDouble(), s.nextDouble(), s.nextDouble())));
        }

        System.out.println("Segmenti di lunghezza superiore a " + maxLen);
        for (Segmento3D segmento : segmenti) {
            if (segmento.length() > maxLen)
                System.out.println(segmento + " - Lunghezza: " + segmento.length());
        }
    }
}
