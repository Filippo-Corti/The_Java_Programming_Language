public class Sfera extends Contenitore {
    // OVERVIEW: classe che descrive un Contenitore Sferico

    // attributes
    public final double raggio;

    // constructors
    public Sfera(double raggio) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza this con super() + raggio
        // lancia IllegalArgumentException se raggio <= 0
        super();
        if (raggio <= 0)
            throw new IllegalArgumentException("Raggio <= 0");

        this.raggio = raggio;

        assert repOk();
    }

    public Sfera(String nomeLiquido, double quantitaLiquido, double raggio)
            throws NullPointerException, IllegalArgumentException, ExceededCapacityException {
        // MODIFIES: this
        // EFFECTS: inizializza this con super(nomeLiquido, quantitaLiquido) + raggio 
        // lancia IllegalArgumentException se raggio <= 0
        // lancia ExceededCapacityException se quantitaLiquido > capacità di this
        super(nomeLiquido, quantitaLiquido);

        if (raggio <= 0)
            throw new IllegalArgumentException("Raggio <= 0");

        this.raggio = raggio;
        
        if (quantitaLiquido > this.getVolume()) 
            throw new ExceededCapacityException("Il cuboide ha capienza: " + getVolume()
            + " ma il liquido ha un volume di: " + quantitaLiquido);

        assert repOk();
    }

    // methods

    @Override
    public double getVolume() {
        return 4./3 * Math.PI * raggio * raggio * raggio;
    }

    @Override
    public boolean repOk() {
        if (raggio <= 0)
            return false;

        return super.repOk();
    }

    @Override
    public String toString() {
        return "Sfera - raggio: " + raggio + "\n  " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sfera other = (Sfera) obj;
        if (Double.doubleToLongBits(raggio) != Double.doubleToLongBits(other.raggio))
            return false;
        return true;
    }

    @Override
    protected Object clone() {
        Sfera s = null;
        try {
            s = new Sfera(getNomeLiquido(), getQuantitaLiquido(), raggio);
        } catch (ExceededCapacityException e) {
            // Impossibile
        }
        return s;
    }
    
}
