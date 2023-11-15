import java.time.LocalDate;

public class Dramma extends Film {
    
    public Dramma(int id, String titolo, LocalDate dataNoleggio) throws NullPointerException, IllegalArgumentException {
        super(id, titolo);
        setDataNoleggio(dataNoleggio);
    }

    @Override
    public String toString() {
        return super.toString().replace("Film", "Film Drammatico");
    }
    
}
