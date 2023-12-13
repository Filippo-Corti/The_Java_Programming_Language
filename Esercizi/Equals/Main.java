public class Main {

    public static void main(String[] args) {

        //Parto con due equals che controllano tutte le coordinate e utilizzano "getClass()"

        Punto2 p1 = new Punto2(0, 0);
        Punto3 p2 = new Punto3(0, 0, 0);
        Punto2 p3 = new Punto2(0, 0);

        System.out.println(p1.equals(p3)); //true
        System.out.println(p1.equals(p2)); //false

        //Non vale la regola dei metodi! (Principio di Liskov)

        //Utilizzo allora due equals che controllano tutte le coordinate e utilizzano "instanceof"

        System.out.println(p1.equals(p2)); //true
        System.out.println(p2.equals(p1)); //false

        //Non vale la proprietà simmetrica!

        //Modifico l'equals in modo che se Punto3 instanceof Punto2 allora controllo solo x e y
        
        System.out.println(p1.equals(p2)); //true
        System.out.println(p2.equals(p1)); //true

        //Ora la simmetrica vale!

        Punto3 p4 = new Punto3(0, 0, -1);

        System.out.println(p1.equals(p4)); //true
        System.out.println(p1.equals(p2)); //true
        System.out.println(p2.equals(p4)); //false

        //Non vale la proprietà transitiva!

        // => Devo scegliere cosa preferisco rompere

        Set punti = new Set();
        punti.insert(p1);
        punti.insert(p2);
        System.out.println(punti.contains(p2)); //true
        System.out.println(punti); // Solo p1

    }
}
