public class StringsMerge {

    public static void main(String[] args) {
        System.out.println(merge(args[0], args[1]));
    }

    private static String merge(String a, String b) {
        a += (char)255;
        b += (char)255;
        int i = 0;
        int j = 0;
        String c = "";
        while(i < a.length() - 1 || j < b.length() - 1) {
            if (a.charAt(i) < b.charAt(j)) {
                c += a.charAt(i);
                i++;
            } else if (a.charAt(i) > b.charAt(j)) {
                c += b.charAt(j);
                j++;
            } else {
                c += a.charAt(i);
                i++;
                j++;
            }
        }
        return c;
    }
    
}