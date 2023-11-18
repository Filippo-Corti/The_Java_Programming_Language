public class Paziente extends Persona {
    // OVERVIEW: classe che descrive un Paziente di una clinica.
    // Estende Persona. Immutabile

    // attributes
    private final String id;

    public Paziente(String nome, String id)
            throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un Paziente con super(nome) + id
        // lancia NullPointerException se id o nome sono nulli
        // lancia IllegalArgumentException se nome è vuoto oppure id è vuoto
        super(nome);

        if (id == null)
            throw new NullPointerException("Id nullo");
        if (id.equals(""))
            throw new IllegalArgumentException("Id vuoto");

        this.id = id;

        assert repOk();
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean repOk() {
        if (id == null)
            return false;
        if (id.equals(""))
            return false;

        return super.repOk();
    }

    @Override
    public String toString() {
        return super.toString().replace("Persona", "Paziente") + ": Id: " + id;
    }


}
