import java.util.InputMismatchException;
import java.util.Scanner;

public class Calcolatrice {
    // OVERVIEW a static class for basic arithmetic operations

    public static void main(String[] args) {
        System.out.println("Inserisci <operando1> <operatore> <operando2>. es: 4 + 3");

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        try {
            System.out.println("Il risultato e' " + calculateFromString(input));
        } catch (InputMismatchException | NullPointerException | DivisionByZeroException e) {
            System.out.println(e.getMessage());
        }

    }

    public static double calculate(double o1, double o2, char op)
            throws InputMismatchException, DivisionByZeroException {
        // EFFECTS: returns o1 op o2. i.e. (o1 = 2.0, op = '+', o2 = 6.5) => 2.0+6.5=8.5
        switch (op) {
            case '+':
                return o1 + o2;
            case '-':
                return o1 - o2;
            case '*':
                return o1 * o2;
            case '/':
                if (o2 == 0)
                    throw new DivisionByZeroException("Non e' possibile dividere per 0");
                return o1 / o2;
            default:
                throw new InputMismatchException("Operatore non riconosciuto");
        }
    }

    public static double calculateFromString(String s)
            throws NullPointerException, InputMismatchException, DivisionByZeroException {
        // EFFECTS: returns the result of the expression s
        if (s == null)
            throw new NullPointerException();

        String[] inputs = s.split(" ");

        if (inputs.length != 3)
            throw new InputMismatchException("Non sono stati inseriti tutti gli input richiesti");

        double o1 = 0, o2 = 0;
        try {
            o1 = Double.parseDouble(inputs[0]);
            o2 = Double.parseDouble(inputs[2]);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Gli operando devono essere numeri reali");
        }

        return calculate(o1, o2, inputs[1].charAt(0));

    }

}
