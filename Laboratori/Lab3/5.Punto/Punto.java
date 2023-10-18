import java.util.ArrayList;
import java.util.Scanner;

/* L'esempio del prof è sbagliato. I punti più distanti sono palesemente (1, 6) e (7 -8) */

public class Punto {
    // OVERVIEW: Punto è una classe mutabile che rappresenta un punto del piano
    // cartesiano

    double x, y;

    // constructors
    public Punto() {
    }

    public Punto(double x) {
        this.x = x;
    }

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // methods
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setXY(double x, double y) {
        setX(x);
        setY(y);
    }

    @Override
    public String toString() {
        return "{Punto: " + x + ", " + y + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Punto other = (Punto) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
    }

    public double distanza(Punto p) throws IllegalArgumentException {
        // EFFECTS: ritorna la distanza tra this e p
        // lancia IllegalArgumentException se p == null
        if (p == null)
            throw new IllegalArgumentException("p non può essere nullo");

        return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Inserire i punti (<x> <y>)");
        System.out.println("Terminare la lettura con CTRL+D");

        ArrayList<Punto> punti = new ArrayList<>();

        while (s.hasNext()) {
            String in = s.nextLine();

            String[] splitIn = in.split(" ");
            try {
                double x = Double.parseDouble(splitIn[0]);
                double y = Double.parseDouble(splitIn[1]);
                punti.add(new Punto(x, y));
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        Punto p1 = null, p2 = null;
        double maxDistanza = 0;
        for (int i = 0; i < punti.size() - 1; i++) {
            for (int j = i + 1; j < punti.size(); j++) {
                try {
                    double distanza = punti.get(i).distanza(punti.get(j));
                    if (distanza > maxDistanza) {
                        maxDistanza = distanza;
                        p1 = punti.get(i);
                        p2 = punti.get(j);
                    }
                } catch (NullPointerException e) {
                    // In teoria non dovrebbe succedere
                    System.exit(1);
                }
            }
        }

        System.out.println("\nI punti piu' distanti sono:");
        System.out.println(p1);
        System.out.println(p2);
    }

}
