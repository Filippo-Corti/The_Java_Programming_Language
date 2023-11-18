public class ScontoCombinato implements PoliticaSconto {
    // OVERVIEW: Politica di Sconto Combinata da due ScontoSemplice.
    // Le due politiche di sconto ScontoSemplice devono avere stesso numeroArticoli
    // e prezzoArticolo.
    // Implementa PoliticaSconto

    // attributes
    private ScontoSemplice politica1;
    private ScontoSemplice politica2;

    // constructors
    public ScontoCombinato(ScontoSemplice p1, ScontoSemplice p2) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un nuovo sconto quantità con 2 Scontosemplice
        // lancia IllegalArgumentException se p1 o p2 è nullo o se p1 != p2
        if (p1 == null || p2 == null)
            throw new IllegalArgumentException("Politica Sconto nulla");

        if (!p1.equals(p2))
            throw new IllegalArgumentException("Politiche di Sconto non Combinabili");

        this.politica1 = p1;
        this.politica2 = p2;

        assert repOk();
    }

    // methods
    @Override
    public double calcolaSconto() {
        return max(politica1.calcolaSconto(), politica2.calcolaSconto());
    }

    private double max(double d1, double d2) {
        if (d1 > d2)
            return d1;
        return d2;
    }

    public boolean repOk() {
        if (politica1 == null || politica2 == null)
            return false;

        if (!politica1.equals(politica2))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return super.toString().replace("generica", "'Sconto Combinato'");
    }

}
