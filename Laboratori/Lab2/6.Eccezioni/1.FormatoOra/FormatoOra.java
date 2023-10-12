import java.time.format.DateTimeParseException;

public class FormatoOra {
    // OVERVIEW static class for conversions from 24-hours format time to 12-hours
    // format time

    public static String converti(String s) throws DateTimeParseException {
        // REQUIRES: s not null
        // EFFECTS: returns s converted in 12-hours format.
        // throws DateTimeParseException if the conversion is not possible

        String[] splitTime = s.split(":");
        if (splitTime.length != 2 || splitTime[0].length() != 2 || splitTime[1].length() != 2)
            throw new DateTimeParseException("Formato ora non valido " + s, s, 0);

        int hours;
        try {
            hours = Integer.parseInt(splitTime[0]); // Checking if hh is a number
        } catch (NumberFormatException e) {
            throw new DateTimeParseException("Ore non valide " + splitTime[0], s, 0);
        }

        if (hours < 0 || hours > 23) // Checking if hh is a valid number
            throw new DateTimeParseException("Ore non valide " + splitTime[0], s, 0);

        int minutes;
        try {
            minutes = Integer.parseInt(splitTime[1]); // Checking if mm is a number
        } catch (NumberFormatException e) {
            throw new DateTimeParseException("Minuti non validi " + splitTime[1], s, 0);
        }

        if (minutes < 0 || minutes > 59) // Checking if mm is a valid number
            throw new DateTimeParseException("Minuti non validi " + splitTime[1], s, 0);

        String converted = "";
        if (hours > 0 && hours <= 12)
            converted += hours;
        else if (hours == 0)
            converted += "12";
        else
            converted += (hours - 12);

        if (minutes >= 10)
            converted += ":" + minutes;
        else
            converted += ":0" + minutes;

        if (hours >= 0 && hours <= 11)
            converted += " AM";
        else
            converted += " PM";

        return converted;

    }

    public static void main(String[] args) {
        try {
            System.out.println(converti(args[0]));
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
