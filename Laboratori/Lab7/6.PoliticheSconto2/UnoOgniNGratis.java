public class UnoOgniNGratis implements PoliticaSconto {
    // OVERVIEW: Politica di Sconto del tipo "Uno Ogni N è Gratis". Implementa
    // PoliticaSconto. Immutabile

    // attributes
    public final int numeroArticoli;
    public final double prezzoArticolo;
    public final int n;

    // constructors
    public UnoOgniNGratis(int numeroArticoli, double prezzoArticolo, int n) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un nuovo sconto uno-ogni-n-gratis 
        // lancia IllegalArgumentException se n <= 0
        if (numeroArticoli <= 0)
            throw new IllegalArgumentException("Numeri Articoli non valido");
        if (prezzoArticolo < 0)
            throw new IllegalArgumentException("Prezzo non valido");
        if (n < 0)
            throw new IllegalArgumentException("n <= 0");

        this.numeroArticoli = numeroArticoli;
        this.prezzoArticolo = prezzoArticolo;
        this.n = n;

        assert repOk();
    }

    // methods
    @Override
    public double getNumeroArticoli() {
        return numeroArticoli;
    }

    @Override
    public double getPrezzoArticolo() {
        return prezzoArticolo;
    }

    @Override
    public double calcolaSconto() {
        return (numeroArticoli / n) * prezzoArticolo;
    }

    public boolean repOk() {
        if (numeroArticoli <= 0)
            return false;

        if (prezzoArticolo < 0)
            return false;

        if (n <= 0)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Politica di Sconto Quantità su " + numeroArticoli + " articoli da " + prezzoArticolo + " l'uno"
                + " - Uno Ogni " + n + " e' Gratis";
    }
}