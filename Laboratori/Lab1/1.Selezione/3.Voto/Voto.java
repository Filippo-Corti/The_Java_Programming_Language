public class Voto {
    public static void main(String[] args) {
        int v = Integer.parseInt(args[0]);
        if (v < 0 || v > 100)
            System.out.println("Errore");
        else if (v < 60)
            System.out.println("Insufficiente");
        else if (v >= 60 && v < 70)
            System.out.println("Sufficiente");
        else if (v >= 70 && v < 80)
            System.out.println("Buono");
        else if (v >= 80 && v < 90)
            System.out.println("Distinto");
        else if (v >= 90 && v <= 100) 
            System.out.println("Ottimo");
    }
}