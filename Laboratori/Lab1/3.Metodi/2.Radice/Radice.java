public class Radice {

    public static double radiceQuadrata(double numero) {
        if (numero <= 0) return 0;
        return Math.sqrt(numero);
    }

    public static void main(String[] args) {
        double n = Double.parseDouble(args[0]);

        double rq = radiceQuadrata(n);
        if (rq == 0) {
            System.out.println("Il valore inserito non appartiene al dominio della funzione");
       } else {
            System.out.println("Radice quadrata: " + rq);
       }
    }
    
}
