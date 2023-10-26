import java.util.InputMismatchException;
import java.util.Scanner;

public class Orario {
    // OVERVIEW: classe mutabile che definisce un orario nel formato 24H

    // attributes
    int ore;
    int minuti;

    // constructors
    public Orario() {
        // MODIFIES: this
        // EFFECTS: inizializza un nuovo oggetto Orario con orario 00:00
        this.ore = 0;
        this.minuti = 0;

        assert repOk();
    }

    public Orario(int ore, int minuti) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un nuovo oggetto Orario con ore e minuti specificate
        // lancia IllegalArgumenteException se ore e minuti non sono nel corretto range
        // (0-23 e 0-59 rispettivamente)
        if (ore < 0 || ore > 23)
            throw new IllegalArgumentException("Valore non valido per ore (0-23)");

        if (minuti < 0 || minuti > 59)
            throw new IllegalArgumentException("Valore non valido per minuti (0-59)");

        this.ore = ore;
        this.minuti = minuti;

        assert repOk();
    }

    public Orario(int ore, int minuti, boolean PM) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza un nuovo oggetto Orario con ore e minuti specificate,
        // apportando le modifiche necessarie per convertirlo dal formato 12H a quello
        // 24H
        // lancia IllegalArgumenteException se ore e minuti non sono nel corretto range
        // (1-12 e 0-59 rispettivamente)
        if (ore < 1 || ore > 12)
            throw new IllegalArgumentException("Valore non valido per ore (1-12)");

        if (minuti < 0 || minuti > 59)
            throw new IllegalArgumentException("Valore non valido per minuti (0-59)");

        this.ore = convertiOra12to24(ore, PM);
        this.minuti = minuti;

        assert repOk();
    }

    // methods

    public int getOre() {
        return ore;
    }

    public int getMinuti() {
        return minuti;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Orario other = (Orario) obj;
        if (ore != other.ore)
            return false;
        if (minuti != other.minuti)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", ore, minuti);
    }

    public boolean repOk() {
        if (ore < 0 || ore > 23)
            return false;

        if (minuti < 0 || minuti > 59)
            return false;

        return true;
    }

    public void avanza(int ore, int minuti) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: fa avanzare l'orario di ore e minuti specificati, gestendo
        // opportunamente il cambio d'ora
        // lancia IllegalArgumentException se ore o minuti sono valori negativi
        if (ore < 0 || minuti < 0)
            throw new IllegalArgumentException("Impossibile avanzare di un numero di ore o minuti negativo");

        this.ore = (this.ore + ore) % 24;

        this.minuti += minuti;
        if (this.minuti > 59) {
            this.ore += (this.minuti / 60);
            this.minuti = this.minuti % 60;
        }

        assert repOk();
    }

    public String getOra24() {
        // EFFECTS: restituisce una stringa che rappresenta this in formato 24H (hh:mm)
        return this.toString();
    }

    public String getOra12() {
        // EFFECTS: restituisce una stringa che rappresenta this in formato 12H (hh:mm)
        int ore = convertiOra24to12(this.ore);
        String suf = (this.ore >= 12) ? "PM" : "AM";

        return String.format("%02d:%02d %s", ore, minuti, suf);
    }

    private int convertiOra24to12(int ore) throws IllegalArgumentException {
        // EFFECTS: ritorna il corrispettivo di ore in formato 12H (ignora AM/PM)
        // lancia IllegalArgumenteException se ore non è nel corretto range (0-23)
        if (ore < 0 || ore > 23)
            throw new IllegalArgumentException("Valore non valido per ore (0-23)");

        if (ore == 0)
            return 12;

        if (ore <= 12)
            return ore;

        return ore - 12;
    }

    private int convertiOra12to24(int ore, boolean PM) throws IllegalArgumentException {
        // EFFECTS: ritorna il corrispettivo di ore in formato 24H
        // lancia IllegalArgumenteException se ore non è corretto range(1-12)
        if (ore < 1 || ore > 12)
            throw new IllegalArgumentException("Valore non valido per ore (1-12)");

        if (ore == 12 && !PM)
            return 0;

        if (ore == 12 && PM)
            return 12;

        if (ore < 12 && !PM)
            return ore;

        return ore + 12;
    }

    public static void main(String[] args) {

        Orario orario = null;
        try {
            String[] time = args[0].split(":");
            if (args.length == 1) {
                orario = new Orario(
                        Integer.parseInt(time[0]),
                        Integer.parseInt(time[1]));
            } else {
                orario = new Orario(
                        Integer.parseInt(time[0]),
                        Integer.parseInt(time[1]),
                        args[1].equals("PM"));
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            orario = new Orario();
        }

        System.out.println("Orario: " + orario.getOra24());
        System.out.println("Orario: " + orario.getOra12());

        Scanner s = new Scanner(System.in);
        try {
        s.next();
            orario.avanza(s.nextInt(), s.nextInt());
        } catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Orario: " + orario.getOra24());
        System.out.println("Orario: " + orario.getOra12());

    }

}
