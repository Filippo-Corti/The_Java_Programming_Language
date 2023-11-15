import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println(
                "Inserisci un prodotto nel formato: `<nome> <costo> alimentare <datascadenza>` oppure `<nome> <costo> nondeperibile riciclabile\\nonriciclabile`");

        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");
            Prodotto p = null;
            switch (in[2]) {
                case "alimentare":
                    String[] campiData = in[3].split("-");
                    LocalDate data = LocalDate.of(
                            Integer.parseInt(campiData[2]),
                            Integer.parseInt(campiData[1]),
                            Integer.parseInt(campiData[0]));
                    p = new ProdottoAlimentare(in[0], Double.parseDouble(in[1]), data);
                    break;
                case "nondeperibile":
                    p = new ProdottoNonDeperibile(in[0], Double.parseDouble(in[1]), in[3].equals("riciclabile"));
                    break;
                default:
                    System.out.println("Prodotto di una categoria non riconosciuta");
                    break;
            }
            System.out.println("Prezzo con sconto del " + p.getPercentualeSconto() + "%: " + p.sconto());
        }
    }

}
