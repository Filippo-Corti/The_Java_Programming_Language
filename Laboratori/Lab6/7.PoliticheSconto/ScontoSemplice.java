public abstract class ScontoSemplice implements PoliticaSconto {
    // OVERVIEW: Classe astratta che descrive una Politica di Sconto Semplice
    // (singola)
    // su numeroArticoli articoli di prezzo prezzoArticolo. Implementa l'interfaccia
    // PoliticaSconto. Immutabile

    // attributes
    public final int numeroArticoli;
    public final double prezzoArticolo;

    // constructor
    public ScontoSemplice(int numeroArticoli, double prezzoArticolo) throws IllegalArgumentException {
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

    public double prezzoTotaleListino() {
        // EFFECTS: ritorna numeroArticoli * prezzoArticolo, senza applicare sconti
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof ScontoSemplice))
            return false;
        ScontoSemplice other = (ScontoSemplice) obj;
        if (numeroArticoli != other.numeroArticoli)
            return false;
        if (Double.doubleToLongBits(prezzoArticolo) != Double.doubleToLongBits(other.prezzoArticolo))
            return false;
        return true;
    }

}
