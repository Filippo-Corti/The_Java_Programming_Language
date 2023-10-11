import java.util.Map;
import java.util.HashMap;

public class NumeriRomani {
    // OVERVIEW: classe statica che effettua conversioni fra numeri arabi e numeri
    // romani

    private static Map<String, Integer> r2m = new HashMap<>();

    private static void initMap() {
        r2m.put("I", 1);
        r2m.put("IV", 4);
        r2m.put("V", 5);
        r2m.put("IX", 9);
        r2m.put("X", 10);
        r2m.put("XL", 40);
        r2m.put("L", 50);
        r2m.put("XC", 90);
        r2m.put("C", 100);
        r2m.put("CD", 400);
        r2m.put("D", 500);
        r2m.put("CM", 900);
        r2m.put("M", 1000);
    }

    public static void main(String[] args) {
        initMap();
        if (isRoman(args[0]))
            System.out.println(romanToArab(args[0]));
        if (isArab(args[0]))
            System.out.println(arabToRoman(Integer.parseInt(args[0])));
    }

    public static boolean isRoman(String s) {
        // EFFECTS: returns false if s is null, is empty or is not a roman number.
        // Returns true otherwise.
        if (s == null || s == "")
            return false;
        int i = 0;
        int lastValue = Integer.MAX_VALUE;
        while (i < s.length()) {

            if ((i < s.length() - 1)
                    && (r2m.containsKey(s.substring(i, i + 2)) && r2m.get(s.substring(i, i + 2)) < lastValue)) {
                lastValue = r2m.get(s.substring(i, i + 2));
                i += 2;
            } else if ((r2m.containsKey(s.substring(i, i + 1)) && r2m.get(s.substring(i, i + 1)) <= lastValue)) {
                lastValue = r2m.get(s.substring(i, i + 1));
                i += 1;
            } else
                return false;
        }
        return true;
    }

    public static boolean isArab(String s) {
        // EFFECTS: returns false if s is null, is empty or is not an arab number.
        // Returns true otherwise.
        if (s == null || s == "" || s.charAt(0) == '0')
            return false;
        int i = 0;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return (i > 0);
    }

    public static int romanToArab(String s) {
        // EFFECTS: returns s converted in arab numbers (in int).
        // If s is not a roman number it returns zero.
        int i = 0;
        int n = 0;
        while (i < s.length()) {
            if ((i < s.length() - 1) && (r2m.containsKey(s.substring(i, i + 2)))) {
                n += r2m.get(s.substring(i, i + 2));
                i += 2;
            } else {
                n += r2m.get(s.substring(i, i + 1));
                i += 1;
            }
        }
        return n;
    }

    public static String arabToRoman(int i) {
        // EFFECTS: returns i converted in roman numbers (as a string).
        if (i <= 0) 
            return null;
        // If i is <= 0 it returns null.
        String[] a2rS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] a2r = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String ris = "";
        for (int j = 0; j < a2r.length; j++) {
            while(i >= a2r[j]) {
                i -= a2r[j];
                ris += a2rS[j];
            }
        }
        return ris;
    }

}
