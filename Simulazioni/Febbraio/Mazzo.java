public class Mazzo extends MazzoRidotto {
    //OVERVIEW: classe che descrive un Mazzo di 52 carte. Estende MazzoRidotto

    //constructors
    public Mazzo() {
        //MODIFIES: this
        //EFFECTS: inizializza Mazzo
        super();
        for (int i = 11; i <= 13; i++) {
            for (Seme seme : Seme.values()) {
                mazzo.add(new Carta(i, seme));
            }
        }

        assert repOk();
    }

    //methods
    public static boolean isValidCard(Carta c) {
        // EFFECTS: ritorna true se c è una carta valida. False altrimenti
        return (c != null) && (c.getValore() >= 1 && c.getValore() <= 13);
    }

    @Override
    public void sort() {
        mazzo.sort(null);
    }
    
    @Override
    public String toString() {
        String res = "Mazzo: \n";
        for (Carta carta : mazzo) {
            res += "\t" + carta + "\n";
        }
        return res;
    }
    
}
