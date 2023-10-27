public class Punto implements Cloneable{
    // OVERVIEW: astrazione immutabile che modella un oggetto Punto avente
    // coordinate decimali x e y.

    // attributes
    private double x, y;

    // constructors

    public Punto() {
        // MODIFIES: this
        // EFFECTS: inizializza un oggetto Punto di coordinate (0,0)

        assert repOk();
    }

    public Punto(double x, double y) {
        // MODIFIES: this
        // EFFECTS: inizializza un oggetto Punto di coordinate (x,y)
        this.x = x;
        this.y = y;

        assert repOk();
    }

    // methods

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanza(Punto p) throws NullPointerException {
        // EFFECTS: ritorna la distanza tra this e p
        // lancia NullPointerException se p è nullo
        if (p == null)
            throw new NullPointerException("Punto nullo");
        return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
    }

    public Punto copiaPunto(double x, double y) {
        //EFFECTS: crea un nuovo punto che si scosti da this dei valori specificati, poi lo ritorna
        return new Punto(this.x + x, this.y + y);
    }

    public boolean repOk() {
        //Eventuali controlli sulle coordinate di punto
        return true;
    }

    @Override
    public String toString() {
        return "( " + this.x + ", " + this.y + ")";
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

    @Override
    protected Object clone() {
        Punto p = null;
        try {
            p = (Punto) super.clone();
        } catch (CloneNotSupportedException e) {
            p = new Punto(this.x, this.y);
        }
        return p;
    }


}
