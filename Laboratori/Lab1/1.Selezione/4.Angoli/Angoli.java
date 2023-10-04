public class Angoli {
    
    public static void main(String[] args) {
        int a1 = Integer.parseInt(args[0]), a2 = Integer.parseInt(args[1]);
        if (a1 + a2 > 180) 
            System.out.println("I due angoli non appartengono ad un triangolo");
        else 
            System.out.println("Ampiezza terzo angolo = " + (180-a1-a2) + "°");
    }
}
