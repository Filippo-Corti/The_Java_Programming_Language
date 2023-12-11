import java.util.Arrays;
import java.util.Scanner;

public class Test {

    public static String getFullName(String[] nomi) {
        String nome = "";
        for (String string : nomi) {
            nome += string + " ";
        }
        return nome;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Nome operatore mancante");
            System.exit(1);
        }
        Operatore operatore = new Operatore(args[0]);

        Scanner s = new Scanner(System.in);

        try {
        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");
            switch (in[1]) {
                case "add":
                    if (in[2].equals("Prepagata"))
                        operatore.add(new UtenzaPrepagata(in[0], getFullName(Arrays.copyOfRange(in, 3, in.length))));
                    else if (in[2].startsWith("Soglia")) {
                        int i = Integer.parseInt(in[2].split("-")[1]);
                        operatore.add(new UtenzaSoglia(in[0], getFullName(Arrays.copyOfRange(in, 3, in.length)), i));
                    } else
                        System.out.println("Tipo di Utenza non riconosciuto");
                    break;
                case "call":
                    operatore.getByNumero(in[0]).chiama(Integer.parseInt(in[2]));
                    break;
                case "load":
                    if (operatore.getByNumero(in[0]).getClass() == UtenzaPrepagata.class)
                        ((UtenzaPrepagata) operatore.getByNumero(in[0])).ricarica(Integer.parseInt(in[2]));
                    else
                        System.out.println("Tipo di Utenza non valido per l'operazione di ricarica");
                        System.exit(0);
                    break;
                case "reset":
                    if (operatore.getByNumero(in[0]).getClass() == UtenzaSoglia.class)
                        ((UtenzaSoglia) operatore.getByNumero(in[0])).reset();
                    else
                        System.out.println("Tipo di Utenza non valido per l'operazione di reset");
                        System.exit(0);
                    break;
                default:
                    System.out.println("Operazione non riconosciuta");
                    break;
            }
        }
        System.out.println(operatore);  
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Input non valido");
        } catch (NullPointerException | IllegalArgumentException | UtenzaNonValidaException | TempoEsauritoException e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
        } 

    }

}
