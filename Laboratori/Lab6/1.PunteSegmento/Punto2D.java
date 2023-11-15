public class Punto2D {
    //OVERVIEW: modella un punto in 2D. Immutabile

    //attributes
    public final double x, y;  
    //Rappresentazione autoprotetta => non servono getter o repOk


    //constructors
    public Punto2D(double x, double y) {
        //MODIFIES: this
        //EFFECTS: inizializza this con x e y
        this.x = x;
        this.y = y;
    }

    //methods
    @Override
    public String toString() {
        return "(Punto2D - x: " + x + ", y: " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!this.getClass().equals(obj.getClass()))
            return false;
        Punto2D other = (Punto2D) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
    }

    
    
}
