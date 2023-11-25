public class ScontoQuantita implements PoliticaSconto {
    // OVERVIEW: Politica di Sconto del tipo "Sconto Quantità". Implementa
    // PoliticaSconto. Immutabile

    // attributes
    public final int numeroArticoli;
    public final double prezzoArticolo;
    public final int minimo;
    public final double percentuale;

    // constructors
    public ScontoQuantita(int numeroArticoli, double prezzoArticolo, int minimo, double percentuale)
            throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un nuovo sconto quantità 
        // lancia IllegalArgumentException se minimo < 0 o percentuale < 0
        if (numeroArticoli <= 0)
            throw new IllegalArgumentException("Numeri Articoli non valido");
        if (prezzoArticolo < 0)
            throw new IllegalArgumentException("Prezzo non valido");
        if (minimo < 0)
            throw new IllegalArgumentException("Minimo < 0");
        if (percentuale < 0)
            throw new IllegalArgumentException("Percentuale sconto < 0");

        this.numeroArticoli = numeroArticoli;
        this.prezzoArticolo = prezzoArticolo;
        this.minimo = minimo;
        this.percentuale = percentuale;

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
        if (numeroArticoli >= minimo)
            return prezzoTotaleListino() * percentuale / 100;
        return 0;
    }

    public boolean repOk() {
        if (numeroArticoli <= 0)
            return false;

        if (prezzoArticolo < 0)
            return false;

        if (minimo < 0)
            return false;

        if (percentuale < 0)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Politica di Sconto Quantita su " + numeroArticoli + " articoli da " + prezzoArticolo + " l'uno"
                + " - Sconto del " + percentuale + "% per quantita' superiori a " + (minimo - 1);
    }


}
