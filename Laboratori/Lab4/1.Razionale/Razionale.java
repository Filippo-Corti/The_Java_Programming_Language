import java.util.InputMismatchException;

public class Razionale {
    // OVERVIEW: classe mutabile che rappresenta un numero razionale (una frazione)

    //attributes
    int num;
    int den;

    // costructors
    public Razionale(int num, int den) throws ArithmeticException {
        // MODIFIES: this
        // EFFECTS: istanza un nuovo oggetto Razionale con num e den
        // lancia ArithmeticException se den = 0.
        if (den == 0)
            throw new ArithmeticException("Denominatore non puo' essere zero");

        this.num = num;
        this.den = den;

        assert repOk();
    }

    // methods
    public boolean repOk() {
        return den != 0;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;

        assert repOk();
    }

    public int getDen() {
        return den;
    }

    public void setDen(int den) throws ArithmeticException {
        // EFFECTS: setter di den
        // lancia ArithmeticException se den = 0;
        if (den == 0)
            throw new ArithmeticException("Denominatore non puo' essere zero");

        this.den = den;

        assert repOk();
    }

    public double valore() {
        // EFFECTS: ritorna num/den espresso in double
        return (double) num / den;
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }

    public static void main(String[] args) {
        int num = 0, den = 0;

        try {
            num = Integer.parseInt(args[0]);
            den = Integer.parseInt(args[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Parametri da riga di comando errati o mancanti: " + e.getMessage());
            System.exit(1);
        }

        try {
            Razionale razionale = new Razionale(num, den);
            System.out.println("Il razionale e' " + razionale + " o " + razionale.valore());
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

    }

}
