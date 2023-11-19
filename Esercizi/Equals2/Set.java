import java.util.NoSuchElementException;

public class Set {

    private Punto[] els; // tipo Object

    public void insert(Punto x) {
        if (els == null)
            els = new Punto[] { x };
        else if (!this.contains(x)) {
            Punto[] tmp = new Punto[els.length + 1];
            for (int i = 0; i < els.length; i++)
                tmp[i] = els[i];
            tmp[els.length] = x;
            this.els = tmp;
        }
    }

    public boolean contains(Punto x) {
        for (Punto i : els)
            if (i.equals(x))
                return true;
        return false;
    }

    public Punto choose() {
        if (this.els == null)
            throw new NoSuchElementException(); // se vuoto
        return els[0]; // ritorna primo
    }

    public String toString() {
        String ret = "";
        for (Punto o : els)
            ret += o + " ";
        return ret;
    }
}
