import java.util.InputMismatchException;
import java.util.Scanner;

public class SvolgiCalcolo {
    // OVERVIEW: classe statica che testa CalcolatriceConMemoria

    public static void main(String[] args) {
        CalcolatriceConMemoria calcolatriceConMemoria = new CalcolatriceConMemoria();

        if (args.length >= 1) {
            try {
                calcolatriceConMemoria = new CalcolatriceConMemoria(Double.parseDouble(args[0]));
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }

        Scanner s = new Scanner(System.in);

        System.out.println("Calcolatrice inizializzata, valore: " + calcolatriceConMemoria.getMem());
        System.out.println("Inserisci <operatore> <operando>. es: + 3");
        System.out.println("Per terminare inserire il carattere '='.");
        while (true) {
            String in = s.nextLine();

            if (in.equals("="))
                break;

            String[] splitIn = in.split(" ");
            try {
                calcolatriceConMemoria.operate(splitIn[0].charAt(0), Double.parseDouble(splitIn[1]));
            } catch (DivideByZeroException e) {
                System.out.println(e.getMessage());
                System.out.println("Inserisci <operatore> <operando>. es: + 3");
                System.out.println("Per terminare inserire il carattere '='.");
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("Inserisci <operatore> <operando>. es: + 3");
                System.out.println("Per terminare inserire il carattere '='.");
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Input non valido: " + e.getMessage());
            }
            System.out.println("valore in memoria: " + calcolatriceConMemoria.getMem());
        }
        System.out.println("Il risultato finale e': " + calcolatriceConMemoria.getMem());
    }

}
