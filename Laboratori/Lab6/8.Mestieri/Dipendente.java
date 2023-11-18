public class Dipendente extends Persona {
    // OVERVIEW: classe che descrive un Dipendente di un'azienda.
    // Estende Persona.

    // attributes
    private String id;
    private double retribuzioneAnnuale;
    private final int annoAssuzione;

    public Dipendente(String nome, String id, double retribuzioneAnnuale, int annoAssuzione)
            throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un Dipendente con super(nome) + id, retribuzione e anno
        // di assunzione
        // lancia NullPointerException se id o nome sono nulli
        // lancia IllegalArgumentException se nome è vuoto, id è vuoto,
        // retribuzioneAnnuale < 0
        super(nome);

        if (id == null)
            throw new NullPointerException("id nullo");
        if (id.equals(""))
            throw new IllegalArgumentException("id vuoto");
        if (retribuzioneAnnuale < 0)
            throw new IllegalArgumentException("Retribuzione < 0");

        this.id = id;
        this.retribuzioneAnnuale = retribuzioneAnnuale;
        this.annoAssuzione = annoAssuzione;

        assert repOk();
    }

    public String getId() {
        return id;
    }

    public double getRetribuzioneAnnuale() {
        return retribuzioneAnnuale;
    }

    public void setRetribuzioneAnnuale(double retribuzioneAnnuale) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: modifica retribuzioneAnnuale di this
        // lancia IllegalArgumentException se retribuzioneAnnuale < 0
        if (retribuzioneAnnuale < 0)
            throw new IllegalArgumentException("Retribuzione < 0");
            
        this.retribuzioneAnnuale = retribuzioneAnnuale;

        assert repOk();
    }

    public int getAnnoAssuzione() {
        return annoAssuzione;
    }

    @Override
    public boolean repOk() {
        if (id == null)
            return false;
        if (id.equals(""))
            return false;
        if (retribuzioneAnnuale < 0)
            return false;

        return super.repOk();
    }

    @Override
    public String toString() {
        return super.toString().replace("Persona", "Dipendente") + ": Retribuzione " + retribuzioneAnnuale
                + " - Anno di Assuzione: " + annoAssuzione;
    }

}
