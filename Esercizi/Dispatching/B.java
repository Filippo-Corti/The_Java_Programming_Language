public class B {

    public String saluta() {
        return "Ciao dalla classe B";
    }

    public double raddoppia(double n, double x) {
        return n * 2;
    }
    
    public double raddoppia(double n, Number x) {
        return n * 4;
    }

    public static void main(String[] args) {
        B b = new A();

        // Questo:
        //System.out.println(b.saluta());
        /*
         * Esegue quello di B, se saluta esiste solo in B
         * Esegue quello di A, se saluta esiste sia in B che in A
         * Non è in grado di eseguire quello di A, se saluta esiste solo in A (a meno
         * che venga effettuato il casting ((A)b)).
         */

        // Questo:
        double n = 5;
        int x = 4;
        System.out.println(b.raddoppia(n,x));
        /*
         * Esegue quello di A SOLO se il metodo in A è Override di quello in B
         * Esegue quello di B quando il metodo in A sia un Overload di quello in B
         * (questo accade anche se A ha un metodo che sarebbe "più specifico" rispetto a
         * quello di B)
         */
    }

}
