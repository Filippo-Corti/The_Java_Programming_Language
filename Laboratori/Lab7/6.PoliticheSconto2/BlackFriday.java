import java.time.LocalDate;
import java.time.Month;

public class BlackFriday implements PoliticaSconto {
    // OVERVIEW: Politica di Sconto del tipo "Black Friday". Implementa
    // PoliticaSconto. Immutabile

    // attributes
    public final int numeroArticoli;
    public final double prezzoArticolo;

    // constructors
    public BlackFriday(int numeroArticoli, double prezzoArticolo) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un nuovo sconto black friday 
        if (numeroArticoli <= 0)
            throw new IllegalArgumentException("Numeri Articoli non valido");
        if (prezzoArticolo < 0)
            throw new IllegalArgumentException("Prezzo non valido");

        this.numeroArticoli = numeroArticoli;
        this.prezzoArticolo = prezzoArticolo;

        assert repOk();
    }

    // methods
    @Override
    public double getNumeroArticoli() {
        return numeroArticoli;
    }

    @Override
    public double getPrezzoArticolo() {
        return prezzoArticolo;
    }

    @Override
    public double calcolaSconto() {
        LocalDate today = LocalDate.now();
        if (today.getMonth() == Month.NOVEMBER)
            return prezzoTotaleListino() * today.getDayOfMonth() / 100;
        return 0;
    }

    public boolean repOk() {
        if (numeroArticoli <= 0)
            return false;

        if (prezzoArticolo < 0)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Politica di Sconto Black Friday su " + numeroArticoli + " articoli da " + prezzoArticolo + " l'uno";
    }
}