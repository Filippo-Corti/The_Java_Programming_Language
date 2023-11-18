public class Dottore extends Persona {
    // OVERVIEW: classe che descrive un Dottore di una clinica.
    // Estende Persona.

    // attributes
    private String specializzazione;
    private double parcella;

    public Dottore(String nome, String specializzazione, double parcella)
            throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un Dottore con super(nome) + parcella e specializzazione
        // lancia NullPointerException se specializzazione o nome sono nulli
        // lancia IllegalArgumentException se nome è vuoto, specializzazione è vuoto,
        // parcella < 0
        super(nome);

        if (specializzazione == null)
            throw new NullPointerException("Specializzazione nulla");
        if (specializzazione.equals(""))
            throw new IllegalArgumentException("Specializzazione vuota");
        if (parcella < 0)
            throw new IllegalArgumentException("Parcella < 0");

        this.specializzazione = specializzazione;
        this.parcella = parcella;

        assert repOk();
    }

    public String getSpecializzazione() {
        return specializzazione;
    }

    public double getParcella() {
        return parcella;
    }

    public void setSpecializzazione(String specializzazione) throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: aggiorna specializzazione di this
        // lancia NullPointerException se specializzazione è nulla
        // lancia IllegalArgumentException se specializzazione è vuota
        if (specializzazione == null)
            throw new NullPointerException("Specializzazione nulla");
        if (specializzazione.equals(""))
            throw new IllegalArgumentException("Specializzazione vuota");

        this.specializzazione = specializzazione;

        assert repOk();
    }

    public void setParcella(double parcella) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: aggiorna specializzazione di this
        // lancia IllegalArgumentException se parcella < 0
        if (parcella < 0)
            throw new IllegalArgumentException("Parcella < 0");

        this.parcella = parcella;

        assert repOk();
    }

    @Override
    public boolean repOk() {
        if (specializzazione == null)
            return false;
        if (specializzazione.equals(""))
            return false;
        if (parcella < 0)
            return false;

        return super.repOk();
    }

    @Override
    public String toString() {
        return super.toString().replace("Persona", "Dottor") + ": Specializzazione: " + specializzazione
                + " - Parcella: " + parcella;
    }

}
