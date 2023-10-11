public class Sottostringa {
    //OVERVIEW: static class that contains methods to find whether a string is substring of another string

    public static void main(String[] args) {
        if (sottoStringa(args[0], args[1])) {
            System.out.println(args[1] + " è sottostringa di " + args[0]);
        } else {
            System.out.println(args[1] + " non è sottostringa di " + args[0]);
        }
        // System.out.println(sottoStringa(null, null));
        // System.out.println(sottoStringaTotale(null, null));
    }

    // Procedura parziale
    public static boolean sottoStringa(String testo, String parola) {
        // REQUIRES: testo, parola not null
        // MODIFIES: /
        // EFFECTS: return true if parola is substring of testo,
        // false otherwise.
        // Not case sensity (e.g. sottoStringa(Seat, eaT) returns true)
        testo = testo.toLowerCase();
        parola = parola.toLowerCase();
        return testo.contains(parola);
    }

    // Procedura totale
    public static boolean sottoStringaTotale(String testo, String parola) {
        // EFFECTS: return true if parola is substring of testo,
        // false otherwise (including the case where testo and/or parola are null).
        // Not case sensity (e.g. sottoStringa(Seat, eaT) returns true)
        if (testo == null || parola == null)
            return false;
        testo = testo.toLowerCase();
        parola = parola.toLowerCase();
        return testo.contains(parola);
    }

}
