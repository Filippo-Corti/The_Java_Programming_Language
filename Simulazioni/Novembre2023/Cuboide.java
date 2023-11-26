public class Cuboide extends Contenitore {
    // OVERVIEW: classe che descrive un Contenitore Cuboidale

    // attributes
    public final double a;
    public final double b;
    public final double c;

    // constructors
    public Cuboide(double a, double b, double c) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza this con super() + a, b e c
        // lancia IllegalArgumentException se a, b o c sono <= 0
        super();

        if (a <= 0 || b <= 0 || c <= 0)
            throw new IllegalArgumentException("Lati <= 0");

        this.a = a;
        this.b = b;
        this.c = c;

        assert repOk();
    }

    public Cuboide(String nomeLiquido, double quantitaLiquido, double a, double b, double c)
            throws NullPointerException, IllegalArgumentException, ExceededCapacityException {
        // MODIFIES: this
        // EFFECTS: inizializza this con super(nomeLiquido, quantitaLiquido) + a, b e c
        // lancia IllegalArgumentException se a, b o c sono <= 0
        // lancia ExceededCapacityException se quantitaLiquido > capacità di this
        super(nomeLiquido, quantitaLiquido);

        if (a <= 0 || b <= 0 || c <= 0)
            throw new IllegalArgumentException("Lati <= 0");

        this.a = a;
        this.b = b;
        this.c = c;
        
        if (quantitaLiquido > this.getVolume()) 
            throw new ExceededCapacityException("Il cuboide ha capienza: " + getVolume()
            + " ma il liquido ha un volume di: " + quantitaLiquido);

        assert repOk();
    }

    // methods

    @Override
    public double getVolume() {
        return a * b * c;
    }

    @Override
    public boolean repOk() {
        if (a <= 0 || b <= 0 || c <= 0)
            return false;

        return super.repOk();
    }

    @Override
    public String toString() {
        return "Cilindro - lati: " + a + ", " + b  + ", " + c + "\n  " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cuboide other = (Cuboide) obj;
        if (Double.doubleToLongBits(a) != Double.doubleToLongBits(other.a))
            return false;
        if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
            return false;
        if (Double.doubleToLongBits(c) != Double.doubleToLongBits(other.c))
            return false;
        return true;
    }

    
    @Override
    protected Object clone() {
        Cuboide s = null;
        try {
            s = new Cuboide(getNomeLiquido(), getQuantitaLiquido(), a, b, c);
        } catch (ExceededCapacityException e) {
            // Impossibile
        }
        return s;
    }

    
}
