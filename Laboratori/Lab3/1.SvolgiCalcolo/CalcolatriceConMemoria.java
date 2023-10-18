import java.util.InputMismatchException;

public class CalcolatriceConMemoria {
    //OVERVIEW: classe mutabile che implementa una calcolatrice sequenziale, ovvero con uno stato in memoria

    double mem;

    //costruttori
    public CalcolatriceConMemoria() {
        //MODIFES: this
        //EFFECTS: inizializza e restituisce una nuova CalcolatriceConMemoria, con stato = 0
        this.mem = 0;
    }

    public CalcolatriceConMemoria(double mem) {
        //MODIFIES: this
        //EFFECTS: inizializza e restituisce una nuova CalcolatriceConMemoria, con stato = mem
        this.mem = mem;
    }

    //metodi
    public double getMem() {
        return mem;
    }

    public double add(double op2) {
        //MODIFIES: this
        //EFFECTS: aggiunge op2 alla memoria e la restituisce
        return mem += op2;
    }

    public double sub(double op2) {
        //MODIFIES: this
        //EFFECTS: sottrae op2 alla memoria e la restituisce
        return mem -= op2;
    }

    public double mul(double op2) {
        //MODIFIES: this
        //EFFECTS: moltipla la memoria per op2 e la restituisce
        return mem *= op2;
    }

    public double div(double op2) throws DivideByZeroException {
        //MODIFIES: this
        //EFFECTS: divide la memoria per op2 e la restituisce
        //  lancia DivideByZeroException se op2 = 0
        if (op2 == 0) 
            throw new DivideByZeroException("Non e' possibile divedere per 0");
        return mem /= op2;
    }

    public double operate(char operator, double op2) throws InputMismatchException, DivideByZeroException {
        //MODIFIES: this
        //EFFECTS: svolge l'operazione corrispondente a operator op2 su memoria e la ritorna (mem_post = mem operator op2)
        //  lancia InputMismatchException se l'operatore non e' riconosciuto
        //  lancia DivideByZeroException se i parametri sono ('/', 0)
        switch (operator) {
            case '+':
                return add(op2);
            case '-':
                return sub(op2);
            case '*':
                return mul(op2);
            case '/':
                return div(op2);
            default:
                throw new InputMismatchException("Operatore non valido");
        }
    }

}
