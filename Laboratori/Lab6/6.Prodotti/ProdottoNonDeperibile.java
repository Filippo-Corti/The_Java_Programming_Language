
public class ProdottoNonDeperibile extends Prodotto {
    //OVERVIEW: classe che descrive un ProdottoNonDeperibile, eventualmente riciclabile. 
    //Estende Prodotto

    //attributes
    private boolean riciclabile;
    private static final double PERCENTUALE_SCONTO_STRAORDINARIA = 10.00;

    //constructors
    public ProdottoNonDeperibile(String nome, double costo, boolean riciclabile) throws NullPointerException, IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: inizializza Prodotto con super(nome, costo) + flag riciclabile
        super(nome, costo);
        
        this.riciclabile = riciclabile;

        assert repOk();
    }

    public boolean isRiciclabile() {
        return riciclabile;
    }

   
    @Override
    public double sconto() {
        // EFFECTS: ritorna super.sconto() oppure il prezzo scontato di
        // PERCENTUALE_SCONTO_STRAORDINARIA%, se il prodotto è riciclabile
        if (riciclabile)
            return super.sconto(PERCENTUALE_SCONTO_STRAORDINARIA);
        else
            return super.sconto();
    }

    @Override
    public double getPercentualeSconto() {
        // EFFECTS: ritorna super.getPercentualeSconto() oppure
        // PERCENTUALE_SCONTO_STRAORDINARIA%, se il prodotto è riciclabile
        if (riciclabile)
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
        return super.repOk();
    }

}
