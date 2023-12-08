import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        MazzoRidotto mazzo = null;
        if (args[0].equals("Mazzo"))
            mazzo = new Mazzo();
        else if (args[0].equals("MazzoRidotto"))
            mazzo = new MazzoRidotto();
        else {
            System.out.println("Tipo di Mazzo non riconosciuto");
            System.exit(1);
        }

        mazzo.shuffle();
        System.out.println(mazzo);

        Scanner s = new Scanner(System.in);
        while(s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");
            switch (in[0]) {
                case "estrai":
                        if(mazzo.hasNext())
                            System.out.println("Estratto: " + mazzo.next());
                    break;
                case "inserisci":
                    try {                  
                        Carta c = Carta.stringToCard(in[1] + " " + in[2]);
                        mazzo.add(c);
                    } catch (CardExistsException | CardNotValidException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Istruzione non valida");
                    break;
            }
        }

        mazzo.sort();
        System.out.println(mazzo);

    }

}
