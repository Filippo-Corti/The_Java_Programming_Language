public class DecorazioneElettrica extends Decorazione implements Comparable<DecorazioneElettrica>{
    // OVERVIEW: classe che descrive una decorazione natalizia elettrica.
    // Estende decorazione
    // Comparabile in base alla potenza

    // attributes
    public final double potenza;
    private boolean accesa;

    // contructors
    public DecorazioneElettrica(String nome, double peso, double potenza)
            throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza una nuova DecorazioneElettrica con super(nome, peso) +
        // potenza.
        // E' inizializzata spenta
        // lancia IllegalArgumentException se potenza <= 0
        super(nome, peso);

        if (potenza <= 0)
            throw new IllegalArgumentException("Potenza <= 0");

        this.potenza = potenza;
        this.accesa = false;
    }

    //methods

    public boolean isAccesa() {
        return accesa;
    }

    public void accendi() {
        //MODIFIES: this
        //EFFECTS: accende this
        accesa = true;
    }

    public void spegni() {
        //MODIFIES: this
        //EFFECTS: spegne this 
        accesa = false;
    }

    @Override
    public int compareTo(DecorazioneElettrica d) {
        return Double.compare(this.potenza, d.potenza);
    }


    @Override
    public String toString() {
        return super.toString() + ", potenza: " + potenza + ", " + ((accesa) ? "accesa" : "spenta");
    }

    @Override
    public DecorazioneElettrica clone() {
        DecorazioneElettrica d = new DecorazioneElettrica(nome, peso, potenza);
        return d;
    }
}
