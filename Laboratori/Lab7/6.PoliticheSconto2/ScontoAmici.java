public class ScontoAmici extends ScontoQuantita {
    // OVERVIEW: Politica di Sconto del tipo "Sconto Amici". Estende ScontoQuantita, con quantita minima = 0. Immutabile

    // constructors
    public ScontoAmici(int numeroArticoli, double prezzoArticolo, double percentuale) throws IllegalArgumentException {
        super(numeroArticoli, prezzoArticolo, 0, percentuale);
    }

    // methods
    @Override
    public String toString() {
        return "Politica di Sconto Amici su " + numeroArticoli + " articoli da " + prezzoArticolo + " l'uno"
                + " - Sconto del " + percentuale + "%";
    }

}
