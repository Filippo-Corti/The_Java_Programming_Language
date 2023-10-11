public class Sottostringa {

    public static void main(String[] args) {
        if (sottoStringa(args[0], args[1])) {
            System.out.println(args[1] + " è sottostringa di " + args[0]);
        } else {
            System.out.println(args[1] + " non è sottostringa di " + args[0]);
        }
        //System.out.println(sottoStringa(null, null));
        //System.out.println(sottoStringaTotale(null, null));
    }

    // Procedura parziale
    // REQUIRES: testo, parola not null
    // MODIFIES: /
    // EFFECTS: return true if parola is substring of testo,
    // false otherwise. 
    // Not case sensity (e.g. sottoStringa(Seat, eaT) returns true)
    public static boolean sottoStringa(String testo, String parola) {
        testo = testo.toLowerCase();
        parola = parola.toLowerCase();
        return testo.contains(parola);
    }

    // Procedura totale
    // MODIFIES: /
    // EFFECTS: return true if parola is substring of testo,
    // false otherwise (even if testo or parola are null)
    public static boolean sottoStringaTotale(String testo, String parola) {
         if (testo == null || parola == null) 
           return false;
        testo = testo.toLowerCase();
        parola = parola.toLowerCase();
        return testo.contains(parola);
    }

}
