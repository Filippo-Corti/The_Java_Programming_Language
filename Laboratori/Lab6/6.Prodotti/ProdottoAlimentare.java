import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProdottoAlimentare extends Prodotto {
    // OVERVIEW: classe che descrive un ProdottoAlimentare, con una scadenza.
    // Estende Prodotto

    // attributes
    private final LocalDate dataScadenza;
    private static final double PERCENTUALE_SCONTO_STRAORDINARIA = 30.00;

    // constructors
    public ProdottoAlimentare(String nome, double costo, LocalDate dataScadenza)
            throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza Prodotto con super(nome, costo) + data di scadenza
        // lancia NullPointerException se dataScadenza è nulla
        super(nome, costo);

        if (dataScadenza == null)
            throw new NullPointerException("Data nulla");

        this.dataScadenza = dataScadenza;

        assert repOk();
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    @Override
    public double sconto() {
        // EFFECTS: ritorna super.sconto() oppure il prezzo scontato di
        // PERCENTUALE_SCONTO_STRAORDINARIA%, se la scadenza è a meno di 10 giorni
        if (dataScadenza.until(LocalDate.now(), ChronoUnit.DAYS) < 10)
            return super.sconto(PERCENTUALE_SCONTO_STRAORDINARIA);
        else
            return super.sconto();
    }

    @Override
    public double getPercentualeSconto() {
        // EFFECTS: ritorna super.getPercentualeSconto() oppure
        // PERCENTUALE_SCONTO_STRAORDINARIA%, se la scadenza è a meno di 10 giorni
        if (dataScadenza.until(LocalDate.now(), ChronoUnit.DAYS) < 10)
            return PERCENTUALE_SCONTO_STRAORDINARIA;
        else
            return super.getPercentualeSconto();
    }

    @Override
    public String toString() {
        return super.toString().replace("Prodotto", "Prodotto Alimentare");
    }

    @Override
    public boolean repOk() {
        if (dataScadenza == null)
            return false;

        return super.repOk();
    }

}
