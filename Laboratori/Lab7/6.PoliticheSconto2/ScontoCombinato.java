import java.util.ArrayList;

public class ScontoCombinato implements PoliticaSconto {
    // OVERVIEW: Politica di Sconto Combinata da N Politiche Sconto.
    // Le politiche di sconto devono avere stesso numeroArticoli e prezzoArticolo.
    // Implementa PoliticaSconto

    // attributes
    private ArrayList<PoliticaSconto> politiche = new ArrayList<>();

    // constructors
    public ScontoCombinato(ArrayList<PoliticaSconto> politiche) throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un nuovo sconto quantità con politiche
        // lancia NullPointerException se politiche o uno dei suoi elementi è nullo
        // lancia IllegalArgumentException se le politiche non hanno uguale
        // numeroArticoli e prezzoArticolo
        if (politiche == null)
            throw new NullPointerException("Politiche nulle");

        if (!politiche.isEmpty()) {
            PoliticaSconto primaPolitica = politiche.get(0);

            for (PoliticaSconto politicaSconto : politiche) {

                if (politicaSconto == null)
                    throw new NullPointerException("Politica nulla");

                if (politicaSconto.getNumeroArticoli() != primaPolitica.getNumeroArticoli()
                        || politicaSconto.getPrezzoArticolo() != primaPolitica.getPrezzoArticolo())
                    throw new IllegalArgumentException("Politiche non combinabili");
            }

        }

        for (PoliticaSconto politicaSconto : politiche) {
            this.politiche.add(politicaSconto);
        }

        assert repOk();
    }

    // methods
    @Override
    public double calcolaSconto() {
        return politiche.stream().reduce(politiche.get(0), (max, el) -> el.calcolaSconto() > max.calcolaSconto() ? el : max).calcolaSconto();
    }

    public boolean repOk() {
        if (politiche == null)
            return false;

        if (politiche.isEmpty())
            return true;

        PoliticaSconto primaPolitica = politiche.get(0);

        for (PoliticaSconto politicaSconto : politiche) {
            if (politicaSconto == null)
                return false;
            if (politicaSconto.getNumeroArticoli() != primaPolitica.getNumeroArticoli()
                    || politicaSconto.getPrezzoArticolo() != primaPolitica.getPrezzoArticolo())
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Sconto Combinato tra " + politiche.size() + " politiche";
    }

    @Override
    public double getNumeroArticoli() {
        return politiche.get(0).getNumeroArticoli();
    }

    @Override
    public double getPrezzoArticolo() {
        return politiche.get(0).getPrezzoArticolo();
    }


}
