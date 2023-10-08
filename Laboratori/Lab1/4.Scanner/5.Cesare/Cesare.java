import java.util.Scanner;

public class Cesare {

    public static void main(String[] args) {
        int key = Integer.parseInt(args[0]);
        System.out.println(cifraTesto(leggiTesto(), key));
    }

    public static String leggiTesto() {
        String message = "";
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            message += s.nextLine() + "\n";
        }
        return message;
    }

    public static char cifraCarattere(char c, int chiave) {
        int LETTERE_IN_ALFABETO = 26;
        chiave = ((chiave % LETTERE_IN_ALFABETO) + LETTERE_IN_ALFABETO) % LETTERE_IN_ALFABETO; //Questa roba assurda è per convertire le chiavi negative, non proprio la mia miglior invenzione diciamo
        if (isMaiuscola(c))
            return (char) (((int) c + chiave - (int) 'A') % (LETTERE_IN_ALFABETO) + (int) 'A');
        else
            return (char) (((int) c + chiave - (int) 'a') % (LETTERE_IN_ALFABETO) + (int) 'a');
    }

    public static String cifraTesto(String s, int chiave) {
        String newS = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isLettera(c))
                newS += cifraCarattere(s.charAt(i), chiave);
            else
                newS += c;
        }
        return newS;
    }

    private static boolean isMaiuscola(char c) {
        return (c >= 'A' && c <= 'Z');
    }

    private static boolean isMinuscola(char c) {
        return (c >= 'a' && c <= 'z');
    }

    private static boolean isLettera(char c) {
        return (isMaiuscola(c) || isMinuscola(c));
    }

}
