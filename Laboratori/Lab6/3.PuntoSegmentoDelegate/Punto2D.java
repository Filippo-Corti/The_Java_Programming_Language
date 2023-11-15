public class Punto2D implements Punto {
    // OVERVIEW: modella un punto in 2D. Immutabile

    // attributes
    public final double x, y;
    // Rappresentazione autoprotetta => non servono getter o repOk

    // constructors
    public Punto2D(double x, double y) {
        // MODIFIES: this
        // EFFECTS: inizializza this con x e y
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

    @Override
    public String toString() {
        return "Punto - x: " + x + ", y: " + y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Punto2D))
            return false;
        Punto2D other = (Punto2D) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
    }

}
