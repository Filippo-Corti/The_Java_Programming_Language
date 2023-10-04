public class Palindrome {
    
    public static void main(String[] args) {
        System.out.print("[ ");

        for (int i = 0; i < args[0].length() - 1; i++) {
            for (int j = i+2; j <= args[0].length(); j++) {
                String s = args[0].substring(i, j);
                if(isPalindroma(s))
                    System.out.print(s + " ");
            }
        }
        System.out.println("]");

    }

    private static boolean isPalindroma(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i))
                return false;
        }
        return true;
    }
}
