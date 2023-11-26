public class Cilindro extends Contenitore {
    // OVERVIEW: classe che descrive un Contenitore Cilindrico

    // attributes
    public final double raggio;
    public final double altezza;

    // constructors
    public Cilindro(double raggio, double altezza) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza this con super() + raggio e altezza
        // lancia IllegalArgumentException se raggio o altezza sono <= 0
        super();
        if (raggio <= 0)
            throw new IllegalArgumentException("Raggio <= 0");

        if (altezza <= 0)
            throw new IllegalArgumentException("Altezza <= 0");

        this.raggio = raggio;
        this.altezza = altezza;

        assert repOk();
    }

    public Cilindro(String nomeLiquido, double quantitaLiquido, double raggio, double altezza)
            throws NullPointerException, IllegalArgumentException, ExceededCapacityException {
        // MODIFIES: this
        // EFFECTS: inizializza this con super(nomeLiquido, quantitaLiquido) + raggio e
        // altezza
        // lancia IllegalArgumentException se raggio o altezza sono <= 0
        // lancia ExceededCapacityException se quantitaLiquido > capacità di this
        super(nomeLiquido, quantitaLiquido);

        if (raggio <= 0)
            throw new IllegalArgumentException("Raggio <= 0");

        if (altezza <= 0)
            throw new IllegalArgumentException("Altezza <= 0");

        this.raggio = raggio;
        this.altezza = altezza;
        
        if (quantitaLiquido > this.getVolume()) 
            throw new ExceededCapacityException("Il cilindro ha capienza: " + getVolume()
            + " ma il liquido ha un volume di: " + quantitaLiquido);

        assert repOk();
    }

    // methods

    @Override
    public double getVolume() {
        return altezza * Math.PI * raggio * raggio;
    }

    public double getVolume(double raggio, double altezza) {
        return altezza * Math.PI * raggio * raggio;
    }

    @Override
    public boolean repOk() {
        if (raggio <= 0)
            return false;

        if (altezza <= 0)
            return false;

        return super.repOk();
    }

    @Override
    public String toString() {
        return "Cilindro - altezza: " + altezza + ", raggio: " + raggio + "\n  " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cilindro other = (Cilindro) obj;
        if (Double.doubleToLongBits(raggio) != Double.doubleToLongBits(other.raggio))
            return false;
        if (Double.doubleToLongBits(altezza) != Double.doubleToLongBits(other.altezza))
            return false;
        return true;
    }

    @Override
    protected Object clone() {
        Cilindro c = null;
        try {
            c = new Cilindro(getNomeLiquido(), getQuantitaLiquido(), raggio, altezza);
        } catch (ExceededCapacityException e) {
            // Impossibile
        }
        return c;
    }
}
