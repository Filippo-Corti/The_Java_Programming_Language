import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Bag<E> implements Iterable<E> {
    // OVERVIEW: modella un Bag/MultiSet generico.

    // attributes
    private HashMap<E, Integer> elems = new HashMap<>();

    // constructors

    // methods
    public void insert(E element) throws NullPointerException {
        // MODIFIES: this
        // EFFECTS: inserisce element in this
        // lancia NullPointerException se element == null
        if (element == null)
            throw new NullPointerException("Element nullo");

        Integer currentCount = elems.putIfAbsent(element, 1);

        if (currentCount != null)
            elems.replace(element, currentCount + 1);

        assert repOk();
    }

    @SuppressWarnings("unchecked")
    public E pick() throws NoSuchElementException, NullPointerException {
        // EFFECTS: restituisce un elemento della Bag
        // lancia NoSuchElementException se bag è vuota
        if (this.size() == 0)
            throw new NoSuchElementException("Bag vuota");

        return (E) (elems.keySet().toArray())[0];
    }

    public void remove(E element) throws NoSuchElementException, NullPointerException {
        // MODIFIES: this
        // EFFECTS: rimuove element da this
        // lancia NoSuchElementException se element non è in this
        // lancia NullPointerException se element nullo
        if (element == null)
            throw new NullPointerException("Element nullo");

        Integer currentCount = elems.get(element);

        if (currentCount == null) {
            throw new NoSuchElementException("Element non presente");
        }

        if (currentCount > 1) {
            elems.replace(element, currentCount - 1);
        } else {
            elems.remove(element);
        }

        assert repOk();
    }

    public boolean contains(E element) throws NullPointerException {
        // EFFECTS: ritorna true se element in this. false altrimenti
        // lancia NullPointerException se element nullo
        if (element == null)
            throw new NullPointerException("Element nullo");

        return elems.containsKey(element);
    }

    public int count(E element) throws NullPointerException {
        // EFFECTS: ritorna il numero di element in this
        // lancia NullPointerException se element nullo
        if (element == null)
            throw new NullPointerException("Element nullo");

        Integer currentCount = elems.get(element);

        if (currentCount == null)
            return 0;

        return currentCount;
    }

    public int size() {
        // EFFECTS: restituisce il numero totale di elementi in this
        int count = 0;
        for (E el : elems.keySet()) {
            count += elems.get(el);
        }
        return count;
    }

    public int sizeUnique() {
        // EFFECTS: restitutisce il numero di elementi unici in this
        return elems.size();
    }

    public boolean repOk() {
        if (elems == null)
            return false;

        for (E el : elems.keySet()) {
            if (el == null)
                return false;
            if (elems.get(el) <= 0)
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String res = "Bag - elements: [ ";
        for (E key : this) {
            res += key.toString() + " ";
        }
        return res + "]";
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Iterator<E> keys = elems.keySet().iterator();
            E current = null;
            int currCount = 1;

            @Override
            public boolean hasNext() {
                if (keys.hasNext())
                    return true;
                
                if (current != null && elems.get(current) > currCount)
                    return true;
                
                return false;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException("Elementi finiti");
                
                if (current == null || elems.get(current) <= currCount) {
                    current = keys.next();
                    currCount = 1;
                }
                else 
                    currCount++;

                return current;
             }

        };
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        switch (args[0]) {
            case "Integer":
                Bag<Integer> bag1 = new Bag<>();
                while(s.hasNextInt()) 
                    bag1.insert(s.nextInt());
                System.out.println(bag1);
                break;
            case "Double":
                Bag<Double> bag2 = new Bag<>();
                while(s.hasNextDouble()) 
                    bag2.insert(s.nextDouble());
                System.out.println(bag2);
                break;
            case "String":
                Bag<String> bag3 = new Bag<>();
                while(s.hasNext()) 
                    bag3.insert(s.next());
                System.out.println(bag3);
                break;
        }
    }

}
