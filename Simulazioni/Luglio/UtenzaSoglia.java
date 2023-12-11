import java.time.temporal.TemporalAccessor;

import javax.naming.OperationNotSupportedException;

public class UtenzaSoglia extends UtenzaPrepagata {
    // OVERVIEW: classe che descrive un'Utenza con una Soglia, ovvero con un limite
    // di secondi di chiamata (settimanale)
    // Estende UtenzaPrepagata

    // attributes
    private final int soglia;

    // constructors
    public UtenzaSoglia(String numero, String nome, int soglia)
            throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un'Utenza Soglia con super() + soglia
        // lancia IllegalArgumentException se soglia < 0
        super(numero, nome);

        if (soglia < 0)
            throw new IllegalArgumentException("Soglia non valida");

        this.soglia = soglia;

        reset();
        assert repOk();
    }

    // methods
    public void reset() {
        // MODIFIES: this
        // EFFECTS: riporta il Credito Residuo alla Soglia stabilita
        super.ricarica(soglia);

        assert repOk();
    }

    public void ricarica(int secondi) throws IllegalArgumentException {
        throw new IllegalArgumentException("Operazione non ammessa su Utenza Soglia");
    }

    @Override
    public String toString() {
        return super.toString() + ", soglia: " + soglia;
    }

    public boolean repOk() {
        if (getCreditoResiduo() > soglia)
            return false;
        return true;
    }

}
