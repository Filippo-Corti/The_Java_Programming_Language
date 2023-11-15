import java.util.ArrayList;
import java.util.Scanner;

public class Segmento3D extends Segmento2D {
    //OVERVIEW: modella un segmento 3D, ottenuto estendendo Segmento2D

    //constructor
    public Segmento3D(Punto3D a, Punto3D b) throws IllegalArgumentException, NullPointerException {
        super(a, b);
    } 

    //methods
    public Punto3D getA() {
        return (Punto3D) super.getA();
    }

    public Punto3D getB() {
        return (Punto3D) super.getB();
    }

    public double length() {
        // EFFECTS: ritorna la lunghezza di this
        Punto3D a = getA(), b = getB();
        return Math.sqrt(
            Math.pow(a.x - b.x, 2) + 
            Math.pow(a.y - b.y, 2) + 
            Math.pow(a.z - b.z, 2));
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
                    new Punto3D(s.nextDouble(), s.nextDouble(), s.nextDouble())
                ));
        }

        System.out.println("Segmenti di lunghezza superiore a " + maxLen);
        for (Segmento2D segmento : segmenti) {
            if (segmento.length() > maxLen)
                System.out.println(segmento + " - Lunghezza: " + segmento.length());
        }
    }
}
