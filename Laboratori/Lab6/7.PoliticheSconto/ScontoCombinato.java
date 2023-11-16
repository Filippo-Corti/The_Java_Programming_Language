public class ScontoCombinato extends PoliticaSconto {
    //OVERVIEW: Politica di Sconto Combinata da due PoliticaSconto. Estende PoliticaSconto
    //  le due PoliticaSconto devono avere gli stessi parametri di ScontoCombinato ????

    //attributes
    private PoliticaSconto politica1;
    private PoliticaSconto politica2;

    //constructors
    public ScontoCombinato(int numeroArticoli, double prezzoArticolo, PoliticaSconto p1, PoliticaSconto p2) throws IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: inizializza un nuovo sconto quantità con super(numeroArticoli, prezzoArticolo) + 2 PoliticaSconto
        //  lancia IllegalArgumentException se p1 o p2 è nullo
        super(numeroArticoli, prezzoArticolo);
        if (p1 == null || p2 == null)
            throw new IllegalArgumentException("Politica Sconto nulla");
    
        this.politica1 = p1;
        this.politica2 = p2;
        
        assert repOk();
    }

    //methods
    @Override
    public double calcolaSconto() {
        return (numeroArticoli / n) * prezzoArticolo;    
    }

    @Override
    public boolean repOk() {
        if (politica1 == null || politica2 == null)
            return false;

        return super.repOk();
    }
    
    @Override
    public String toString() {
        return super.toString().replace("generica", "'Sconto Combinato'")
    }

}
