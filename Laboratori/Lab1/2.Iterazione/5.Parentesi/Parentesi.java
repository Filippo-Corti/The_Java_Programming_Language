public class Parentesi {
    public static void main(String[] args) {
        int c= 0;

        for(int i = 0; i < args[0].length(); i++) {
            if (args[0].charAt(i) == '[')
                c++;
            if (args[0].charAt(i) == ']') 
                c--; 
            if (c < 0) 
                break;
        }
        if (c != 0) {
            System.out.println("Non bilanciate");
        } else {
            System.out.println("Bilanciate");
        }
    }
}
