import java.time.LocalDate;

public class Azione extends Film {

    public Azione(int id, String titolo, LocalDate dataNoleggio) throws NullPointerException, IllegalArgumentException {
        super(id, titolo);
        setDataNoleggio(dataNoleggio);
        setPenaleGiornaliera(3.00);
    }

    @Override
    public String toString() {
        return super.toString().replace("Film", "Film d'Azione");
    }
    
}
