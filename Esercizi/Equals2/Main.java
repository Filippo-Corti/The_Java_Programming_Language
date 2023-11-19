public class Main {

    public static void main(String[] args) {

        Punto2 p1 = new Punto2(0, 0);
        Punto3 p2 = new Punto3(0, 0, 0);

        Set punti = new Set();
        punti.insert(p1);
        punti.insert(p2);
        System.out.println(punti.contains(p2)); //true
        System.out.println(punti); // Sia p1 che p2

    }
}
