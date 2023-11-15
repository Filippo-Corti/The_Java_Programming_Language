import java.time.LocalDate;

public class Commedia extends Film {
    
    public Commedia(int id, String titolo, LocalDate dataNoleggio) throws NullPointerException, IllegalArgumentException {
        super(id, titolo);
        setDataNoleggio(dataNoleggio);
        setPenaleGiornaliera(2.50);
    }

    @Override
    public String toString() {
        return super.toString().replace("Film", "Commedia");
    }
}
