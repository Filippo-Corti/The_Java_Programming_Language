public class Palindroma {

    public static void main(String[] args) {
        if (isPalindroma(args[0]))
            System.out.println("Palidroma");
        else
            System.out.println("Non palindroma");
    }

    private static boolean isPalindroma(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i))
                return false;
        }
        return true;
    }
}
