import javax.management.RuntimeErrorException;

public class A {
    
    public Studente stampaStudente(StudenteMagistrale s) {
        if (s == null)
            throw new RuntimeException("Non ci piace");
        return s;
    }
}
