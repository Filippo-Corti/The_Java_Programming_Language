public class Sottostringa {

    public static void main(String[] args) {
        if (sottoStringa(args[0], args[1])) {
            System.out.println(args[1] + " è sottostringa di " + args[0]);
        } else {
            System.out.println(args[1] + " non è sottostringa di " + args[0]);
        }
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
    // REQUIRES: testo, parola not null
    // MODIFIES: /
    // EFFECTS: return true if parola is substring of testo,
    // false otherwise
    public static boolean sottoStringaTotale(String testo, String parola) {
       // if (testo == null || parola == null) 
         //   return null;
        
        return testo.contains(parola);
    }

}
