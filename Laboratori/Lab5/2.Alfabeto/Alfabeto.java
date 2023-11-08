import java.util.Iterator;
import java.util.NoSuchElementException;

public class Alfabeto implements Iterator<Character> {
    // OVERVIEW: iteratore Standalone che itera su una porzione dell'alfabeto, da
    // start a end

    // attributes
    private char start, end, current;

    // constructors
    public Alfabeto() {
        // MODIFIES: this
        // EFFECTS: istanzia un nuovo iteratore Alfabeto, con start = 'a' ed end = 'z'.
        // Punta il corrente start
        start = 'a';
        end = 'z';
        current = start;
    }

    public Alfabeto(char start, char end) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: istanzia un nuovo iteratore Alfabeto, con start ed end specificati.
        // Punta il corrente a start
        // lancia IllegalArgumentException se start ed end non sono caratteri minuscoli
        // validi o se start > end
        if (!isValidLetter(start))
            throw new IllegalArgumentException("Primo carattere non valido");

        if (!isValidLetter(end))
            throw new IllegalArgumentException("Ultimo carattere non valido");

        if (start > end)
            throw new IllegalArgumentException("La prima lettera è successiva alla seconda");

        this.start = start;
        this.end = end;
        this.current = start;

        assert repOk();
    }

    @Override
    public boolean hasNext() {
        return this.current <= this.end;
    }

    @Override
    public Character next() throws NoSuchElementException {
        // MODIFIES: this
        // EFFECTS: ritorna la lettera dell'alfabeto successiva
        // lancia NoSuchElementException se non c'è una lettera successiva
        if (!hasNext())
            throw new NoSuchElementException("Non esiste un successivo");

        assert repOk();
        return this.current++;
    }

    public boolean repOk() {
        if (start > end)
            return false;

        if (current < start || current > end)
            return false;

        return true;
    }

    //functions

    public static boolean isValidLetter(char c) {
        // EFFECTS: ritorna true se c è una lettera minuscola dell'alfabeto latino,
        // false altrimenti
        return c >= 'a' && c <= 'z';
    }

    @Override
    public String toString() { // Non so quanto sia necessario in questo caso
        return "[ " + this.start + " ... " + this.current + " ... " + this.end + "]";
    }

    public static void main(String[] args) {

        Alfabeto a = null;
        try {
            a = new Alfabeto(args[0].charAt(0), args[1].charAt(0));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        while (a.hasNext()) {
            System.out.println(a.next());
        }
    }

}
