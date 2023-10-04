public class Primi {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        numeriPrimi(n);
    }

    public static boolean primo(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static void numeriPrimi(int limite) {
        if(limite <= 0) {
            System.out.println("La soglia inserita non e' positiva");
            return;
        }
        System.out.println("Numeri primi inferiori a " + limite);
        for (int i = 2; i < limite; i++) {
            if (primo(i))
                System.out.print(i + " ");
        }
        System.out.println();
    }

}
