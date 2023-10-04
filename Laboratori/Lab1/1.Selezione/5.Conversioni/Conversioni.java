public class Conversioni {
    public static void main(String[] args) {
        double qty = Double.parseDouble(args[0]);
        String unit1 = args[1], unit2 = args[3];

        switch (unit1) {
            case "minuti":
                qty *= 60;
                break;
            case "ore":
                qty *= 60 * 60;
                break;
            case "giorni":
                qty *= 60 * 60 * 24;
                break;
            case "mesi":
                qty *= 60 * 60 * 24 * 30;
                break;
            case "anni":
                qty *= 60 * 60 * 60 * 24 * 365;
                break;
            default:
                break;
        }

        switch (unit2) {
            case "minuti":
                qty /= 60;
                break;
            case "ore":
                qty /= 60 * 60;
                break;
            case "giorni":
                qty /= 60 * 60 * 24;
                break;
            case "mesi":
                qty /= 60 * 60 * 24 * 30;
                break;
            case "anni":
                qty /= 60 * 60 * 60 * 24 * 365;
                break;
            default:
                break;
        }
        System.out.println(args[0] + " " + unit1 + " corrispondono a " + qty + " " +  unit2);

    }
}
