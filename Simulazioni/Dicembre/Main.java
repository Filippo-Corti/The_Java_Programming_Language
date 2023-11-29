import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        AlberoNatalizio albero = new AlberoNatalizio(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
    
        Scanner s = new Scanner(System.in);

        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");

            try {
            switch (in[0]) {
                case "Decorazione":
                        albero.add(new Decorazione(in[1], Double.parseDouble(in[2])));
                    break;
            
                case "DecorazioneElettrica":
                        albero.add(new DecorazioneElettrica(in[1], Double.parseDouble(in[2]), Double.parseDouble(in[3])));
                    break;
            
                case "Puntale":
                        albero.add(new Puntale(in[1], Double.parseDouble(in[2])));
                    break;
                default: 
                    System.out.println("Tipo di Decorazione non riconosciuta");
            }
            } catch (WeightReachedException | TopperExistsException e) {
                System.out.println("Non si può aggiungere: " + e.getMessage());
            }
        }

        albero.accendiAlbero();

        System.out.println();
        System.out.println(albero);

    }

}
