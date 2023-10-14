import java.util.InputMismatchException;
import java.util.Scanner;

/* Mi sembrava molto poco sensato implementare una classe statica per una calcolatrice la cui peculiarità è avere uno stato, quindi è implementato come oggetto vero e proprio */

public class CalcolatriceSequenziale {
    // OVERVIEW: class implementing a sequential calculator. Mutable

    double risultato;

    // constructors
    public CalcolatriceSequenziale() {
        risultato = 0;
    }

    // methods
    public double getRisultato() {
        // EFFECTS: returns the current value for risultato
        return this.risultato;
    }

    public void calculateFromString(String s)
            throws NumberFormatException, InputMismatchException, DivisionByZeroException {
        // EFFECTS: applies the operation described in s to operand and returns it

        if (s == null)
            throw new NullPointerException("Parametro nullo");

        String[] splitInput = s.split(" ");

        if (splitInput.length != 2)
            throw new InputMismatchException("Non sono stati inseriti tutti gli input richiesti");

        double operand2 = 0;
        try {
            operand2 = Double.parseDouble(splitInput[1]);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Operando non valido");
        }

        this.risultato = calculate(this.risultato, splitInput[0].charAt(0), operand2);

    }

    private static double calculate(double operand1, char operator, double operand2)
            throws InputMismatchException, DivisionByZeroException {
        // EFFECTS: returns operand1 operator operand2. i.e., 3.5 + 6.0 returns 9.5

        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new DivisionByZeroException("Non e' possibile dividere per 0");
                }
                return operand1 / operand2;
            default:
                throw new InputMismatchException("Operatore non riconosciuto");
        }
    }

    // main
    public static void main(String[] args) {
        CalcolatriceSequenziale cs = new CalcolatriceSequenziale();

        Scanner s = new Scanner(System.in);

        System.out.println("Inserisci <operatore> <operando>. es: + 3");
        System.out.println("Per terminare inserire il carattere '='");

        System.out.println("risultato = " + cs.getRisultato());
        while (true) {
            String in = s.nextLine();
            if (in.equals("="))
                break;
            try {
                cs.calculateFromString(in);
                System.out.println("risultato " + in + " = " + cs.getRisultato());
            } catch (NumberFormatException | InputMismatchException | DivisionByZeroException e) {
                System.out.println(e.getMessage());
                System.out.println("risultato = " + cs.getRisultato());
            }
        }

        System.out.println("Il risultato finale e' " + cs.getRisultato());

    }

}