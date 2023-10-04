public class Cerchio {

    public static double CalcolaArea(double raggio) {
        return raggio*raggio*Math.PI;
    }

    public static double CalcolaCirconferenza(double raggio) {
        return 2*raggio*Math.PI;
    }
    
    public static void main(String[] args) {
        double raggio = Double.parseDouble(args[0]);
        System.out.println("Area del cerchio: " + CalcolaArea(raggio));
        System.out.println("Circonferenza del cerchio: " + CalcolaCirconferenza(raggio));
    }

}
