import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Evento implements Cloneable {
    // OVERVIEW: modella un Evento con data e nome. Astrazione immutabile

    // attributes
    Date data;
    String nome;

    // constructors
    public Evento(Date data, String nome) throws IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza this
        // se data = null lancia NullPointerException
        // se nome = null lancia NullPointerException
        // se nome vuoto lancia IllegalArgumentException
        if (data == null)
            throw new NullPointerException("Data nulla");

        if (nome == null)
            throw new NullPointerException("Nome nullo");

        if (nome.equals(""))
            throw new IllegalArgumentException("Nome vuoto");

        this.data = (Date) data.clone();
        this.nome = nome;

        assert repOk();
    }

    // methods
    public Date getData() {
        return (Date) data.clone();
    }

    public String getNome() {
        return nome;
    }

    public boolean repOk() {
        if (this.data == null)
            return false;

        if (this.nome == null)
            return false;

        if (this.nome.equals(""))
            return false;

        return true;
    }

    public Evento copiaEvento(int n) throws IllegalArgumentException {
        // EFFECTS: crea un nuovo evento con this.nome e this.data + n
        // se n <= 0 lancia IllegalArgumentException
        if (n <= 0)
            throw new IllegalArgumentException("n <= 0");

        Evento nuovo = (Evento) this.clone();
        nuovo.data = new Date(nuovo.data.getTime() + TimeUnit.DAYS.toMillis(n));
        return nuovo;

        // return new Evento(new Date(this.data.getTime() + TimeUnit.DAYS.toMillis(n)),
        // this.nome);
    }

    @Override
    public Object clone() {
        Evento nuovo = null;
        try {
            nuovo = (Evento) super.clone();
        } catch (CloneNotSupportedException e) {
            nuovo = new Evento(data, nome);
        }
        // Eventuali attributi mutabili devono essere clonati
        nuovo.data = (Date) data.clone();

        return nuovo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Evento other = (Evento) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Evento: " + this.nome + " in data: " + this.data.getDate() + "/" + this.data.getMonth() + "/"
                + (this.data.getYear() + 1900);
    }

}