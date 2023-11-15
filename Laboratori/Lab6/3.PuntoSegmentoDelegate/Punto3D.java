public class Punto3D implements Punto {
    // OVERVIEW: modella un punto 3D

    // attributes
    final Punto2D p;
    final double z;

    // constructors
    public Punto3D(double x, double y, double z) {
        // MODIFIES: this
        // EFFECTS: inizializza this con super(x, y) + z
        this.p = new Punto2D(x, y);
        this.z = z;
    }

    // methods

    public double getX() {
        return p.getX(); // Getter per delega
    }

    public double getY() {
        return p.getY();
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return p.toString() + ", z: " + z;
    }

    @Override
    public boolean equals(Object obj) {
        // Non essendoci gerarchia l'equals non ha problemi
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Punto3D other = (Punto3D) obj;
        if (p == null) {
            if (other.p != null)
                return false;
        } else if (!p.equals(other.p))
            return false;
        if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
            return false;
        return true;
    }

}
