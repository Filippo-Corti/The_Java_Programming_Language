public abstract class PoliticaSconto {
    // OVERVIEW: Classe astratta che descrive una generica Politica di Sconto
    // su numeroArticoli articoli di prezzo prezzoArticolo. Immutabile

    // attributes
    public final int numeroArticoli;
    public final double prezzoArticolo;

    // constructor
    public PoliticaSconto(int numeroArticoli, double prezzoArticolo) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza una nuova Politica di Sconto
        // lancia IllegalArgumentException se numeroArticoli <= 0 o prezzoArticolo < 0
        if (numeroArticoli <= 0)
            throw new IllegalArgumentException("Numeri Articoli non valido");
        if (prezzoArticolo < 0)
            throw new IllegalArgumentException("Prezzo non valido");

        this.numeroArticoli = numeroArticoli;
        this.prezzoArticolo = prezzoArticolo;

        assert repOk();
    }

    // methods
    public abstract double calcolaSconto();

    public double prezzoTotaleListino() {
        //EFFECTS: ritorna numeroArticoli * prezzoArticolo, senza applicare sconti
        return numeroArticoli * prezzoArticolo;
    }

    public boolean repOk() {
        if (numeroArticoli <= 0)
            return false;
        if (prezzoArticolo < 0)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Politica di Sconto generica su " + numeroArticoli + " articoli da " + prezzoArticolo + " l'uno";
    }

}
