import java.time.temporal.TemporalAccessor;

public class UtenzaPrepagata extends Utenza {
    //OVERVIEW: classe che descrive un'Utenza Prepagata, ovvero con un limite di secondi di chiamata (ricaricabile) 
    //Estende Utenza

    //attributes
    private int creditoResiduo;

    //constructor
    public UtenzaPrepagata(String numero, String nome) throws NullPointerException, IllegalArgumentException {
       //MODIFIES: this
       //EFFECTS: 
        super(numero, nome);
        creditoResiduo = 0;

        assert repOk();
    }
    //methods
    public int getCreditoResiduo() {
        return creditoResiduo;
    }

    @Override
    public void chiama(int secondi) throws IllegalArgumentException, TempoEsauritoException {
        // MODIFIES: this
        // EFFECTS: incrementa i secondi totali di conversazione di secondi
        //  lancia IllegalArgumentException se secondi < 0
        //  lancia TempoEsauritoException se l'utente ha esaurito il tempo disponibile
        if (secondi < 0)
            throw new IllegalArgumentException("Secondi non validi");
        if (creditoResiduo < secondi) 
            throw new TempoEsauritoException("Credito esaurito");

        incrementaSecondiDiConversazione(secondi);
        creditoResiduo -= secondi;

        assert repOk();
    }

    public void ricarica(int secondi) throws IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: imposta il credito residuo a secondi
        //  lancia IllegalArgumentException se secondi < 0
        if (secondi < 0)
            throw new IllegalArgumentException("Secondi non validi");

        this.creditoResiduo = secondi;

        assert repOk();
    }

    @Override
    public String toString() {
        return super.toString() + ", credito: " + creditoResiduo;
    }

    @Override
    public boolean repOk() {
        if (creditoResiduo < 0)
            return false;
        return super.repOk();
    }
    
}