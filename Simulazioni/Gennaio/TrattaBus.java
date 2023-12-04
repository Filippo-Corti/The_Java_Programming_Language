public class TrattaBus extends Tratta {
    //OVERVIEW: classe che descrive una Tratta di Bus. Estende Tratta. Immutabile

    //attributes
    public final double qHr; //CO2/hr

    public TrattaBus(String origine, String destinazione, double lunghezza, double velocita, double qHr)
            throws NullPointerException, IllegalArgumentException {
                //MODIFIES: this
                //EFFECTS: inizializza una nuova Tratta di Treno con super(...) + qKm
                //  lancia IllegalArgumentException se qHr < 0
        super(origine, destinazione, lunghezza, velocita);

        if (qHr < 0)
            throw new IllegalArgumentException("Emissioni di C02 non valide");

        this.qHr = qHr;
    }

    //methods

    @Override
    public double getCO2() {
        return getDurata() * qHr;
    }

    @Override
    public String toString() {
        return "(Bus) " + super.toString() + ", qHr: " + qHr;
    }

}
