import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Film {
    // OVERVIEW: classe astratta che descrive un Film per il noleggio

    //attributes
    public final int id;
    public final String titolo;
    private LocalDate dataNoleggio;
    private double penaleGiornaliera;

    //constructors
    public Film(int id, String titolo) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un Film con id e titolo
        // lancia IllegalArgumentException se titolo è nullo o vuoto
        if (titolo == null || titolo.equals(""))
            throw new IllegalArgumentException("Titolo vuoto o nullo");

        this.id = id;
        this.titolo = titolo;
        this.penaleGiornaliera = 2.00;

        assert repOk();
    }

    //methods
    public double calcolaPenaleRitardo() {
        // EFFECTS: ritorna la penale di ritardo di restituzione per this, se sono
        // passati pi
        // di 7 giorni dalla data di noleggio
        // ritorna 0 se la restituzione non è ancora in ritardo
        LocalDate today = LocalDate.now();
        LocalDate dataRiconsegna = dataNoleggio.plusDays(7);
        if (dataRiconsegna.isBefore(today)) {
            // In Ritardo!
            return penaleGiornaliera *  dataRiconsegna.until(today, ChronoUnit.DAYS);
        }
        return 0;
    }

    public LocalDate getDataNoleggio() {
        return dataNoleggio;
    }

    public void setDataNoleggio(LocalDate dataNoleggio) throws NullPointerException{
        if (dataNoleggio == null)
            throw new NullPointerException("Data nulla");
        this.dataNoleggio = dataNoleggio;

        assert repOk();
    }

    public double getPenaleGiornaliera() {
        return penaleGiornaliera;
    }

    public void setPenaleGiornaliera(double penaleGiornaliera) throws IllegalArgumentException{
        if (penaleGiornaliera < 0)
            throw new IllegalArgumentException("Penale negativa");
        this.penaleGiornaliera = penaleGiornaliera;
    }

    @Override
    public String toString() {
        return "Film " + titolo + " noleggiato il " + dataNoleggio.toString();
    }

    public boolean repOk() {
        if (titolo == null || titolo.equals(""))
            return false;

        if (dataNoleggio == null)
            return false;

        if (dataNoleggio.isAfter(LocalDate.now()))
            return false;

        if (penaleGiornaliera < 0)
            return false;

        return true;
    }

}
