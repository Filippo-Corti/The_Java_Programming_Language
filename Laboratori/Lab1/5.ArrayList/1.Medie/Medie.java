public class Medie {
    
    public static void main(String[] args) {
        double somma = 0;
        int i = 0;
        for (String s : args) {
            int n = Integer.parseInt(s);
            somma += n;
            i++;
        }    
        System.out.println("Media aritmetica: " + somma/i);
    }

}
