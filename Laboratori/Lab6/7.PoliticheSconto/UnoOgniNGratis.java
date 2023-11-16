public class UnoOgniNGratis extends PoliticaSconto {
    // OVERVIEW: Politica di Sconto del tipo "Uno Ogni N è Gratis". Estende
    // PoliticaSconto. Immutabile

    // attributes
    public final int n;

    // constructors
    public UnoOgniNGratis(int numeroArticoli, double prezzoArticolo, int n) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un nuovo sconto quantità con super(numeroArticoli,
        // prezzoArticolo) + n
        // lancia IllegalArgumentException se n <= 0
        super(numeroArticoli, prezzoArticolo);
        if (n < 0)
            throw new IllegalArgumentException("n <= 0");

        this.n = n;

        assert repOk();
    }

    // methods
    @Override
    public double calcolaSconto() {
        return (numeroArticoli / n) * prezzoArticolo;
    }

    @Override
    public boolean repOk() {
        if (n <= 0)
            return false;

        return super.repOk();
    }

    @Override
    public String toString() {
        return super.toString().replace("generica", "'Uno Ogni " + n + " e' Gratis'");
    }

}
