public class ScontoQuantita extends ScontoSemplice {
    //OVERVIEW: Politica di Sconto del tipo "Sconto Quantità". Estende PoliticaSconto. Immutabile

    //attributes
    public final int minimo;
    public final double percentuale;

    //constructors
    public ScontoQuantita(int numeroArticoli, double prezzoArticolo, int minimo, double percentuale) throws IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: inizializza un nuovo sconto quantità con super(numeroArticoli, prezzoArticolo) + minimo e percentuale
        //  lancia IllegalArgumentException se minimo < 0 o percentuale < 0
        super(numeroArticoli, prezzoArticolo);
        if (minimo < 0)
            throw new IllegalArgumentException("Minimo < 0");
        if (percentuale < 0)
            throw new IllegalArgumentException("Percentuale sconto < 0");
    
        this.minimo = minimo;
        this.percentuale = percentuale;
        
        assert repOk();
    }

    //methods
    @Override
    public double calcolaSconto() {
        if (numeroArticoli >= minimo)
            return prezzoTotaleListino() * percentuale / 100;
        return 0;
    }

    @Override
    public boolean repOk() {
        if (minimo < 0)
            return false;

        if (percentuale < 0)
            return false; 

        return super.repOk();
    }
    
    @Override
    public String toString() {
        return super.toString().replace("generica", "'Sconto Quantita''") + " - Sconto del " + percentuale + "% per quantita' superiori a " + (minimo-1);
    }

}
