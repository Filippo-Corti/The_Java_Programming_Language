public class Multiplo {
    public static void main(String[] args) {
        int n1 = Integer.parseInt(args[0]), n2 = Integer.parseInt(args[1]);
        if (n1 % n2 == 0) 
            System.out.println(n1 + " e' multiplo di " + n2);
        else 
            System.out.println(n1 + " non e' multiplo di " + n2);
    }    
}
