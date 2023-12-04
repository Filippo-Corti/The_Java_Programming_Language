public class TrattaTreno extends Tratta {
    //OVERVIEW: classe che descrive una Tratta di Treno. Estende Tratta. Immutabile

    //attributes
    public final double qKm; //CO2/km

    public TrattaTreno(String origine, String destinazione, double lunghezza, double velocita, double qKm)
            throws NullPointerException, IllegalArgumentException {
                //MODIFIES: this
                //EFFECTS: inizializza una nuova Tratta di Treno con super(...) + qKm
                //  lancia IllegalArgumentException se qKm < 0
        super(origine, destinazione, lunghezza, velocita);

        if (qKm < 0)
            throw new IllegalArgumentException("Emissioni di C02 non valide");

        this.qKm = qKm;
    }

    //methods

    @Override
    public double getCO2() {
        return getLunghezza() * qKm;
    }

    @Override
    public String toString() {
        return "(Treno) " + super.toString() + ", qKm: " + qKm;
    }

}
