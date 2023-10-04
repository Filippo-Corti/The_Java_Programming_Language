public class Perfetti {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        if (perfetto(n))
            System.out.println(n + " e' perfetto");
        else
            System.out.println(n + " non e' perfetto");
    }

    public static boolean perfetto(int n) {
        if(n == 0) return false;
        return (sommaDivisoriPropri(n) == n);
    }

    public static int sommaDivisoriPropri(int n) {
        int sum = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) 
                sum += i;
        }
        return sum;
    }

}
