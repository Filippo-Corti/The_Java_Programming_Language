public class Punto3D extends Punto2D {
    // OVERVIEW: modella un punto 3D

    // attributes
    public final double z;

    // constructors
    public Punto3D(double x, double y, double z) {
        // MODIFIES: this
        // EFFECTS: inizializza this con super(x, y) + z
        super(x, y);
        this.z = z;
    }

    // methods
    @Override
    public String toString() {
        return super.toString() + ", z: " + z;
    }

    @Override
    public boolean equals(Object obj) {
        //Questa equals ha il problema di non rispettare il principio di Liskov per la transitività dell'equals
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Punto3D) {
            Punto3D other = (Punto3D) obj;
            if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
                return false;
            if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
                return false;
            if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
                return false;
        }
        return super.equals(obj); 
    }



}
