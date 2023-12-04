public class TrattaAereo extends Tratta {
    //OVERVIEW: classe che descrive una Tratta di Treno. Estende Tratta. Immutabile

    //attributes
    public final double qFix; //CO2 per il decollo
    public final double qKm; //CO2/km

    public TrattaAereo(String origine, String destinazione, double lunghezza, double velocita, double qFix, double qKm)
            throws NullPointerException, IllegalArgumentException {
                //MODIFIES: this
                //EFFECTS: inizializza una nuova Tratta di Treno con super(...) + qKm
                //  lancia IllegalArgumentException se qFix < 0 || qHr < 0
        super(origine, destinazione, lunghezza, velocita);

        if (qKm < 0 || qFix < 0)
            throw new IllegalArgumentException("Emissioni di C02 non valide");

        this.qKm = qKm;
        this.qFix = qFix;
    }

    //methods

    @Override
    public double getCO2() {
        return getLunghezza() * qKm + qFix;
    }

    @Override
    public String toString() {
        return "(Aereo) " + super.toString() + ", qKm: " + qKm + ", qFix: " + qFix;
    }

}
